package com.kata.tennis.set;

import com.kata.tennis.game.Game;
import com.kata.tennis.game.GameBuilder;
import com.kata.tennis.player.Player;
import com.kata.tennis.player.PlayerBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static com.kata.tennis.game.GameScore.FORTY_FIFTEEN;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class SetTennisGameMonitorImplTest {

    @InjectMocks
    private SetTennisGameMonitorImpl setTennisGameMonitor;

    @Test
    public void winPointShouldEndTheSetWhenScoreIsGreaterThanSixAndDiffIsTwo() {
        Player michael = new PlayerBuilder().withName("Michael")
                .buildFirstPlayer();
        Player david = new PlayerBuilder().withName("David")
                .buildSecondPlayer();
        Game game = new GameBuilder().withFirstPlayer(michael)
                .withSecondPlayer(david)
                .build();
        SetTennisGame setTennisGame = new SetTennisGameBuilder()
                .withGame(game)
                .buildAndStart();

        setTennisGameMonitor.setSetTennisGame(setTennisGame);
        setTennisGameMonitor.getSetTennisGame().setScoreFirstPlayer(5);
        setTennisGameMonitor.getSetTennisGame().setScoreSecondPlayer(0);
        setTennisGameMonitor.getSetTennisGame().getGame().setScore(FORTY_FIFTEEN);

        setTennisGameMonitor.winPoint(1);
        assertThat(setTennisGameMonitor.getSetTennisGame().isInProgress()).isFalse();
    }

    @Test
    public void winPointShouldNotEndTheSetWhenScoreIsNotGreaterThanSix() {
        Player michael = new PlayerBuilder().withName("Michael")
                .buildFirstPlayer();
        Player david = new PlayerBuilder().withName("David")
                .buildSecondPlayer();
        Game game = new GameBuilder().withFirstPlayer(michael)
                .withSecondPlayer(david)
                .build();
        SetTennisGame setTennisGame = new SetTennisGameBuilder()
                .withGame(game)
                .buildAndStart();

        setTennisGameMonitor.setSetTennisGame(setTennisGame);
        setTennisGameMonitor.getSetTennisGame().setScoreFirstPlayer(3);
        setTennisGameMonitor.getSetTennisGame().setScoreSecondPlayer(3);
        setTennisGameMonitor.getSetTennisGame().getGame().setScore(FORTY_FIFTEEN);

        setTennisGameMonitor.winPoint(1);
        assertThat(setTennisGameMonitor.getSetTennisGame().isInProgress()).isTrue();
    }
}