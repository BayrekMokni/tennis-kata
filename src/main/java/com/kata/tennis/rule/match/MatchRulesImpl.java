package com.kata.tennis.rule.match;

import com.kata.tennis.match.Match;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MatchRulesImpl implements MatchRule {

    private List<WinSetGameMatchRule> rules = new ArrayList<>();

    @Override
    public void validate(Match match) {
        getRules().forEach(rules -> rules.validate(match));
    }

    private List<WinSetGameMatchRule> getRules() {
        rules.add(new WinSetGameMatchRule());
        return rules;
    }
}
