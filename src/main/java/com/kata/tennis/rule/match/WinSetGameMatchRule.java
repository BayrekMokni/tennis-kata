package com.kata.tennis.rule.match;

import com.kata.tennis.match.Match;
import org.springframework.stereotype.Component;

import static com.kata.tennis.match.MatchStatus.FIRST_PLAYER_WIN;
import static com.kata.tennis.match.MatchStatus.SECOND_PLAYER_WIN;

@Component
public class WinSetGameMatchRule {

    public Match validate(Match match) {
        if (match.getFirstPlayerWinSetNumber().equals(3)) {
            return match.setMatchStatus(FIRST_PLAYER_WIN);
        } else if (match.getSecondPlayerWinSetNumber().equals(3)) {
            return match.setMatchStatus(SECOND_PLAYER_WIN);
        }
        return match;
    }
}
