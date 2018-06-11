package com.kata.tennis.rule.set;

import com.kata.tennis.game.GameScore;
import com.kata.tennis.set.SetTennisGame;
import org.springframework.stereotype.Component;

import static com.kata.tennis.game.GameScore.*;

/**
 * This implementation contains rules to win a set.
 * This will be called each time one player win a point.
 */

@Component
public class SetRuleImpl implements SetRule {

    @Override
    public void validate(GameScore newScore, SetTennisGame setTennisGame) {
        if (FIRST_PLAYER_WIN.equals(newScore)) {
            setTennisGame.increaseFirstPlayerSetScore();
            checkSetStatus(setTennisGame);
        } else if (SECOND_PLAYER_WIN.equals(newScore)) {
            setTennisGame.increaseSecondPlayerSetScore();
            checkSetStatus(setTennisGame);
        } else {
            setTennisGame.getGame().setScore(newScore);
        }
    }

    private void checkSetStatus(SetTennisGame setTennisGame) {
        setTennisGame.getGame().setScore(ZERO_ALL);
        if (isSixOrGreaterScore(setTennisGame) && isDiff2(setTennisGame)) {
            setTennisGame.setInProgress(false);
        }
    }

    private boolean isSixOrGreaterScore(SetTennisGame setTennisGame) {
        return (setTennisGame.getScoreFirstPlayer() >= 6) || (setTennisGame.getScoreSecondPlayer() >= 6);
    }

    private boolean isDiff2(SetTennisGame setTennisGame) {
        return (setTennisGame.getScoreFirstPlayer() - setTennisGame.getScoreSecondPlayer() >= 2)
                || (setTennisGame.getScoreSecondPlayer() - setTennisGame.getScoreFirstPlayer() >= 2);
    }
}
