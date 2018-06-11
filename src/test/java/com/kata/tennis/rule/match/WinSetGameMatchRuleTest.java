package com.kata.tennis.rule.match;

import com.kata.tennis.match.Match;
import com.kata.tennis.player.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.kata.tennis.TennisTestUtil.*;
import static com.kata.tennis.match.MatchStatus.FIRST_PLAYER_WIN;
import static com.kata.tennis.match.MatchStatus.IN_PROGRESS;
import static com.kata.tennis.match.MatchStatus.SECOND_PLAYER_WIN;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class WinSetGameMatchRuleTest {

    @InjectMocks
    private WinSetGameMatchRule winSetGameMatchRule;

    private Match match;

    @Before
    public void setUp() {
        Player michael = getFirstPlayer("Michael");
        Player david = getSecondPlayer("David");
        match = getAndStartMatch(michael, david);
    }

    @Test
    public void shouldSetMatchStatusToFirstPlayerWin_whenWinSetNumberIs3() {
        match.setFirstPlayerWinSetNumber(3);

        winSetGameMatchRule.validate(match);

        assertThat(match.getMatchStatus()).isEqualTo(FIRST_PLAYER_WIN);
    }

    @Test
    public void shouldSetMatchStatusToSecondPlayerWin_whenWinSetNumberIs3() {
        match.setSecondPlayerWinSetNumber(3);

        winSetGameMatchRule.validate(match);

        assertThat(match.getMatchStatus()).isEqualTo(SECOND_PLAYER_WIN);
    }

    @Test
    public void shouldNotChangeMatchStatus_whenWinSetNumberIsNot3() {
        match.setSecondPlayerWinSetNumber(1);

        winSetGameMatchRule.validate(match);

        assertThat(match.getMatchStatus()).isEqualTo(IN_PROGRESS);
    }
}