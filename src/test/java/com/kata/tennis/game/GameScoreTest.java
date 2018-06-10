package com.kata.tennis.game;

import org.junit.Test;

import static com.kata.tennis.game.GameScore.*;
import static org.assertj.core.api.Assertions.assertThat;
public class GameScoreTest {

    @Test
    public void getNextZERO_ALL__ShouldReturnFIFTEEN_ZERO() {
        GameScore nextScore = ZERO_ALL.getNext(1);
        assertThat(nextScore).isEqualTo(FIFTEEN_ZERO);
    }
    @Test
    public void getNextZERO_ALL__ShouldReturnZERO_FIFTEEN() {
        GameScore nextScore = ZERO_ALL.getNext(2);
        assertThat(nextScore).isEqualTo(ZERO_FIFTEEN);
    }

    @Test
    public void getNextFIFTEEN_ZERO__ShouldReturnTHIRTY_ZERO() {
        GameScore nextScore = FIFTEEN_ZERO.getNext(1);
        assertThat(nextScore).isEqualTo(THIRTY_ZERO);
    }
    @Test
    public void getNextFIFTEEN_ZERO__ShouldReturnFIFTEEN_ALL() {
        GameScore nextScore = FIFTEEN_ZERO.getNext(2);
        assertThat(nextScore).isEqualTo(FIFTEEN_ALL);
    }

    @Test
    public void getNextZERO_FIFTEEN__ShouldReturnZERO_THIRTY() {
        GameScore nextScore = ZERO_FIFTEEN.getNext(1);
        assertThat(nextScore).isEqualTo(ZERO_THIRTY);
    }
    @Test
    public void getNextZERO_FIFTEEN__ShouldReturnFIFTEEN_ALL() {
        GameScore nextScore = ZERO_FIFTEEN.getNext(2);
        assertThat(nextScore).isEqualTo(FIFTEEN_ALL);
    }

    @Test
    public void getNextTHIRTY_ZERO__ShouldReturnFORTY_ZERO() {
        GameScore nextScore = THIRTY_ZERO.getNext(1);
        assertThat(nextScore).isEqualTo(FORTY_ZERO);
    }
    @Test
    public void getNextTHIRTY_ZERO__ShouldReturnTHIRTY_FIFTEE() {
        GameScore nextScore = THIRTY_ZERO.getNext(2);
        assertThat(nextScore).isEqualTo(THIRTY_FIFTEEN);
    }

    @Test
    public void getNextZERO_THIRTY__ShouldReturnFIFTEEN_THIRTY() {
        GameScore nextScore = ZERO_THIRTY.getNext(1);
        assertThat(nextScore).isEqualTo(FIFTEEN_THIRTY);
    }
    @Test
    public void getNextZERO_THIRTY__ShouldReturnZERO_FORTY() {
        GameScore nextScore = ZERO_THIRTY.getNext(2);
        assertThat(nextScore).isEqualTo(ZERO_FORTY);
    }

    @Test
    public void getNextZERO_FORTY__ShouldReturnFIFTEEN_THIRTY() {
        GameScore nextScore = ZERO_FORTY.getNext(1);
        assertThat(nextScore).isEqualTo(FIFTEEN_FORTY);
    }
    @Test
    public void getNextZERO_FORTY__ShouldReturnZERO_FORTY() {
        GameScore nextScore = ZERO_FORTY.getNext(2);
        assertThat(nextScore).isEqualTo(SECOND_PLAYER_WIN);
    }

    @Test
    public void getNextFORTY_ZERO__ShouldReturnFIRST_PLAYER_WIN() {
        GameScore nextScore = FORTY_ZERO.getNext(1);
        assertThat(nextScore).isEqualTo(FIRST_PLAYER_WIN);
    }
    @Test
    public void getNextFORTY_ZERO__ShouldReturnFORTY_FIFTEEN() {
        GameScore nextScore = FORTY_ZERO.getNext(2);
        assertThat(nextScore).isEqualTo(FORTY_FIFTEEN);
    }

    @Test
    public void getNextFIFTEEN_ALL__ShouldReturnTHIRTY_FIFTEEN() {
        GameScore nextScore = FIFTEEN_ALL.getNext(1);
        assertThat(nextScore).isEqualTo(THIRTY_FIFTEEN);
    }
    @Test
    public void getNextFIFTEEN_ALL__ShouldReturnFIFTEEN_THIRTY() {
        GameScore nextScore = FIFTEEN_ALL.getNext(2);
        assertThat(nextScore).isEqualTo(FIFTEEN_THIRTY);
    }

    @Test
    public void getNextFIFTEEN_ALL__ShouldReturnTHIRTY_ALL() {
        GameScore nextScore = FIFTEEN_ALL.getNext(1);
        assertThat(nextScore).isEqualTo(THIRTY_FIFTEEN);
    }
    @Test
    public void getNextFIFTEEN_ALL__ShouldReturnFIFTEEN_FORTY() {
        GameScore nextScore = FIFTEEN_ALL.getNext(2);
        assertThat(nextScore).isEqualTo(FIFTEEN_THIRTY);
    }

    @Test
    public void getNextTHIRTY_FIFTEEN__ShouldReturnFORTY_FIFTEEN() {
        GameScore nextScore = THIRTY_FIFTEEN.getNext(1);
        assertThat(nextScore).isEqualTo(FORTY_FIFTEEN);
    }
    @Test
    public void getNextTHIRTY_FIFTEEN__ShouldReturnTHIRTY_ALL() {
        GameScore nextScore = THIRTY_FIFTEEN.getNext(2);
        assertThat(nextScore).isEqualTo(THIRTY_ALL);
    }

