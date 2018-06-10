package com.kata.tennis.observator;

import com.kata.tennis.match.Match;
import org.springframework.stereotype.Service;

@Service
public interface MatchObservable {
    void addObserver(MatchObserver matchObserver);
    void removeObserver(MatchObserver matchObserver);
    void notifyObservers(Integer playerId, Match match);
}
