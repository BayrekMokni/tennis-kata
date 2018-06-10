package com.kata.tennis.match;

import com.kata.tennis.exception.MatchAlreadyOverRuntimeException;
import com.kata.tennis.exception.NoPlayerFoundRuntimeException;
import com.kata.tennis.game.GameScore;
import com.kata.tennis.player.Player;
import com.kata.tennis.set.SetTennisGameMonitorImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.kata.tennis.TennisTestUtil.*;
import static com.kata.tennis.game.GameScore.FORTY_ZERO;
import static com.kata.tennis.game.GameScore.ZERO_FORTY;
import static com.kata.tennis.match.MatchStatus.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class MatchMonitorImplTest {

    @InjectMocks
    private MatchMonitorImpl matchMonitor;

    private SetTennisGameMonitorImpl setTennisGameMonitor = new SetTennisGameMonitorImpl();
    private Match match;

    @Before
    public void setUp() {
        Player michael = getFirstPlayer("Michael");
        Player david = getSecondPlayer("David");
        match = getAndStartMatch(michael, david);
    }

    @Test
    public void checkScoresShouldEndTheMatchWhenFirstPlayerWin() {
        setLastSetData(FORTY_ZERO, 5, 0);
        match.setFirstPlayerWinSetNumber(2);

        matchMonitor.checkScores(1);

        assertThat(match.getMatchStatus()).isEqualTo(FIRST_PLAYER_WIN);
    }

    @Test
    public void checkScoresShouldEndTheMatchWhenSecondPlayerWin() {
        setLastSetData(ZERO_FORTY, 3, 5);
        match.setSecondPlayerWinSetNumber(2);

        matchMonitor.checkScores(2);

        assertThat(match.getMatchStatus()).isEqualTo(SECOND_PLAYER_WIN);
    }

    @Test
    public void checkScoresShouldNotEndTheMatch() {
        setLastSetData(FORTY_ZERO, 5, 0);
        match.setFirstPlayerWinSetNumber(1);

        matchMonitor.checkScores(1);

        assertThat(match.getMatchStatus()).isEqualTo(IN_PROGRESS);
    }

    @Test(expected = MatchAlreadyOverRuntimeException.class)
    public void checkScoresShouldThrowMatchAlreadyOverRuntimeException() {
        match.setMatchStatus(FIRST_PLAYER_WIN);
        matchMonitor.setMatch(match);

        matchMonitor.checkScores(1);
    }

    @Test(expected = NoPlayerFoundRuntimeException.class)
    public void checkScoresShouldThrowNoPlayerFoundRuntimeException() {
        matchMonitor.setMatch(match);

        matchMonitor.checkScores(5);
    }

    private void setLastSetData(GameScore value, Integer scoreFirstPlayer, Integer scoreSecondPlayer) {
        match.getSets().getLast().getGame().setScore(value);
        match.getSets().getLast().setScoreFirstPlayer(scoreFirstPlayer);
        match.getSets().getLast().setScoreSecondPlayer(scoreSecondPlayer);
        matchMonitor.setMatch(match);
        matchMonitor.setSetTennisGameMonitor(setTennisGameMonitor);
    }
}