package com.cognixia.fh.bingeboard.userinterface;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

// This class will serve as the main interface for the BingeBoard application.
// It will handle user interactions, display menus, and manage navigation between different features of the application.
public class MainInterface {
    // This method displays the main menu and handles user input for navigating through the application.
    public static void displayMainMenu(Scanner inputScanner, Connection connection) {
        int choice; // Variable to store the user's menu choice\
        boolean exit = false; // Flag to control the exit condition

        System.out.println("Welcome to BingeBoard!");

        // Loop to handle user input and menu selection
        // This loop will continue until the user selects a valid option.
        while(!exit) {
            System.out.println("Please select an option from the menu below:");
            System.out.println("Enter the number and press 'enter' to select an option.");
            System.out.println("===================================");
            System.out.println("1. View My Watch List");
            System.out.println("2. Update My Watch List");
            System.out.println("3. Remove from My Watch List");
            System.out.println("4. View TV Show Catalog");
            System.out.println("5. Sign out");
            System.out.println("6. Exit BingeBoard"); 
            System.out.println("===================================\n");    // Extra new line for better readability

            System.out.println("Please enter your choice:");
            

            try {
                choice = inputScanner.nextInt(); // Read the user's choice
                inputScanner.nextLine(); // Consume any leftover newline character

                switch (choice) {
                    case 1:
                        viewWatchList(inputScanner, connection);
                        break;
                    case 2:
                        updateWatchList(inputScanner, connection);
                        break;
                    case 3:
                        removeFromWatchList(inputScanner, connection);
                        break;
                    case 4:
                        vewCatalog(inputScanner, connection);
                        break;
                    case 5:
                        signOut(inputScanner, connection);
                        break;
                    case 6:
                        exitBingeBoard(); 
                        exit = true; // Set exit flag to true to break the loop
                        break;
                    default:
                        System.out.println("Invalid option. Please select from the options above.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number corresponding to the menu options."); 
                // Clear the scanner buffer
                if (inputScanner.hasNextLine()) {
                    inputScanner.nextLine(); // Consume the newline character left by nextInt()
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                e.printStackTrace(); // Print the stack trace for debugging purposes
                // Clear the scanner buffer
                if (inputScanner.hasNextLine()) {
                    inputScanner.nextLine(); // Consume the newline character left by nextInt()
                }
            }
        }
    }

    // Other methods for handling user input and navigating through the application can be added here.
    private static void viewWatchList(Scanner inputScanner, Connection connection) {
        // Display the user's watch list and corresponding menu
        WatchList.displayMenu(inputScanner, connection);
    }

    private static void updateWatchList(Scanner inputScanner, Connection connection) {
        // Code to update the user's watch list
        UpdateWatchList.displayMenu(inputScanner, connection);
        // Implement logic to add or modify items in the watch list
        
    }

    private static void removeFromWatchList(Scanner inputScanner, Connection connection) {
        // Code to remove an item from the user's watch list
        RemoveFromWatchList.displayMenu(inputScanner, connection);
        // Implement logic to remove an item from the watch list
        
    }

    private static void vewCatalog(Scanner inputScanner, Connection connection) {
        // Code to view catalog of TV shows
        Catalog.displayMenu(inputScanner, connection);
        // Implement logic to search for content in the database
        
    }

    private static void signOut(Scanner inputScanner, Connection connection) {
        // Code to sign out the user
        System.out.println("Signing out...");

        Login.signOut(inputScanner, connection);
    }

    private static void exitBingeBoard() {
        // Print exit message
        System.out.println("Exiting BingeBoard. Thank you for using our service!");

        Login.clearCredentials(); // Clear the stored login credentials
    }
}
