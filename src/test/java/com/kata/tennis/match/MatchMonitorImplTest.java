package com.kata.tennis.match;

import com.kata.tennis.match.impl.MatchMonitorImpl;
import com.kata.tennis.observator.MatchObservable;
import com.kata.tennis.observator.MatchObserver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
public class MatchMonitorImplTest {

    @InjectMocks
    private MatchMonitorImpl matchMonitor;

    @Mock
    private MatchObservable matchObservable;

    @Mock
    private Match match;

    private MatchObserver matchObserver;

    @Test
    public void checkScoresShouldNotifyObservers() {
        Integer playerId = 1;
        matchMonitor.markPoint(playerId);

        verify(matchObservable, times(1)).notifyObservers(playerId, match);
    }

    @Test
    public void addObserverShouldAddObserver() {
        matchMonitor.addObserver(matchObserver);

        verify(matchObservable, times(1)).addObserver(matchObserver);
    }
}