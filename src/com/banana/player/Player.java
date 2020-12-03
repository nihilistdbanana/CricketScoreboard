package com.banana.player;

public class Player {
    public String getName() {
        return name;
    }

    private String name;
    private int score;
    private int fours;
    private int sixes;
    private int balls;
    private int extras;
    private boolean isCurrent;


    Player(String playerName) {
        name = playerName;
        score = 0;
        fours = 0;
        sixes = 0;
        balls = 0;
        extras = 0;
        isCurrent = false;
    }

    public int getScore() {
        return score;
    }

    public void incScore(int score) {
        this.score += score;
    }

    public int getFours() {
        return fours;
    }

    public void incFours() {
        this.fours++;
    }

    public int getSixes() {
        return sixes;
    }

    public void incSixes() {
        this.sixes++;
    }

    public int getBalls() {
        return balls;
    }

    public void incBalls() {
        this.balls++;
    }

    public int getExtras() {
        return extras;
    }

    public void incExtras() {
        this.extras++;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }

    public double getRunRate() {
        if (balls == 0){
            return 0;
        }
        return score/balls;
    }

    public String toString() {

        String form = "%1$-15s %2$-8s %3$-8s %4$-8s %5$-15s";

        if(isCurrent){
            return String.format(form, name+"*", score, fours, sixes, balls+"/"+extras);
        } else {
            return String.format(form, name, score, fours, sixes, balls+"/"+extras);
        }
    }
}
