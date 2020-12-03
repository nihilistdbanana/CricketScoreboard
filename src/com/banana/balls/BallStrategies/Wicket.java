package com.banana.balls.BallStrategies;

import com.banana.balls.Ball;
import com.banana.balls.BallTypeEnum;

public class Wicket extends Ball {
    public Wicket(String ballStr) {
        super(BallTypeEnum.getBallTypeFromLabel(ballStr));
        score = 0;
        four = false;
        six = false;
        ball = true;
        extra = false;
    }
}
