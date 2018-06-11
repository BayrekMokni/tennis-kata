package com.kata.tennis.player;

public class Player {

    public Integer id;
    public String name;

    public Integer getId() {
        return id;
    }

    public Player setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Player setName(String name) {
        this.name = name;
        return this;
    }
}
