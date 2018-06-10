package com.kata.tennis.game;

public enum PlayerScore {

    SCORE_ZERO(0, "The score is 0"),
    SCORE_15(15, "The score is 15"),
    SCORE_30(30, "The score is 30"),
    SCORE_40(40, "The score is 40");

    Integer value;
    String description;

    PlayerScore(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public Integer getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
