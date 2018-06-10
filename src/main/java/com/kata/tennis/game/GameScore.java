package com.kata.tennis.game;

import static com.kata.tennis.game.PlayerScore.*;

public enum GameScore {

    ZERO_ALL(SCORE_ZERO, SCORE_ZERO, "0-0") {
        @Override
        public GameScore getNext(Integer playerId) {
            return isFirstPlayerTheWinner(playerId) ? FIFTEEN_ZERO : ZERO_FIFTEEN;
        }
    },
    FIFTEEN_ZERO(SCORE_15, SCORE_ZERO, "15-0") {
        @Override
        public GameScore getNext(Integer winnerPlayerId) {
            return winnerPlayerId.equals(1) ? THIRTY_ZERO : FIFTEEN_ALL;
        }
    },
    ZERO_FIFTEEN(SCORE_ZERO, SCORE_15, "0-15") {
        @Override
        public GameScore getNext(Integer winnerPlayerId) {
            return isFirstPlayerTheWinner(winnerPlayerId) ? ZERO_THIRTY : FIFTEEN_ALL;
        }
    },

    THIRTY_ZERO(SCORE_30, SCORE_ZERO, "30-0") {
        @Override
        public GameScore getNext(Integer winnerPlayerId) {
            return isFirstPlayerTheWinner(winnerPlayerId) ? FORTY_ZERO : THIRTY_FIFTEEN;
        }
    },
    ZERO_THIRTY(SCORE_ZERO, SCORE_30, "0-30") {
        @Override
        public GameScore getNext(Integer winnerPlayerId) {
            return isFirstPlayerTheWinner(winnerPlayerId) ? FIFTEEN_THIRTY : ZERO_FORTY;
        }
    },

    ZERO_FORTY(SCORE_ZERO, SCORE_40, "0-40") {
        @Override
        public GameScore getNext(Integer winnerPlayerId) {
            return isFirstPlayerTheWinner(winnerPlayerId) ? FIFTEEN_FORTY : SECOND_PLAYER_WIN;
        }
    },
    FORTY_ZERO(SCORE_40, SCORE_ZERO, "40-0") {
        @Override
        public GameScore getNext(Integer winnerPlayerId) {
            return isFirstPlayerTheWinner(winnerPlayerId) ? FIRST_PLAYER_WIN : FORTY_FIFTEEN;
        }
    },

    FIFTEEN_ALL(SCORE_15, SCORE_15, "15-15") {
        @Override
        public GameScore getNext(Integer winnerPlayerId) {
            return isFirstPlayerTheWinner(winnerPlayerId) ? THIRTY_FIFTEEN : FIFTEEN_THIRTY;
        }
    },
    FIFTEEN_THIRTY(SCORE_15, SCORE_30, "15-30") {
        @Override
        public GameScore getNext(Integer winnerPlayerId) {
            return isFirstPlayerTheWinner(winnerPlayerId) ? THIRTY_ALL : FIFTEEN_FORTY;
        }
    },
    THIRTY_FIFTEEN(SCORE_30, SCORE_15, "30-15") {
        @Override
        public GameScore getNext(Integer winnerPlayerId) {
            return isFirstPlayerTheWinner(winnerPlayerId) ? FORTY_FIFTEEN : THIRTY_ALL;
        }
    },

    THIRTY_ALL(SCORE_30, SCORE_30, "30-30") {
        @Override
        public GameScore getNext(Integer winnerPlayerId) {
            return isFirstPlayerTheWinner(winnerPlayerId) ? FORTY_THIRTY : THIRTY_FORTY;
        }
    },
    THIRTY_FORTY(SCORE_30, SCORE_40, "30-40") {
        @Override
        public GameScore getNext(Integer winnerPlayerId) {
            return isFirstPlayerTheWinner(winnerPlayerId) ? FORTY_ALL : SECOND_PLAYER_WIN;
        }
    },
    FORTY_THIRTY(SCORE_40, SCORE_30, "40-30") {
        @Override
        public GameScore getNext(Integer winnerPlayerId) {
            return isFirstPlayerTheWinner(winnerPlayerId) ? FIRST_PLAYER_WIN : FORTY_ALL;
        }
    },

    FIFTEEN_FORTY(SCORE_15, SCORE_40, "15-40") {
        @Override
        public GameScore getNext(Integer winnerPlayerId) {
            return isFirstPlayerTheWinner(winnerPlayerId) ? THIRTY_FORTY : SECOND_PLAYER_WIN;
        }
    },
    FORTY_FIFTEEN(SCORE_40, SCORE_15, "40-15") {
        @Override
        public GameScore getNext(Integer winnerPlayerId) {
            return isFirstPlayerTheWinner(winnerPlayerId) ? FIRST_PLAYER_WIN : FORTY_THIRTY;
        }
    },
    FORTY_ALL(SCORE_40, SCORE_40, "Deuce") {
        @Override
        public GameScore getNext(Integer winnerPlayerId) {
            return isFirstPlayerTheWinner(winnerPlayerId) ? FIRST_PLAYER_ADVANTAGE : SECOND_PLAYER_ADVANTAGE;
        }
    },
    FIRST_PLAYER_ADVANTAGE(SCORE_40, SCORE_40, "Advantage") {
        @Override
        public GameScore getNext(Integer winnerPlayerId) {
            return isFirstPlayerTheWinner(winnerPlayerId) ? FIRST_PLAYER_WIN : FORTY_ALL;
        }
    },
    SECOND_PLAYER_ADVANTAGE(SCORE_40, SCORE_40, "Advantage") {
        @Override
        public GameScore getNext(Integer winnerPlayerId) {
            return isFirstPlayerTheWinner(winnerPlayerId) ? FORTY_ALL : SECOND_PLAYER_WIN;
        }
    },

    FIRST_PLAYER_WIN(null, null, null) {
        @Override
        public GameScore getNext(Integer winnerPlayerId) {
            return null;
        }
    },
    SECOND_PLAYER_WIN(null, null, null) {
        @Override
        public GameScore getNext(Integer winnerPlayerId) {
            return null;
        }
    };

    private PlayerScore firstPlayerScore;
    private PlayerScore secondPlayerScore;
    private String value;

    GameScore(PlayerScore firstPlayerScore, PlayerScore secondPlayerScore, String value) {
        this.firstPlayerScore = firstPlayerScore;
        this.secondPlayerScore = secondPlayerScore;
        this.value = value;
    }

    public PlayerScore getFirstPlayerScore() {
        return firstPlayerScore;
    }

    public PlayerScore getSecondPlayerScore() {
        return secondPlayerScore;
    }

    public String getValue() {
        return value;
    }

    private static boolean isFirstPlayerTheWinner(Integer winnerPlayerId) {
        return winnerPlayerId.equals(1);
    }

    // TODO: Can add a constraint @1Or2PlayerId
    public abstract GameScore getNext(Integer winnerPlayerId);
}
