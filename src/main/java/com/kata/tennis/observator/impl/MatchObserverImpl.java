package com.kata.tennis.observator.impl;

import com.kata.tennis.exception.MatchAlreadyOverRuntimeException;
import com.kata.tennis.exception.NoPlayerFoundRuntimeException;
import com.kata.tennis.match.Match;
import com.kata.tennis.observator.MatchObserver;
import com.kata.tennis.rule.match.MatchRule;
import com.kata.tennis.set.SetTennisGame;
import com.kata.tennis.set.SetTennisGameBuilder;
import com.kata.tennis.set.SetTennisGameMonitorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import static com.kata.tennis.game.GameScore.ZERO_ALL;
import static com.kata.tennis.match.MatchStatus.FIRST_PLAYER_WIN;
import static com.kata.tennis.match.MatchStatus.SECOND_PLAYER_WIN;

/**
 * This Observable pattern help us to notify classes that are observing if any changes will be made.
 * Our observer here will handle the notification when any player will make a point.
 * We can add any other observer to handle differently the notification. For example when notifying a betting website
 * observing the game.
 */
@Service
public class MatchObserverImpl implements MatchObserver {

    @Autowired
    private SetTennisGameMonitorImpl setTennisGameMonitor;
    private MatchRule matchRule;

    public SetTennisGameMonitorImpl getSetTennisGameMonitor() {
        return setTennisGameMonitor;
    }

    public MatchObserverImpl setSetTennisGameMonitor(SetTennisGameMonitorImpl setTennisGameMonitor) {
        this.setTennisGameMonitor = setTennisGameMonitor;
        return this;
    }

    public MatchRule getMatchRule() {
        return matchRule;
    }

    public MatchObserverImpl setMatchRule(MatchRule matchRule) {
        this.matchRule = matchRule;
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
        matchRule.validate(match);
        if (match.getMatchStatus().equals(FIRST_PLAYER_WIN) ||
                match.getMatchStatus().equals(SECOND_PLAYER_WIN)) {
            return match;
        }
        SetTennisGame setTennisGame = new SetTennisGameBuilder()
                .withGame(currentSetTennisGame.getGame())
                .buildAndStart();
        setTennisGame.getGame().setScore(ZERO_ALL);
        match.getSets().add(setTennisGame);
        return match;
    }
}
