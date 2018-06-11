package com.kata.tennis.player;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomPlayer {
    private static final Random RANDOM = new Random();

    public static Player randomPlayer(Player firstPlayer, Player secondPlayer) {
        List<Player> playerList = Arrays.asList(firstPlayer, secondPlayer);
        return playerList.get(RANDOM.ints(0, 2).findFirst().getAsInt());
    }
}
