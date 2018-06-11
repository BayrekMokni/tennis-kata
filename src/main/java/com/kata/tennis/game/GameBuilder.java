package com.kata.tennis.game;

import com.kata.tennis.player.Player;

import static com.kata.tennis.game.GameScore.ZERO_ALL;

public class GameBuilder {

    private Game game;

    public GameBuilder() {
        this.game = new Game();
    }

    public GameBuilder withFirstPlayer(Player player) {
        game.setFirstPlayer(player);
        return this;
    }

    public GameBuilder withSecondPlayer(Player player) {
        game.setSecondPlayer(player);
        return this;
    }

    public Game build() {
        return game
                .setInProgress(true)
                .setScore(ZERO_ALL);
    }
}
