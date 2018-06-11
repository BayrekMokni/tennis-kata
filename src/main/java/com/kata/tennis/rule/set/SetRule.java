package com.kata.tennis.rule.set;

import com.kata.tennis.game.GameScore;
import com.kata.tennis.rule.TennisRule;
import com.kata.tennis.set.SetTennisGame;
import org.springframework.stereotype.Service;

/**
 * This interface introduce set rules.
 */

@Service
public interface SetRule extends TennisRule {
    void validate(GameScore newScore, SetTennisGame setTennisGame);
}
