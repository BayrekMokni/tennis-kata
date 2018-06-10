package com.kata.tennis.match;

import com.kata.tennis.set.SetTennisGame;

import java.util.LinkedList;

import static com.kata.tennis.match.MatchStatus.IN_PROGRESS;

public class Match {

    private MatchStatus matchStatus;
    private LinkedList<SetTennisGame> sets = new LinkedList<>();
    private Integer firstPlayerWinSetNumber;
    private Integer secondPlayerWinSetNumber;

    public LinkedList<SetTennisGame> getSets() {
        return sets;
    }

    public Match setSets(LinkedList<SetTennisGame> sets) {
        this.sets = sets;
        return this;
    }

    public MatchStatus getMatchStatus() {
        return matchStatus;
    }

    public Match setMatchStatus(MatchStatus matchStatus) {
        this.matchStatus = matchStatus;
        return this;
    }

    public Integer getFirstPlayerWinSetNumber() {
        return firstPlayerWinSetNumber;
    }

    public Match setFirstPlayerWinSetNumber(Integer firstPlayerWinSetNumber) {
        this.firstPlayerWinSetNumber = firstPlayerWinSetNumber;
        return this;
    }

    public Integer getSecondPlayerWinSetNumber() {
        return secondPlayerWinSetNumber;
    }

    public Match setSecondPlayerWinSetNumber(Integer secondPlayerWinSetNumber) {
        this.secondPlayerWinSetNumber = secondPlayerWinSetNumber;
        return this;
    }

    public void start() {
        setMatchStatus(IN_PROGRESS);
        sets.getLast().setInProgress(true);
    }

    public Integer increaseFirstPlayerWinSet() {
        return firstPlayerWinSetNumber = firstPlayerWinSetNumber + 1;
    }

    public Integer increaseSecondPlayerWinSet() {
        return secondPlayerWinSetNumber = secondPlayerWinSetNumber + 1;
    }
}
