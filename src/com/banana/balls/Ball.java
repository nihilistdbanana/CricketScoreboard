package com.banana.balls;

import com.banana.balls.BallStrategies.BallStrategyInterface;
import com.banana.player.Player;

public abstract class Ball implements BallStrategyInterface {

    protected BallTypeEnum ballType;

    protected int score;
    protected boolean four;
    protected boolean six;
    protected boolean ball;
    protected boolean extra;

    private Ball() {
    }

    protected Ball(BallTypeEnum ballType) {
        this.ballType = ballType;
    }

    @Override
    public void updatePlayer(Player player) {
        player.incScore(score);
        if (four) {
            player.incFours();
        }
        if (six) {
            player.incSixes();
        }
        if (ball) {
            player.incBalls();
        }
        if (extra) {
            player.incExtras();
        }
    }

    public boolean isBall() {
        return ball;
    }


    public BallTypeEnum getBallType() {
        return ballType;
    }

    public int getScore() {
        return score;
    }
}
