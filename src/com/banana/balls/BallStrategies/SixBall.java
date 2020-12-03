package com.banana.balls.BallStrategies;

import com.banana.balls.Ball;
import com.banana.balls.BallTypeEnum;

public class SixBall extends Ball {
    public SixBall(String ballStr) {
        super(BallTypeEnum.getBallTypeFromLabel(ballStr));
        score = Integer.parseInt(ballStr);
        four = false;
        six = true;
        ball = true;
        extra = false;
    }
}
