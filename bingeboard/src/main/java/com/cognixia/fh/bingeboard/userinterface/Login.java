package com.cognixia.fh.bingeboard.userinterface;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.cognixia.fh.bingeboard.dao.ProgressLists;
import com.cognixia.fh.bingeboard.dao.Users;

public class Login {
    private static String username;
    private static String password;
    private static int userId;
    private static Users user;

    public static void signinPage(Scanner inputScanner, Connection connection) {
        boolean validLogin = false;
        
        while (!validLogin) {
            // Get username
            System.out.println("Please enter your username:");
            username = inputScanner.nextLine(); // No need for try-catch here

            

            // Get password
            System.out.println("Please enter your password:");
            password = inputScanner.nextLine();

            // Check if login combination matches existing user
            userId = Users.validateLogin(connection, username, password);
            if (userId != -1) {
                validLogin = true;
            }
            else {
                System.out.println("Invalid username or password. Please try again.\n");
            }
        }

        user = new Users(userId, username, password); // Create a new Users object with the provided username and password


        // Display success message if all setup is successful
        System.out.println("\nLogin successful! Welcome, " + username + "!\n");
    }

    public static void signupPage(Scanner inputScanner, Connection connection) {
        // Get username
        System.out.println("Please enter a username:");
        username = inputScanner.nextLine();

        if(username.isEmpty()) {
            System.out.println("Username cannot be empty. Please try again.\n");
            signupPage(inputScanner, connection); // Retry sign-up if username is empty
        }

        // Check if username is already taken
        if (Users.usernameExists(connection, username)) {
            System.out.println("Username already exists " + username + ". Please try a different username.\n");
            signupPage(inputScanner, connection); // Retry sign-up if username exists
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
                break; // Exit loop to complete sign-up if validation passes
            } else {
                System.out.println("Password must be at least 6 characters long and contain at least 1 digit and 1 symbol.\n");
            }
        }

        // Insert new user and progress list into the database
        user = Users.insertNewUser(connection, username, password);

        if (user != null) {
            System.out.println("\nSign up successful! Welcome, " + username + "!\n");
        } else {
            System.out.println("Sign up failed. Please try again.\n");
            signupPage(inputScanner, connection); // Retry sign-up if insertion fails
        }

    }

    public static Users startPage(Scanner inputScanner, Connection connection) {
        // Display start page message
        System.out.println("Welcome to the BingeBoard!");
        System.out.println("Your entertainment tracking hub for all things binge-worthy!\n");
        System.out.println("Please choose an option to continue:");
        
        while (true) { 
            System.out.println("1. Sign In");
            System.out.println("2. Sign Up\n");

            try {
                int choice = inputScanner.nextInt(); // Read user's choice
                inputScanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        signinPage(inputScanner, connection);
                        return user; // Exit the loop & function if sign-in is successful
                    case 2:
                        signupPage(inputScanner, connection);
                        return user; // Exit the loop & function if sign-up is successful
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

    public static void signOut(Scanner inputScanner, Connection connection, ProgressLists progressList) {
        clearCredentials(); // Clear the stored login credentials

        // Display sign-out message and 2 new lines for better readability
        System.out.println("\nYou have successfully signed out. Thank you for using BingeBoard!");
        System.out.println();
        System.out.println();

        startPage(inputScanner, connection);   // Return to the sign-in page
    }
}
