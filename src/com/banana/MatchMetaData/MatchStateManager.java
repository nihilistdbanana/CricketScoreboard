package com.banana.MatchMetaData;

import com.banana.balls.Ball;
import com.banana.balls.BallFactory;
import com.banana.balls.BallTypeEnum;
import com.banana.player.Player;
import com.banana.player.Team;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.atomic.AtomicInteger;

public class MatchStateManager extends Observable {
    private static boolean initDone = false;
    static BallPublisher ballPublisher;
    static Team currentTeam;
    static Player currentPlayer1;
    static Player currentPlayer2;
    static AtomicInteger working;

    static int competedOvers;
    static int ballCount;
    static int wicketsTaken;


    public static void init() {
        initDone = true;
        ballPublisher = new BallPublisher();
        working = new AtomicInteger(0);

        subscribe(manageOversAndWickets());
        subscribe(manageBatsmen());

    }

    public static void startMatch() {

        if (!initDone) {

            throw new IllegalStateException("Match can't start without init");

        } else {
            try {
                conductInning(1);
            } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
            }
            Team t1 = currentTeam;
            int wicketsTaken1 = wicketsTaken;

            try {
                conductInning(2);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return;
            }
            Team t2 = currentTeam;
            int wicketsTaken2 = wicketsTaken;

            printResult(t1,wicketsTaken1,t2,wicketsTaken2);

        }
        stopMatch();
    }

    private static void stopMatch() {
        unsubscribeAll();
        return;
    }


    private static void conductInning(int teamNumber) {

        System.out.println("Batting Order for team "+teamNumber+":");
        currentTeam = new Team(MatchInputManager.readPlayers(), teamNumber);

        currentPlayer1 = currentTeam.getPlayerMap().get(currentTeam.getBattingOrder().poll());
        currentPlayer1.setCurrent(true);

        currentPlayer2 = currentTeam.getPlayerMap().get(currentTeam.getBattingOrder().poll());
        currentPlayer2.setCurrent(true);

        manageInning(MatchInputManager.getPlayers(), MatchInputManager.getOvers());
    }

    private static void manageInning(int players, int overs) {

        ballCount = 0;
        competedOvers = 0;
        wicketsTaken = 0;
        while (wicketsTaken < players-1 && competedOvers < overs) {
            manageOver(players);
       }


    }

    private static void manageOver(int players) {

        System.out.println("Over " + (competedOvers+1) + ":");
        ballCount = 0;

        while (ballCount < 6 && wicketsTaken < players-1) {

            String ballstr = MatchInputManager.readBall();
            while(!working.compareAndSet(0,ballPublisher.countObservers()));
            manageBall(ballstr);


            System.out.println("player|overs|balls|wickets: " + currentPlayer1.getName() +competedOvers + " " + ballCount + " " + wicketsTaken);

        }

        competedOvers += ballCount / 6;
        ballCount %= 6;
        swapBatsmen();

        System.out.println("\nScorecard for Team "+currentTeam.getNumber()+":");
        System.out.println(currentTeam.getScorecard());

        System.out.println("Total: " + currentTeam.getTotal()+"/"+ wicketsTaken);
        System.out.println("Overs: " + (competedOvers + ballCount / 10.0));
        System.out.println();


    }

    private static void manageBall(String ballstr) {

        try {
            BallTypeEnum ballType = BallTypeEnum.getBallTypeFromLabel(ballstr);

            Ball ball = BallFactory.getBall(ballType, ballstr);

            ballPublisher.notifyObservers(ball);

        } catch (IllegalStateException e) {
            System.out.println(e.getMessage()+"\t Please try again");
            working.set(0);
            return;
        }
    }

    private static void printResult(Team t1, int wicketsTaken1, Team t2, int wicketsTaken2) {
        int score1 = t1.getTotal();
        int score2 = t2.getTotal();
        if(score1 > score2) {
            System.out.println("Result: Team "+1+" won the match by "+(score1-score2)+" run"+(((score1-score2)==1)?"":"s"));
        } else if(score1 < score2) {
            System.out.println("Result: Team "+2+" won the match by "+(score2-score1)+" run"+(((score2-score1)==1)?"":"s"));
        } else {
            System.out.println("The match was a draw");
        }

    }


//    _________________________________________OBSERVERS________________________________________________________________

    private static Observer manageOversAndWickets() {
        Observer ballObserver = new Observer() {

            @Override
            public void update(Observable observable, Object o) {
                Ball ball = (Ball) (o);

                ballCount += (ball.isBall()) ? 1 : 0;
                if (ball.getBallType().equals(BallTypeEnum.WICKET)) {
                    wicketsTaken++;
                }

                working.getAndDecrement();

            }
        };

        return ballObserver;
    }

    private static Observer manageBatsmen() {
        Observer ballObserver = new Observer() {

            @Override
            public void update(Observable observable, Object o) {

                Ball ball = (Ball) (o);
                ball.updatePlayer(currentPlayer1);

                managePitch(ball);

                working.getAndDecrement();
            }
        };

        return ballObserver;
    }

    private static void managePitch(Ball ball) {

        BallTypeEnum ballType = ball.getBallType();

        switch(ballType) {
            case NO_BALL:
            case WIDE_BALL:
            case SIX:
            case FOUR:
                break;
            case NORMAL_BALL:
                if (ball.getScore()%2==1){
                    swapBatsmen();
                }
                break;
            case WICKET:
                replaceCurrentBatsman();

        }

    }

    private static void replaceCurrentBatsman() {
        currentPlayer1.setCurrent(false);

        if (currentTeam.getBattingOrder().isEmpty()){
            currentPlayer1 = currentPlayer2;
        } else {

            currentPlayer1 = currentTeam.getPlayerMap().get(currentTeam.getBattingOrder().poll());
            currentPlayer1.setCurrent(true);
        }
    }

    private static void swapBatsmen() {
        Player temp = currentPlayer1;
        currentPlayer1 = currentPlayer2;
        currentPlayer2 = temp;
    }



//    _________________________________________OBSERVABLE SUBSCRIPTION__________________________________________________

    public static void subscribe(Observer observer) {

        ballPublisher.addObserver(observer);

    }

    public static void unsubscribe(Observer observer) {

        ballPublisher.deleteObserver(observer);

    }

    public static void unsubscribeAll() {

        ballPublisher.deleteObservers();

    }

}
