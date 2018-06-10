package com.kata.tennis;

import com.kata.tennis.match.Match;
import com.kata.tennis.match.MatchBuilder;
import com.kata.tennis.match.impl.MatchMonitorImpl;
import com.kata.tennis.observator.MatchObservable;
import com.kata.tennis.observator.impl.MatchObservableImpl;
import com.kata.tennis.observator.impl.MatchObserverImpl;
import com.kata.tennis.player.Player;
import com.kata.tennis.player.PlayerBuilder;
import com.kata.tennis.set.SetTennisGame;
import com.kata.tennis.set.SetTennisGameMonitorImpl;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

import static com.kata.tennis.match.MatchStatus.IN_PROGRESS;
import static com.kata.tennis.player.RandomPlayer.randomPlayer;

@Component
public class TennisSimulator {

    public void run() {
        Player firstPlayer = getFirstPlayer("Michael");
        Player secondPlayer = getSecondPlayer("David");
        Match match = getMatch(firstPlayer, secondPlayer);

        MatchMonitorImpl matchMonitor = new MatchMonitorImpl();
        MatchObservable matchObservable = new MatchObservableImpl();
        matchMonitor.setMatchObservable(matchObservable);
        matchMonitor.addObserver(new MatchObserverImpl().setSetTennisGameMonitor(new SetTennisGameMonitorImpl()));
        matchMonitor.setMatch(match);
        match.start();

        System.out.print("############## Tennis-Game output ############## \n");
        do {
            Integer playerId = randomPlayer(firstPlayer, secondPlayer).getId();
            matchMonitor.markPoint(playerId);
            print(match);
        } while (match.getMatchStatus().isInProgress());
        System.out.print("\n############## END OUTPUT ############## \n");
    }

    private void print(Match match) {
        System.out.print("\n");
        System.out.print("Player  1 : " + match.getSets().getLast().getGame().getFirstPlayer().getName() + "\n");
        System.out.print("Player  2 : " + match.getSets().getLast().getGame().getSecondPlayer().getName() + "\n");
        System.out.print("Score : ");
        System.out.print(printSets(match.getSets()) + "\n");
        if (match.getMatchStatus().equals(IN_PROGRESS)) {
            System.out.print("Current game status : " + match.getSets().getLast().getGame().getScore().getValue() + "\n");
        }
        System.out.print("Match Status : " + match.getMatchStatus().getDescription() + "\n");
    }

    private String printSets(LinkedList<SetTennisGame> sets) {
        sets.forEach(setTennisGame -> System.out.print("(" + setTennisGame.getScoreFirstPlayer().toString() + " - "
                + setTennisGame.getScoreSecondPlayer().toString() + ")"));
        return "";
    }

    private Match getMatch(Player firstPlayer, Player secondPlayer) {
        return new MatchBuilder()
                .betweenPlayers(firstPlayer, secondPlayer)
                .build();
    }

    private Player getSecondPlayer(String value) {
        return new PlayerBuilder().withName(value)
                .buildSecondPlayer();
    }

    private Player getFirstPlayer(String value) {
        return new PlayerBuilder().withName(value)
                .buildFirstPlayer();
    }
}
