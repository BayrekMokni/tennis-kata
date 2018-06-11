package com.kata.tennis.rule.match;

import com.kata.tennis.match.Match;
import com.kata.tennis.rule.TennisRule;
import org.springframework.stereotype.Service;

/**
 * This interface introduce match rules.
 */

@Service
public interface MatchRule extends TennisRule {
    void validate(Match match);
}
