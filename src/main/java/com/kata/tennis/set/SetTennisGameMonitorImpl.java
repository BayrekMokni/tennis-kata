package com.kata.tennis.set;

import com.kata.tennis.game.GameScore;
import com.kata.tennis.rule.set.SetRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetTennisGameMonitorImpl implements SetTennisGameMonitor {

    @Autowired
    private SetRule setRule;

    private SetTennisGame setTennisGame;

    public SetTennisGame getSetTennisGame() {
        return setTennisGame;
    }

    public SetTennisGameMonitorImpl setSetTennisGame(SetTennisGame setTennisGame) {
        this.setTennisGame = setTennisGame;
        return this;
    }

    public SetRule getSetRule() {
        return setRule;
    }

    public SetTennisGameMonitorImpl setSetRule(SetRule setRule) {
        this.setRule = setRule;
        return this;
    }

    @Override
    public void winPoint(Integer playerId) {
        GameScore newScore = getCurrentScore().getNext(playerId);
        setRule.validate(newScore, setTennisGame);
    }

    private GameScore getCurrentScore() {
        return setTennisGame.getGame().getScore();
    }
}
