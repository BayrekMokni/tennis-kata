package com.kata.tennis.match;

import com.kata.tennis.observator.MatchObserver;

public interface MatchMonitor {
    void markPoint(Integer playerMarkedThePointId);
    void addObserver(MatchObserver matchObserver);
}
