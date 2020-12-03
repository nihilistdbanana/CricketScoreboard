package com.banana.MatchMetaData;

import com.banana.Main;

import java.util.LinkedList;
import java.util.Scanner;

// This class will be the used to manage all the inputs to the program,
// and store the constant data for the match like the number of players, number of overs.

public class MatchInputManager {

    private static Scanner sc;
    private static boolean initDone = false;

    private static int players;
    private static int overs;


    public static void init() {
        initDone = true;
        sc = new Scanner(System.in);
        System.out.print("No. of players for each team: ");
        players = sc.nextInt();
        sc.nextLine();
        System.out.print("No. of overs: ");
        overs = sc.nextInt();
        sc.nextLine();

    }

    public static String readBall() {
        String ballStr = sc.nextLine().trim();
        return ballStr;
    }

    public static String[] readPlayers() {
        if (!initDone) {

            throw new IllegalStateException("Match can't start without init");

        } else {
            String battingOrderArr[] = new String[players];
            for(int i =0; i<players; i++) {
                battingOrderArr[i] = sc.nextLine().trim();
            }
            return battingOrderArr;
        }
    }


    public static int getPlayers() {
        return players;
    }

    public static int getOvers() {
        return overs;
    }
}
