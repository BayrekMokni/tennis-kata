package com.kata.tennis.rule.set;

import com.kata.tennis.game.Game;
import com.kata.tennis.player.Player;
import com.kata.tennis.set.SetTennisGame;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static com.kata.tennis.TennisTestUtil.*;
import static com.kata.tennis.game.GameScore.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class SetRuleImplTest {

    @InjectMocks
    private SetRuleImpl setRuleImpl;

    private SetTennisGame setTennisGame;

    @Before
    public void setUp() {
        Player michael = getFirstPlayer("Michael");
        Player david = getSecondPlayer("David");
        Game game = getGameBetween(michael, david);
        setTennisGame = getAndStartSet(game);
    }

    @Test
    public void validateShouldEndTheSetWhenFirstPlayerScoreIsGreaterThanSixAndDiffIsTwo() {
        Integer firstPlayerScore = 5;
        setPlayersScoresSuccessivelyTo(firstPlayerScore, 0);

        setRuleImpl.validate(FIRST_PLAYER_WIN, setTennisGame);

        assertThat(setTennisGame.getScoreFirstPlayer()).isEqualTo(firstPlayerScore + 1);
        assertThat(setTennisGame.getGame().getScore()).isEqualTo(ZERO_ALL);
        assertThat(setTennisGame.isInProgress()).isFalse();
    }

    @Test
    public void validateShouldEndTheSetWhenSecondPlayerScoreIsGreaterThanSixAndDiffIsTwo() {
        Integer secondPlayerScore = 5;
        setPlayersScoresSuccessivelyTo(4, secondPlayerScore);

        setRuleImpl.validate(SECOND_PLAYER_WIN, setTennisGame);

        assertThat(setTennisGame.getScoreSecondPlayer()).isEqualTo(secondPlayerScore + 1);
        assertThat(setTennisGame.getGame().getScore()).isEqualTo(ZERO_ALL);
        assertThat(setTennisGame.isInProgress()).isFalse();
    }

    @Test
    public void validateShouldNotEndTheSetWhenScoreIsNotGreaterThanSix() {
        Integer secondPlayerScore = 3;
        setPlayersScoresSuccessivelyTo(3, secondPlayerScore);

        setRuleImpl.validate(SECOND_PLAYER_WIN, setTennisGame);

        assertThat(setTennisGame.getScoreSecondPlayer()).isEqualTo(secondPlayerScore + 1);
        assertThat(setTennisGame.getGame().getScore()).isEqualTo(ZERO_ALL);
        assertThat(setTennisGame.isInProgress()).isTrue();
    }

    @Test
    public void validateShouldNotEndTheSetAndGetNewScoreWhenScoreIsNotGreaterThanSix() {
        Integer secondPlayerScore = 4;
        setPlayersScoresSuccessivelyTo(3, secondPlayerScore);

        setRuleImpl.validate(THIRTY_ALL, setTennisGame);

        assertThat(setTennisGame.getScoreSecondPlayer()).isEqualTo(secondPlayerScore);
        assertThat(setTennisGame.getGame().getScore()).isEqualTo(THIRTY_ALL);
        assertThat(setTennisGame.isInProgress()).isTrue();
    }

    private void setPlayersScoresSuccessivelyTo(Integer firstPlayerScore, Integer SecondPlayerScore) {
        setTennisGame.setScoreFirstPlayer(firstPlayerScore);
        setTennisGame.setScoreSecondPlayer(SecondPlayerScore);
    }
}