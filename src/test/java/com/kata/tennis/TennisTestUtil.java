package com.kata.tennis;

import com.kata.tennis.game.Game;
import com.kata.tennis.game.GameBuilder;
import com.kata.tennis.player.Player;
import com.kata.tennis.player.PlayerBuilder;
import com.kata.tennis.set.SetTennisGame;
import com.kata.tennis.set.SetTennisGameBuilder;

public class TennisTestUtil {

    public static Player getFirstPlayer(String value) {
        return new PlayerBuilder().withName(value)
                .buildFirstPlayer();
    }

    public static Player getSecondPlayer(String value) {
        return new PlayerBuilder().withName(value)
                .buildSecondPlayer();
    }

    public static Game getGameBetween(Player firstPlayer, Player secondPlayer) {
        return new GameBuilder().withFirstPlayer(firstPlayer)
                .withSecondPlayer(secondPlayer)
                .build();
    }

    public static SetTennisGame getAndStartSet(Game game) {
        return new SetTennisGameBuilder()
                .withGame(game)
                .buildAndStart();
    }
}
