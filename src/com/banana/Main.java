package com.banana;

import com.banana.MatchMetaData.MatchInputManager;
import com.banana.MatchMetaData.MatchStateManager;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        init();
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);

        MatchInputManager.init();
        MatchStateManager.init();
        MatchStateManager.startMatch();

    }
}
