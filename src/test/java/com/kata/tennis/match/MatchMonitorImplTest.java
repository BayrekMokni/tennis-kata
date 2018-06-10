package com.kata.tennis.match;

import com.kata.tennis.exception.MatchAlreadyOverRuntimeException;
import com.kata.tennis.exception.NoPlayerFoundRuntimeException;
import com.kata.tennis.player.Player;
import com.kata.tennis.set.SetTennisGameMonitorImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.kata.tennis.TennisTestUtil.getFirstPlayer;
import static com.kata.tennis.TennisTestUtil.getSecondPlayer;
import static com.kata.tennis.game.GameScore.FORTY_ZERO;
import static com.kata.tennis.game.GameScore.ZERO_FORTY;
import static com.kata.tennis.match.MatchStatus.FIRST_PLAYER_WIN;
import static com.kata.tennis.match.MatchStatus.IN_PROGRESS;
import static com.kata.tennis.match.MatchStatus.SECOND_PLAYER_WIN;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class MatchMonitorImplTest {

    @InjectMocks
    private MatchMonitorImpl matchMonitor;

    private SetTennisGameMonitorImpl setTennisGameMonitor = new SetTennisGameMonitorImpl();

    @Test
    public void checkScoresShouldEndTheMatchWhenFirstPlayerWin() {
        Player michael = getFirstPlayer("Michael");
        Player david = getSecondPlayer("David");
        Match match = new MatchBuilder()
                .betweenPlayers(michael, david)
                .build();
        match.start();
        match.getSets().getLast().getGame().setScore(FORTY_ZERO);
        match.getSets().getLast().setScoreFirstPlayer(5);
        match.getSets().getLast().setScoreSecondPlayer(0);
        matchMonitor.setMatch(match);
        matchMonitor.setSetTennisGameMonitor(setTennisGameMonitor);
        match.setFirstPlayerWinSetNumber(2);

        matchMonitor.checkScores(1);

        assertThat(match.getMatchStatus()).isEqualTo(FIRST_PLAYER_WIN);
    }

    @Test
    public void checkScoresShouldEndTheMatchWhenSecondPlayerWin() {
        Player michael = getFirstPlayer("Michael");
        Player david = getSecondPlayer("David");
        Match match = new MatchBuilder()
                .betweenPlayers(michael, david)
                .build();
        match.start();
        match.getSets().getLast().getGame().setScore(ZERO_FORTY);
        match.getSets().getLast().setScoreFirstPlayer(3);
        match.getSets().getLast().setScoreSecondPlayer(5);
        matchMonitor.setMatch(match);
        matchMonitor.setSetTennisGameMonitor(setTennisGameMonitor);
        match.setSecondPlayerWinSetNumber(2);

        matchMonitor.checkScores(2);

        assertThat(match.getMatchStatus()).isEqualTo(SECOND_PLAYER_WIN);
    }

    @Test
    public void checkScoresShouldNotEndTheMatch() {
        Player michael = getFirstPlayer("Michael");
        Player david = getSecondPlayer("David");
        Match match = new MatchBuilder()
                .betweenPlayers(michael, david)
                .build();
        match.start();
        match.getSets().getLast().getGame().setScore(FORTY_ZERO);
        match.getSets().getLast().setScoreFirstPlayer(5);
        match.getSets().getLast().setScoreSecondPlayer(0);
        matchMonitor.setMatch(match);
        matchMonitor.setSetTennisGameMonitor(setTennisGameMonitor);
        match.setFirstPlayerWinSetNumber(1);

        matchMonitor.checkScores(1);

        assertThat(match.getMatchStatus()).isEqualTo(IN_PROGRESS);
    }

    @Test(expected = MatchAlreadyOverRuntimeException.class)
    public void checkScoresShouldThrowMatchAlreadyOverRuntimeException() {
        Player michael = getFirstPlayer("Michael");
        Player david = getSecondPlayer("David");
        Match match = new MatchBuilder()
                .betweenPlayers(michael, david)
                .build();
        match.setMatchStatus(FIRST_PLAYER_WIN);
        matchMonitor.setMatch(match);

        matchMonitor.checkScores(1);
    }

    @Test(expected = NoPlayerFoundRuntimeException.class)
    public void checkScoresShouldThrowNoPlayerFoundRuntimeException() {
        Player michael = getFirstPlayer("Michael");
        Player david = getSecondPlayer("David");
        Match match = new MatchBuilder()
                .betweenPlayers(michael, david)
                .build();
        match.setMatchStatus(IN_PROGRESS);
        matchMonitor.setMatch(match);

        matchMonitor.checkScores(5);
    }
}