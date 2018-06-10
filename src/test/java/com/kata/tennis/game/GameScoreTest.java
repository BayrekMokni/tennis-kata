package com.kata.tennis.game;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.kata.tennis.game.GameScore.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(DataProviderRunner.class)
public class GameScoreTest {

    @DataProvider
    public static Object[][] getDataWhenFirstPlayerScorePoint() {
        return new Object[][]{
                {ZERO_ALL, FIFTEEN_ZERO},
                {FIFTEEN_ZERO, THIRTY_ZERO},
                {ZERO_FIFTEEN, ZERO_THIRTY},
                {THIRTY_ZERO, FORTY_ZERO},
                {ZERO_THIRTY, FIFTEEN_THIRTY},
                {ZERO_FORTY, FIFTEEN_FORTY},
                {FORTY_ZERO, FIRST_PLAYER_WIN},
                {FIFTEEN_ALL, THIRTY_FIFTEEN},
                {THIRTY_FIFTEEN, FORTY_FIFTEEN},
                {THIRTY_ALL, FORTY_THIRTY},
                {THIRTY_FORTY, FORTY_ALL},
                {FORTY_THIRTY, FIRST_PLAYER_WIN},
                {FIFTEEN_FORTY, THIRTY_FORTY},
                {FORTY_FIFTEEN, FIRST_PLAYER_WIN},
                {FORTY_ALL, FIRST_PLAYER_ADVANTAGE},
                {FIRST_PLAYER_ADVANTAGE, FIRST_PLAYER_WIN},
                {FIRST_PLAYER_WIN, null},
                {SECOND_PLAYER_WIN, null}
        };
    }

    @Test
    @UseDataProvider(value = "getDataWhenFirstPlayerScorePoint")
    public void getNextShouldReturnNewScoreWhenFirstPlayerScorePoint(GameScore initialScore, GameScore newScore) {
        GameScore nextScore = initialScore.getNext(1);
        assertThat(nextScore).isEqualTo(newScore);
    }

    @DataProvider
    public static Object[][] getDataWhenSecondPlayerScorePoint() {
        return new Object[][]{
                {ZERO_ALL, ZERO_FIFTEEN},
                {FIFTEEN_ZERO, FIFTEEN_ALL},
                {ZERO_FIFTEEN, FIFTEEN_ALL},
                {THIRTY_ZERO, THIRTY_FIFTEEN},
                {ZERO_THIRTY, ZERO_FORTY},
                {ZERO_FORTY, SECOND_PLAYER_WIN},
                {FORTY_ZERO, FORTY_FIFTEEN},
                {FIFTEEN_ALL, FIFTEEN_THIRTY},
                {THIRTY_FIFTEEN, THIRTY_ALL},
                {THIRTY_ALL, THIRTY_FORTY},
                {THIRTY_FORTY, SECOND_PLAYER_WIN},
                {FORTY_THIRTY, FORTY_ALL},
                {FIFTEEN_FORTY, SECOND_PLAYER_WIN},
                {FORTY_FIFTEEN, FORTY_THIRTY},
                {FORTY_ALL, SECOND_PLAYER_ADVANTAGE},
                {SECOND_PLAYER_ADVANTAGE, SECOND_PLAYER_WIN},
                {FIRST_PLAYER_WIN, null},
                {SECOND_PLAYER_WIN, null}
        };
    }

    @Test
    @UseDataProvider(value = "getDataWhenSecondPlayerScorePoint")
    public void getNextShouldReturnNewScoreWhenSecondPlayerScorePoint(GameScore initialScore, GameScore newScore) {
        GameScore nextScore = initialScore.getNext(2);
        assertThat(nextScore).isEqualTo(newScore);
    }
}
