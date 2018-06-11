package com.kata.tennis.match.impl;

import com.kata.tennis.match.Match;
import com.kata.tennis.match.MatchMonitor;
import com.kata.tennis.observator.MatchObservable;
import com.kata.tennis.observator.MatchObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MatchMonitorImpl implements MatchMonitor {

    private Match match;
    private MatchObservable matchObservable;

    @Override
    public void markPoint(Integer playerMarkedThePointId) {
        matchObservable.notifyObservers(playerMarkedThePointId, match);
    }

    @Override
    public void addObserver(MatchObserver matchObserver) {
        matchObservable.addObserver(matchObserver);
    }

    @Autowired
    public void setMatchObservable(MatchObservable matchObservable) {
        this.matchObservable = matchObservable;
    }

    @Autowired
    public void setMatch(Match match) {
        this.match = match;
    }
}
