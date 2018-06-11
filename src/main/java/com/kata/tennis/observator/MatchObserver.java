package com.kata.tennis.observator;

import com.kata.tennis.match.Match;
import org.springframework.stereotype.Service;

/**
 * This interface introduce observers client behavior.
 */

@Service
public interface MatchObserver {
    void handleChanges(Integer playerMarkedThePointId, Match match);
}
