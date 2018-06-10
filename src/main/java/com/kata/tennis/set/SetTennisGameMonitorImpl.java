package com.kata.tennis.set;

import com.kata.tennis.game.GameScore;
import org.springframework.stereotype.Service;

import static com.kata.tennis.game.GameScore.FIRST_PLAYER_WIN;
import static com.kata.tennis.game.GameScore.SECOND_PLAYER_WIN;

@Service
public class SetTennisGameMonitorImpl implements SetTennisGameMonitor {

    private SetTennisGame setTennisGame;

    public SetTennisGame getSetTennisGame() {
        return setTennisGame;
    }

    public SetTennisGameMonitorImpl setSetTennisGame(SetTennisGame setTennisGame) {
        this.setTennisGame = setTennisGame;
        return this;
    }

    @Override
    public void winPoint(Integer playerId) {
        GameScore newScore = setTennisGame.getGame().getScore().getNext(playerId);
        if (FIRST_PLAYER_WIN.equals(newScore)) {
            setTennisGame.increaseFirstPlayerSetScore();
            checkSetStatus();
        } else if (SECOND_PLAYER_WIN.equals(newScore)) {
            setTennisGame.increaseSecondPlayerSetScore();
            checkSetStatus();
        }
    }

    private void checkSetStatus() {
        if (isSixOrGreaterScore() && isDiff2()) {
            setTennisGame.setInProgress(false);
        }
    }

    private boolean isSixOrGreaterScore() {
        return (setTennisGame.getScoreFirstPlayer() >= 6) || (setTennisGame.getScoreSecondPlayer() >= 6);
    }

    private boolean isDiff2() {
        return (setTennisGame.getScoreFirstPlayer() - setTennisGame.getScoreSecondPlayer() >= 2)
                || (setTennisGame.getScoreSecondPlayer() - setTennisGame.getScoreFirstPlayer() >= 2);
    }

}
