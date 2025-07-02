package com.cognixia.fh.bingeboard.userinterface;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Login {
    private static String username;
    private static String password;

    public static void signinPage(Scanner inputScanner) {
        boolean validLogin = false;
        
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
                System.out.println("\nLogin successful! Welcome, " + username + "!\n");
            } else {
                System.out.println("Password must be at least 6 characters long and contain at least 1 digit and 1 symbol.\n");
            }
        }
    }

    public static void signupPage(Scanner inputScanner) {
        // Get username
        System.out.println("Please enter a username:");
        username = inputScanner.nextLine();

        if(username.isEmpty()) {
            System.out.println("Username cannot be empty. Please try again.\n");
            signupPage(inputScanner); // Retry sign-up if username is empty
        }

        // Keep looping until a valid password is entered and confirmed
        while(true) {
            // Get password
            System.out.println("Please enter a password:");
            password = inputScanner.nextLine();

            System.err.println("Please confirm your password:");
            String confirmPassword = inputScanner.nextLine();

            // Check if passwords match
            if (!password.equals(confirmPassword)) {
                System.out.println("Passwords do not match. Please try again.\n");
                continue; // Retry password input if they don't match
            }

            // Validate password with a length and regex to check for at least 6 characters, 1 digit, and 1 symbol
            if (password.length() > 6 && password.matches("(?=.*\\d)(?=.*[^a-zA-Z0-9]).+")) {
                // INSERT CODE HERE TO ADD USER TO SQL DB
                System.out.println("\nSign up successful! Welcome, " + username + "!\n");
                break; // Exit loop if sign-up is successful
            } else {
                System.out.println("Password must be at least 6 characters long and contain at least 1 digit and 1 symbol.\n");
                continue; // Retry password input if validation fails
            }
        }
    }

    public static void startPage(Scanner inputScanner) {
        // Display start page message
        System.out.println("Welcome to the BingeBoard!");
        System.out.println("Your entertainment tracking hub for all things binge-worthy!\n");
        
        while (true) { 
            System.out.println("1. Sign In");
            System.out.println("2. Sign Up");

            try {
                int choice = inputScanner.nextInt(); // Read user's choice
                inputScanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        signinPage(inputScanner);
                        return; // Exit the loop after successful sign-in
                    case 2:
                        signupPage(inputScanner);
                        return; // Exit the loop after successful sign-up
                    default:
                        System.out.println("Invalid choice. Please try again.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number corresponding to the options above.\n");
                inputScanner.nextLine(); // Clear the scanner buffer
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                e.printStackTrace(); // Print the stack trace for debugging purposes
                inputScanner.nextLine(); // Clear the scanner buffer
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
        
        startPage(inputScanner);   // Return to the sign-in page
    }
}
