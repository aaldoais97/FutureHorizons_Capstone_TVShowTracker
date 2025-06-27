package com.cognixia.fh.bingeboard.userinterface;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainInterface {
    // This class will serve as the main interface for the BingeBoard application.
    // It will handle user interactions, display menus, and manage navigation between different features of the application.



    public static void displayMainMenu(Scanner inputScanner) {
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
            System.out.println("4. Search for Content");
            System.out.println("5. Sign out");
            System.out.println("6. Exit BingeBoard"); 
            System.out.println("===================================\n");    // Extra new line for better readability

            System.out.println("Please enter your choice:");
            

            try {
                choice = inputScanner.nextInt(); // Read the user's choice
                inputScanner.nextLine(); // Consume any leftover newline character

                switch (choice) {
                    case 1:
                        viewWatchList(inputScanner);
                        break;
                    case 2:
                        updateWatchList(inputScanner);
                        break;
                    case 3:
                        removeFromWatchList(inputScanner);
                        break;
                    case 4:
                        searchContent(inputScanner);
                        break;
                    case 5:
                        signOut();
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
    private static void viewWatchList(Scanner inputScanner) {
        // Display the user's watch list and corresponding menu
        WatchList.displayWatchList(inputScanner);
    }

    private static void updateWatchList(Scanner inputScanner) {
        // Code to update the user's watch list
        UpdateWatchList.displayMenu(inputScanner);
        // Implement logic to add or modify items in the watch list
        
    }

    private static void removeFromWatchList(Scanner inputScanner) {
        // Code to remove an item from the user's watch list
        System.out.println("Removing an item from your watch list...");
        // Implement logic to remove an item from the watch list
        
    }

    private static void searchContent(Scanner inputScanner) {
        // Code to search for content
        System.out.println("Searching for content...");
        // Implement logic to search for content in the database
        
    }

    private static void signOut() {
        // Code to sign out the user
        System.out.println("Signing out...");
        // Implement logic to handle user sign-out
        
    }

    private static void exitBingeBoard() {
        // Print exit message
        System.out.println("Exiting BingeBoard. Thank you for using our service!");
        // Implement logic to clean up resources and exit the application
        
    }
}
