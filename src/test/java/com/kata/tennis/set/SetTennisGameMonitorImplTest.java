package com.kata.tennis.set;

import com.kata.tennis.game.Game;
import com.kata.tennis.game.GameScore;
import com.kata.tennis.player.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static com.kata.tennis.TennisTestUtil.*;
import static com.kata.tennis.game.GameScore.FORTY_FIFTEEN;
import static com.kata.tennis.game.GameScore.THIRTY_FORTY;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class SetTennisGameMonitorImplTest {

    @InjectMocks
    private SetTennisGameMonitorImpl setTennisGameMonitor;

    @Before
    public void setUp() {
        Player michael = getFirstPlayer("Michael");
        Player david = getSecondPlayer("David");
        Game game = getGameBetween(michael, david);
        SetTennisGame setTennisGame = getAndStartSet(game);
        setTennisGameMonitor.setSetTennisGame(setTennisGame);
    }

    @Test
    public void winPointShouldEndTheSetWhenFirstPlayerScoreIsGreaterThanSixAndDiffIsTwo() {
        setPlayersScoresSuccessivelyTo(5, 0);
        setGameScore(FORTY_FIFTEEN);

        setTennisGameMonitor.winPoint(1);

        assertThat(setTennisGameMonitor.getSetTennisGame().isInProgress()).isFalse();
    }

    @Test
    public void winPointShouldEndTheSetWhenSecondPlayerScoreIsGreaterThanSixAndDiffIsTwo() {
        setPlayersScoresSuccessivelyTo(4, 5);
        setGameScore(THIRTY_FORTY);

        setTennisGameMonitor.winPoint(2);

        assertThat(setTennisGameMonitor.getSetTennisGame().isInProgress()).isFalse();
    }

    @Test
    public void winPointShouldNotEndTheSetWhenScoreIsNotGreaterThanSix() {
        setPlayersScoresSuccessivelyTo(3, 3);
        setGameScore(FORTY_FIFTEEN);

        setTennisGameMonitor.winPoint(1);

        assertThat(setTennisGameMonitor.getSetTennisGame().isInProgress()).isTrue();
    }

    private Game setGameScore(GameScore value) {
        return setTennisGameMonitor.getSetTennisGame().getGame().setScore(value);
    }

    private void setPlayersScoresSuccessivelyTo(Integer firstPlayerScore, Integer SecondPlayerScore) {
        setTennisGameMonitor.getSetTennisGame().setScoreFirstPlayer(firstPlayerScore);
        setTennisGameMonitor.getSetTennisGame().setScoreSecondPlayer(SecondPlayerScore);
    }
}
