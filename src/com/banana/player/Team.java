package com.banana.player;

import java.util.*;

public class Team {
    public Queue<String> getBattingOrder() {
        return battingOrder;
    }

    public Map<String, Player> getPlayerMap() {
        return players;
    }

    private Queue<String> battingOrder;
    private Map<String, Player> players;
    private int number;

    public Team(String battingOrderArray[], int number) {
        this.number = number;
        players = new HashMap<String, Player>();
        this.battingOrder = new LinkedList<String>(Arrays.asList(battingOrderArray));

        for (String name : battingOrderArray) {
            if(players.containsKey(name)) {
                throw new IllegalArgumentException("Players can't have same name!!");
            }
            players.put(name, new Player(name));
        }
    }

    public String getScorecard() {
        String header = String.format("%1$-15s %2$-8s %3$-8s %4$-8s %5$-8s", "Player Name", "Score", "4s", "6s", "Balls");
        StringBuilder scorecardBuilder = new StringBuilder(header);
        for(Player player : players.values()){

            scorecardBuilder.append("\n").append(player.toString());

        }
        return scorecardBuilder.toString();
    }
    public int getTotal(){
        int totScore = 0;
        for(Player player : players.values()){

            totScore += player.getScore();

        }
        return totScore;
    }

    public int getNumber() {
        return number;
    }
}
