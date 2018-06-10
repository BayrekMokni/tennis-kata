package com.kata.tennis.game;

import com.kata.tennis.player.Player;

public class Game {

    private Player firstPlayer;
    private Player secondPlayer;
    private GameScore score;
    private boolean isInProgress;

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public Game setFirstPlayer(Player firstPlayer) {
        this.firstPlayer = firstPlayer;
        return this;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public Game setSecondPlayer(Player secondPlayer) {
        this.secondPlayer = secondPlayer;
        return this;
    }

    public GameScore getScore() {
        return score;
    }

    public Game setScore(GameScore score) {
        this.score = score;
        return this;
    }

    public boolean isInProgress() {
        return isInProgress;
    }

    public Game setInProgress(boolean inProgress) {
        isInProgress = inProgress;
        return this;
    }
}
