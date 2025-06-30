package com.cognixia.fh.bingeboard.userinterface;

import java.util.Scanner;

public class Login {
    private static String username;
    private static String password;

    public static void signinPage(Scanner inputScanner) {
        boolean validLogin = false;

        System.out.println("Welcome to the BingeBoard!");
        System.out.println("Your entertainment tracking hub for all things binge-worthy!");
        System.out.println("Please log in or sign up by entering a username and password.");
        
        while (!validLogin) {
            // Get username
            System.out.println("Please enter your username and press 'enter':");
            username = inputScanner.nextLine(); // No need for try-catch here

            // Get password
            System.out.println("Please enter your password and press 'enter':");
            password = inputScanner.nextLine();

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

    public static String getUsername() {
        return username; // Return the username for further use
    }

    public static String getPassword() {
        return password; // Return the password for further use
    }

    public static void clearCredentials() {
        username = null; // Clear the username
        password = null; // Clear the password
    }

    public static void signOut(Scanner inputScanner) {
        clearCredentials(); // Clear the stored login credentials

        // Display sign-out message and 2 new lines for better readability
        System.out.println("You have successfully signed out. Thank you for using BingeBoard!");
        System.out.println();
        System.out.println();
        
        signinPage(inputScanner);   // Return to the sign-in page
    }
}
