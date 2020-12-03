package com.banana.balls.BallStrategies;

import com.banana.balls.Ball;
import com.banana.balls.BallTypeEnum;

public class NormalBall extends Ball {
    public NormalBall(String ballStr) {
        super(BallTypeEnum.getBallTypeFromLabel(ballStr));
        score = Integer.parseInt(ballStr);
        four = false;
        six = false;
        ball = true;
        extra = false;
    }
}
