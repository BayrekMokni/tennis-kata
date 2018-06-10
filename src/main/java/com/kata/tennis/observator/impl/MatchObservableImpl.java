package com.kata.tennis.observator.impl;

import com.kata.tennis.match.Match;
import com.kata.tennis.observator.MatchObservable;
import com.kata.tennis.observator.MatchObserver;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class MatchObservableImpl implements MatchObservable {

    private Set<MatchObserver> observers = new HashSet<>();

    @Override
    public void addObserver(MatchObserver matchObserver) {
        observers.add(matchObserver);
    }

    @Override
    public void removeObserver(MatchObserver matchObserver) {
        observers.remove(matchObserver);
    }

    @Override
    public void notifyObservers(Integer playerMarkedThePointId, Match match) {
        observers.forEach(matchObserver -> matchObserver.handleChanges(playerMarkedThePointId, match));
    }
}
