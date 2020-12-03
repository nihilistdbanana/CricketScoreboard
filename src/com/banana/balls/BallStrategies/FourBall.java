package com.banana.balls.BallStrategies;

import com.banana.balls.Ball;
import com.banana.balls.BallTypeEnum;

public class FourBall extends Ball {
    public FourBall(String ballStr) {
        super(BallTypeEnum.getBallTypeFromLabel(ballStr));
        score = Integer.parseInt(ballStr);
        four = true;
        six = false;
        ball = true;
        extra = false;
    }
}
