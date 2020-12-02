package com.banana;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    init();
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Initializing all services and objects");
        System.out.println("What is your name?");
        String name = sc.nextLine();
        System.out.println("Fuck you, " + name + ".");

    }
}
