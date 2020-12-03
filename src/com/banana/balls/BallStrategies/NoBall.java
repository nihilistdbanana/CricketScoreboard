package com.banana.balls.BallStrategies;

import com.banana.balls.Ball;
import com.banana.balls.BallTypeEnum;

public class NoBall extends Ball {
    public NoBall(String ballStr) {
        super(BallTypeEnum.getBallTypeFromLabel(ballStr));
        score = 0;
        four = false;
        six = false;
        ball = false;
        extra = true;
    }


}
