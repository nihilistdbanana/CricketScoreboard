package com.banana.MatchMetaData;

import com.banana.balls.Ball;

import java.util.Observable;

public class BallPublisher extends Observable {

    public void notifyObservers(Ball ball) {
        this.setChanged();
        super.notifyObservers(ball);
    }

}