    @Test
    public void getNextTHIRTY_ALL__ShouldReturnFORTY_THIRTY() {
        GameScore nextScore = THIRTY_ALL.getNext(1);
        assertThat(nextScore).isEqualTo(FORTY_THIRTY);
    }
    @Test
    public void getNextTHIRTY_ALL__ShouldReturnTHIRTY_FORTY() {
        GameScore nextScore = THIRTY_ALL.getNext(2);
        assertThat(nextScore).isEqualTo(THIRTY_FORTY);
    }

    @Test
    public void getNextTHIRTY_FORTY__ShouldReturnFORTY_ALLWhenScore() {
        GameScore nextScore = THIRTY_FORTY.getNext(1);
        assertThat(nextScore).isEqualTo(FORTY_ALL);
    }
    @Test
    public void getNextTHIRTY_FORTY__ShouldReturnSECOND_PLAYER_WIN() {
        GameScore nextScore = THIRTY_FORTY.getNext(2);
        assertThat(nextScore).isEqualTo(SECOND_PLAYER_WIN);
    }

    @Test
    public void getNextFORTY_THIRTY__ShouldReturnFIRST_PLAYER_WIN() {
        GameScore nextScore = FORTY_THIRTY.getNext(1);
        assertThat(nextScore).isEqualTo(FIRST_PLAYER_WIN);
    }
    @Test
    public void getNextFORTY_THIRTY__ShouldReturnFORTY_ALL() {
        GameScore nextScore = FORTY_THIRTY.getNext(2);
        assertThat(nextScore).isEqualTo(FORTY_ALL);
    }

    @Test
    public void getNextFIFTEEN_FORTY__ShouldReturnTHIRTY_FORTY() {
        GameScore nextScore = FIFTEEN_FORTY.getNext(1);
        assertThat(nextScore).isEqualTo(THIRTY_FORTY);
    }
    @Test
    public void getNextFIFTEEN_FORTY__ShouldReturnSECOND_PLAYER_WIN() {
        GameScore nextScore = FIFTEEN_FORTY.getNext(2);
        assertThat(nextScore).isEqualTo(SECOND_PLAYER_WIN);
    }

    @Test
    public void getNextFORTY_FIFTEEN__ShouldReturnFIRST_PLAYER_WIN() {
        GameScore nextScore = FORTY_FIFTEEN.getNext(1);
        assertThat(nextScore).isEqualTo(FIRST_PLAYER_WIN);
    }
    @Test
    public void getNextFORTY_FIFTEEN__ShouldReturnFORTY_THIRTY() {
        GameScore nextScore = FORTY_FIFTEEN.getNext(2);
        assertThat(nextScore).isEqualTo(FORTY_THIRTY);
    }

    @Test
    public void getNextFORTY_ALL__ShouldReturnFIRST_PLAYER_ADVANTAGE() {
        GameScore nextScore = FORTY_ALL.getNext(1);
        assertThat(nextScore).isEqualTo(FIRST_PLAYER_ADVANTAGE);
    }
    @Test
    public void getNextFORTY_ALL__ShouldReturnSECOND_PLAYER_ADVANTAGE() {
        GameScore nextScore = FORTY_ALL.getNext(2);
        assertThat(nextScore).isEqualTo(SECOND_PLAYER_ADVANTAGE);
    }

    @Test
    public void getNextFIRST_PLAYER_ADVANTAGE__ShouldReturnFIRST_PLAYER_WIN() {
        GameScore nextScore = FIRST_PLAYER_ADVANTAGE.getNext(1);
        assertThat(nextScore).isEqualTo(FIRST_PLAYER_WIN);
    }
    @Test
    public void getNextFIRST_PLAYER_ADVANTAGE__ShouldReturnFORTY_ALL() {
        GameScore nextScore = FIRST_PLAYER_ADVANTAGE.getNext(2);
        assertThat(nextScore).isEqualTo(FORTY_ALL);
    }

    @Test
    public void getNextSECOND_PLAYER_ADVANTAGE__ShouldReturnFORTY_ALL() {
        GameScore nextScore = SECOND_PLAYER_ADVANTAGE.getNext(1);
        assertThat(nextScore).isEqualTo(FORTY_ALL);
    }
    @Test
    public void getNextSECOND_PLAYER_ADVANTAGE__ShouldReturnSECOND_PLAYER_WIN() {
        GameScore nextScore = SECOND_PLAYER_ADVANTAGE.getNext(2);
        assertThat(nextScore).isEqualTo(SECOND_PLAYER_WIN);
    }

    @Test
    public void getNextFIRST_PLAYER_WIN__ShouldReturnNULL__WhenPlayer1() {
        GameScore nextScore = FIRST_PLAYER_WIN.getNext(1);
        assertThat(nextScore).isNull();
    }
    @Test
    public void getNextFIRST_PLAYER_WIN__ShouldReturnNULL__WhenPlayer2() {
        GameScore nextScore = FIRST_PLAYER_WIN.getNext(2);
        assertThat(nextScore).isNull();
    }
    @Test
    public void getNextSECOND_PLAYER_WIN__ShouldReturnNULL__WhenPlayer1() {
        GameScore nextScore = SECOND_PLAYER_WIN.getNext(1);
        assertThat(nextScore).isNull();
    }
    @Test
    public void getNextSECOND_PLAYER_WIN__ShouldReturnNULL__WhenPlayer2() {
        GameScore nextScore = SECOND_PLAYER_WIN.getNext(2);
        assertThat(nextScore).isNull();
    }
}
