package com.kata.tennis.match;

import com.kata.tennis.game.Game;
import com.kata.tennis.game.GameBuilder;
import com.kata.tennis.player.Player;
import com.kata.tennis.set.SetTennisGame;
import com.kata.tennis.set.SetTennisGameBuilder;

public class MatchBuilder {

    private Match match;

    public MatchBuilder() {
        this.match = new Match();
    }

    public MatchBuilder betweenPlayers(Player firstPlayer, Player secondPlayer) {
        Game game = new GameBuilder().withFirstPlayer(firstPlayer)
                .withSecondPlayer(secondPlayer)
                .build();
        SetTennisGame setTennisGame = new SetTennisGameBuilder()
                .withGame(game)
                .build();
        match.getSets().add(setTennisGame);
        return this;
    }

    public Match build() {
        return match
                .setFirstPlayerWinSetNumber(0)
                .setSecondPlayerWinSetNumber(0);
    }
}
