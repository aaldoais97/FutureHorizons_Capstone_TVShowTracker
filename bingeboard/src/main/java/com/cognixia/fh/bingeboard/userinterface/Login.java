package com.cognixia.fh.bingeboard.userinterface;

import java.util.Scanner;

public class Login {
    private static String username;
    private static String password;

    public static void homePage() {
        Scanner loginScanner = new Scanner(System.in);
        boolean validLogin = false;

        System.out.println("Welcome to the BingeBoard!");
        System.out.println("Your entertainment tracking hub for all things binge-worthy!");
        System.out.println("Please log in or sign up by entering a username and password.");
        
        while (!validLogin) {
            // Get username
            System.out.println("Please enter your username and press 'enter':");
            username = loginScanner.nextLine(); // No need for try-catch here

            // Get password
            System.out.println("Please enter your password and press 'enter':");
            password = loginScanner.nextLine();

            // Check if login combination matches existing user
            // INSERT CODE HERE TO CHECK SQL DB

            // Validate password with a length and regex to check for at least 6 characters, 1 digit, and 1 symbol
            if (password.length() > 6 && password.matches("(?=.*\\d)(?=.*[^a-zA-Z0-9]).+")) {
                validLogin = true; // Valid login, exit loop
                System.out.println("Login successful! Welcome, " + username + "!");
            } else {
                System.out.println("Password must be at least 6 characters long and contain at least 1 digit and 1 symbol.");
            }
        }
    }
}
