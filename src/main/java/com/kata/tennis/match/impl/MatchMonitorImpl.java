package com.kata.tennis.match.impl;

import com.kata.tennis.match.Match;
import com.kata.tennis.match.MatchMonitor;
import com.kata.tennis.observator.MatchObservable;
import com.kata.tennis.observator.MatchObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MatchMonitorImpl implements MatchMonitor {

    @Autowired
    private MatchObservable matchObservable;

    @Autowired
    private Match match;

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    @Override
    public void checkScores(Integer playerMarkedThePointId) {
        matchObservable.notifyObservers(playerMarkedThePointId, match);
    }

    public void addObserver(MatchObserver matchObserver) {
        matchObservable.addObserver(matchObserver);
    }
}
