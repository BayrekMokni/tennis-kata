package com.kata.tennis.set;

import com.kata.tennis.game.Game;
import com.kata.tennis.game.GameScore;
import com.kata.tennis.player.Player;
import com.kata.tennis.rule.set.SetRule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.kata.tennis.TennisTestUtil.*;
import static com.kata.tennis.game.GameScore.FIRST_PLAYER_WIN;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
public class SetTennisGameMonitorImplTest {

    @InjectMocks
    private SetTennisGameMonitorImpl setTennisGameMonitor;

    @Mock
    private SetTennisGame setTennisGame;

    @Mock
    private SetRule setRule;

    @Before
    public void setUp() {
        Player michael = getFirstPlayer("Michael");
        Player david = getSecondPlayer("David");
        Game game = getGameBetween(michael, david);
        setTennisGame = getAndStartSet(game);
        setTennisGameMonitor.setSetTennisGame(setTennisGame);
    }

    @Test
    public void shouldValidateSetRules_whenPlayerScoreIsGreaterThanSixAndDiffIsTwo() {
        setGameScoreToFORTY_FIFTEEN();

        setTennisGameMonitor.winPoint(1);

        verify(setRule, times(1)).validate(FIRST_PLAYER_WIN, setTennisGame);
    }

    private Game setGameScoreToFORTY_FIFTEEN() {
        return setTennisGameMonitor.getSetTennisGame().getGame().setScore(GameScore.FORTY_FIFTEEN);
    }
}
