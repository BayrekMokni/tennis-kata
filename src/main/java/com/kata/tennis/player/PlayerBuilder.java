package com.kata.tennis.player;

import org.springframework.beans.factory.annotation.Autowired;

public class PlayerBuilder {

    @Autowired
    private Player player;

    public PlayerBuilder() {
        this.player = new Player();
    }

    public PlayerBuilder withId(Integer id) {
        player.setId(id);
        return this;
    }

    public PlayerBuilder withName(String name) {
        player.setName(name);
        return this;
    }

    public Player build() {
        return player;
    }

    public Player buildFirstPlayer() {
        return player.setId(1);
    }

    public Player buildSecondPlayer() {
        return player.setId(2);
    }
}
