package com.kata.tennis.set;

import com.kata.tennis.game.Game;

public class SetTennisGameBuilder {

    private SetTennisGame setTennisGame;

    public SetTennisGameBuilder() {
        this.setTennisGame = new SetTennisGame();
    }

    public SetTennisGameBuilder withGame(Game game) {
        setTennisGame.setGame(game);
        return this;
    }

    public SetTennisGame build() {
        return setTennisGame.setScoresToZero().setInProgress(false);
    }

    public SetTennisGame buildAndStart() {
        return setTennisGame.setScoresToZero().setInProgress(true);
    }
}
