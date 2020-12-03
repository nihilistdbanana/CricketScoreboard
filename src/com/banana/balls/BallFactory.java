package com.banana.balls;

import com.banana.balls.BallStrategies.*;

public class BallFactory {
    public static Ball getBall(BallTypeEnum ballType, String ballstr) {
        switch (ballType) {
            case NORMAL_BALL:
                return new NormalBall(ballstr);
            case FOUR:
                return new FourBall(ballstr);
            case SIX:
                return new SixBall(ballstr);
            case NO_BALL:
                return new NoBall(ballstr);
            case WIDE_BALL:
                return new WideBall(ballstr);
            case WICKET:
                return new Wicket(ballstr);
            default:
                throw new IllegalStateException("Unexpected value: " + ballType);
        }
    }
}
