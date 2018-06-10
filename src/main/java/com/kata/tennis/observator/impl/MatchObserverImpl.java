package com.kata.tennis.observator.impl;

import com.kata.tennis.exception.MatchAlreadyOverRuntimeException;
import com.kata.tennis.exception.NoPlayerFoundRuntimeException;
import com.kata.tennis.match.Match;
import com.kata.tennis.observator.MatchObserver;
import com.kata.tennis.set.SetTennisGame;
import com.kata.tennis.set.SetTennisGameBuilder;
import com.kata.tennis.set.SetTennisGameMonitorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import static com.kata.tennis.match.MatchStatus.FIRST_PLAYER_WIN;
import static com.kata.tennis.match.MatchStatus.SECOND_PLAYER_WIN;

@Service
public class MatchObserverImpl implements MatchObserver {

    @Autowired
    private SetTennisGameMonitorImpl setTennisGameMonitor;

    public SetTennisGameMonitorImpl getSetTennisGameMonitor() {
        return setTennisGameMonitor;
    }

    public MatchObserverImpl setSetTennisGameMonitor(SetTennisGameMonitorImpl setTennisGameMonitor) {
        this.setTennisGameMonitor = setTennisGameMonitor;
        return this;
    }

    @Override
    public void handleChanges(Integer playerMarkedThePointId, Match match) {
        if (!match.getMatchStatus().isInProgress()) {
            throw new MatchAlreadyOverRuntimeException("Match already over");
        }
        if (!Arrays.asList(1, 2).contains(playerMarkedThePointId)) {
            throw new NoPlayerFoundRuntimeException("Unknown player id");
        }
        SetTennisGame currentSetTennisGame = match.getSets().getLast();
        setTennisGameMonitor.setSetTennisGame(currentSetTennisGame);
        setTennisGameMonitor.winPoint(playerMarkedThePointId);

        if (isSetFinished(currentSetTennisGame)) {
            increaseWinSetNumberFor(playerMarkedThePointId, match);
            endMatchOrGetToNextSet(match, currentSetTennisGame);
        }
    }

    private void increaseWinSetNumberFor(Integer playerMarkedThePointId, Match match) {
        if (playerMarkedThePointId.equals(1)) {
            match.increaseFirstPlayerWinSet();
        } else {
            match.increaseSecondPlayerWinSet();
        }
    }

    private boolean isSetFinished(SetTennisGame currentSetTennisGame) {
        return !currentSetTennisGame.isInProgress();
    }

    private Match endMatchOrGetToNextSet(Match match, SetTennisGame currentSetTennisGame) {
        if (match.getFirstPlayerWinSetNumber().equals(3)) {
            return match.setMatchStatus(FIRST_PLAYER_WIN);
        } else if (match.getSecondPlayerWinSetNumber().equals(3)) {
            return match.setMatchStatus(SECOND_PLAYER_WIN);
        }
        SetTennisGame setTennisGame = new SetTennisGameBuilder()
                .withGame(currentSetTennisGame.getGame())
                .buildAndStart();
        match.getSets().add(setTennisGame);
        return match;
    }
}
