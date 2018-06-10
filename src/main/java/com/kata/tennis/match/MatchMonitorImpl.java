package com.kata.tennis.match;

import com.kata.tennis.exception.MatchAlreadyOverRuntimeException;
import com.kata.tennis.exception.NoPlayerFoundRuntimeException;
import com.kata.tennis.set.SetTennisGame;
import com.kata.tennis.set.SetTennisGameBuilder;
import com.kata.tennis.set.SetTennisGameMonitorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static com.kata.tennis.match.MatchStatus.FIRST_PLAYER_WIN;
import static com.kata.tennis.match.MatchStatus.SECOND_PLAYER_WIN;

@Component
public class MatchMonitorImpl implements MatchMonitor {

    @Autowired
    private SetTennisGameMonitorImpl setTennisGameMonitor;

    private Match match;

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public SetTennisGameMonitorImpl getSetTennisGameMonitor() {
        return setTennisGameMonitor;
    }

    public void setSetTennisGameMonitor(SetTennisGameMonitorImpl setTennisGameMonitor) {
        this.setTennisGameMonitor = setTennisGameMonitor;
    }

    @Override
    public void checkScores(Integer playerMarkedThePointId) {
        if (!match.getMatchStatus().isInProgress()) {
            throw new MatchAlreadyOverRuntimeException("Match already over");
        }
        if (!Arrays.asList(1, 2).contains(playerMarkedThePointId)) {
            throw new NoPlayerFoundRuntimeException("Unknown player id");
        }
        SetTennisGame currentSetTennisGame = match.getSets().getLast();
        setTennisGameMonitor.setSetTennisGame(currentSetTennisGame);
        setTennisGameMonitor.winPoint(playerMarkedThePointId);

        if (!currentSetTennisGame.isInProgress()) {
            if (playerMarkedThePointId.equals(1)) {
                match.increaseFirstPlayerWinSet();
            } else {
                match.increaseSecondPlayerWinSet();
            }
            updateStatusMatchIfEnded();
            if (!match.getMatchStatus().equals(FIRST_PLAYER_WIN)
                    || !match.getMatchStatus().equals(SECOND_PLAYER_WIN)) {
                SetTennisGame setTennisGame = new SetTennisGameBuilder()
                        .withGame(currentSetTennisGame.getGame())
                        .buildAndStart();
                match.getSets().add(setTennisGame);
            }
        }
    }

    private void updateStatusMatchIfEnded() {
        if (match.getFirstPlayerWinSetNumber().equals(3)) {
            match.setMatchStatus(FIRST_PLAYER_WIN);
        } else if (match.getSecondPlayerWinSetNumber().equals(3)) {
            match.setMatchStatus(SECOND_PLAYER_WIN);
        }
    }
}
