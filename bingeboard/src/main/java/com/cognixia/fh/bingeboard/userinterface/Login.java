package com.cognixia.fh.bingeboard.userinterface;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.cognixia.fh.bingeboard.dao.ProgressLists;
import com.cognixia.fh.bingeboard.dao.Users;

/*
 * This class handles user login and sign-up functionality.
 * It provides methods for the BingeBoard start page, signing in, and signing up.
*/
public class Login {
    private static String username;
    private static String password;
    private static int userId;
    private static Users user;

    /**
     * This method displays the sign-in page and prompts the user for their username and password.
     * If the login is successful, it creates a Users object with the provided credentials.
     * If the login fails, it prompts the user to try again.
     */
    public static void signinPage(Scanner inputScanner, Connection connection) {
        boolean validLogin = false;
        
        // Keep looping until a valid login is provided
        while (!validLogin) {
            // Get username
            System.out.println("Please enter your username (enter 'exit' to cancel):");
            username = inputScanner.nextLine(); // No need for try-catch here

            // Check if username is empty or "exit"
            if (username.isEmpty()) {
                continue; // Retry login if username is empty
            } else if (username.equalsIgnoreCase("exit")) {
                startPage(inputScanner, connection); // Return to start page if user wants to exit
                return; // Exit the method to prevent further processing
            }

            // Get password
            System.out.println("Please enter your password:");
            password = inputScanner.nextLine();

            // Check if login combination matches existing user
            try {
                userId = Users.validateLogin(connection, username, password);
            } catch (SQLException e) {
                System.out.println("An error occurred while validating login: " + e.getMessage());
                System.out.println("Please try again.\n");
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                System.out.println("Please try again.\n");
                e.printStackTrace();
            }

            // If userId is not -1, login is successful
            if (userId != -1) {
                validLogin = true;
            }
            else {
                System.out.println("Invalid username or password. Please try again.\n");
            }
        }

        // If login is successful, create a new Users object with the provided username and password
        // Note: In a real application, you would not store the password in plain text like this.
        user = new Users(userId, username, password); // Create a new Users object with the provided username and password


        // Display success message if all setup is successful
        System.out.println("\nLogin successful! Welcome, " + username + "!\n");
    }

    /**
     * This method displays the sign-up page and prompts the user for a username and password.
     * It checks if the username is already taken and validates the password.
     * If the sign-up is successful, it creates a new Users object with the provided credentials.
     */
    public static void signupPage(Scanner inputScanner, Connection connection) {
        // Get username
        System.out.println("Please enter a username (enter 'exit' to cancel):");
        username = inputScanner.nextLine();

        // Check if username is empty or "exit"
        if(username.isEmpty()) {
            signupPage(inputScanner, connection); // Retry sign-up if username is empty
        } else if (username.equalsIgnoreCase("exit")) {
            startPage(inputScanner, connection); // Return to start page if user wants to exit
        }

        // Check if username is already taken
        try {
            if (Users.usernameExists(connection, username)) {
                System.out.println("Username already exists " + username + ". Please try a different username.\n");
                signupPage(inputScanner, connection); // Retry sign-up if username exists
                return; // Exit the method to prevent further processing
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while checking username availability: " + e.getMessage());
            System.out.println("Please try again.\n");
            e.printStackTrace(); // Print the stack trace for debugging purposes
        } catch (Exception e) {
            System.out.println("An error occurred while checking username availability: " + e.getMessage());
            System.out.println("Please try again.\n");
            e.printStackTrace(); // Print the stack trace for debugging purposes
        }

        // Keep looping until a valid password is entered and confirmed
        while(true) {
            // Get password
            System.out.println("Please enter a password:");
            password = inputScanner.nextLine();

            // Ask user to confirm password
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
        try {
            user = Users.insertNewUser(connection, username, password);
        } catch (SQLException e) {
            System.out.println("An error occurred while inserting new user: " + e.getMessage());
            System.out.println("Please try again.\n");
            e.printStackTrace(); // Print the stack trace for debugging purposes
        } catch (Exception e) {
            System.out.println("An error occurred while inserting new user: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for debugging purposes
        }

        // If user is successfully created, display success message
        if (user != null) {
            System.out.println("\nSign up successful! Welcome, " + username + "!\n");
        } else {
            System.out.println("Sign up failed. Please try again.\n");
            signupPage(inputScanner, connection); // Retry sign-up if insertion fails
        }

    }

    /**
     * This method displays the start page of the BingeBoard application.
     * It prompts the user to either sign in or sign up.
     * If the user chooses to sign in, it calls the signinPage method.
     * If the user chooses to sign up, it calls the signupPage method.
     */
    public static Users startPage(Scanner inputScanner, Connection connection) {
        // Display start page message
        System.out.println("Welcome to the BingeBoard!");
        System.out.println("Your entertainment tracking hub for all things binge-worthy!\n");
        System.out.println("Please choose an option to continue:");
        
        // Loop until the user successfully signs in or signs up
        while (true) { 
            System.out.println("1. Sign In");
            System.out.println("2. Sign Up\n");

            // Get user choice
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

    // This method clears the stored login credentials.
    // It sets the username and password to null, effectively logging out the user.
    public static void clearCredentials() {
        username = null; // Clear the username
        password = null; // Clear the password
    }

    /**
     * This method handles the sign-out process.
     * It clears the stored login credentials and displays a sign-out message.
     * After signing out, it returns to the start page of the application.
     */
    public static void signOut(Scanner inputScanner, Connection connection, ProgressLists progressList) {
        clearCredentials(); // Clear the stored login credentials

        // Display sign-out message and 2 new lines for better readability
        System.out.println("\nYou have successfully signed out. Thank you for using BingeBoard!");
        System.out.println();
        System.out.println();

        startPage(inputScanner, connection);   // Return to the sign-in page
    }
}
