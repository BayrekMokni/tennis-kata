package com.kata.tennis.match;

public enum  MatchStatus {

    IN_PROGRESS("In progress"){
        @Override
        public boolean isInProgress() {
            return true;
        }
    },
    FIRST_PLAYER_WIN("First player wins") {
        @Override
        public boolean isInProgress() {
            return false;
        }
    },
    SECOND_PLAYER_WIN("Second player wins") {
        @Override
        public boolean isInProgress() {
            return false;
        }
    };

    private String description;

    MatchStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public abstract boolean isInProgress();
}
