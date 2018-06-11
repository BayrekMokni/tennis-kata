package com.kata.tennis.set;

import com.kata.tennis.game.Game;

public class SetTennisGame {

    private Integer scoreFirstPlayer;
    private Integer scoreSecondPlayer;
    private Game game;
    private boolean inProgress;

    public Integer getScoreFirstPlayer() {
        return scoreFirstPlayer;
    }

    public SetTennisGame setScoreFirstPlayer(Integer scoreFirstPlayer) {
        this.scoreFirstPlayer = scoreFirstPlayer;
        return this;
    }

    public Integer getScoreSecondPlayer() {
        return scoreSecondPlayer;
    }

    public SetTennisGame setScoreSecondPlayer(Integer scoreSecondPlayer) {
        this.scoreSecondPlayer = scoreSecondPlayer;
        return this;
    }

    public Game getGame() {
        return game;
    }

    public SetTennisGame setGame(Game game) {
        this.game = game;
        return this;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public SetTennisGame setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
        return this;
    }

    public SetTennisGame setScoresToZero() {
        this.scoreFirstPlayer = 0;
        this.scoreSecondPlayer = 0;
        return this;
    }

    public void increaseFirstPlayerSetScore() {
        scoreFirstPlayer = scoreFirstPlayer + 1;
    }

    public void increaseSecondPlayerSetScore() {
        scoreSecondPlayer = scoreSecondPlayer + 1;
    }
}
