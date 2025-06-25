package com.cognixia.fh.bingeboard.userinterface;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainInterface {
    // This class will serve as the main interface for the BingeBoard application.
    // It will handle user interactions, display menus, and manage navigation between different features of the application.



    public static void displayMainMenu(Scanner inputScanner) {
        int choice = -1; // Variable to store the user's menu choice
        boolean validOption = false;

        System.out.println("Welcome to BingeBoard!");
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

        // Loop to handle user input and menu selection
        // This loop will continue until the user selects a valid option.
        while(!validOption) {
            System.out.println("Please enter your choice:");
            

            try {
                choice = inputScanner.nextInt(); // Read the user's choice
                inputScanner.nextLine(); // Consume any leftover newline character

                switch (choice) {
                    case 1:
                        viewWatchList();
                        validOption = true;
                        break;
                    case 2:
                        updateWatchList();
                        validOption = true;
                        break;
                    case 3:
                        removeFromWatchList();
                        validOption = true;
                        break;
                    case 4:
                        searchContent();
                        validOption = true;
                        break;
                    case 5:
                        signOut();
                        validOption = true;
                        break;
                    case 6:
                        exitBingeBoard();
                        validOption = true;
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
                System.out.println("Please enter a valid option.");
                e.printStackTrace(); // Print the stack trace for debugging purposes
                // Clear the scanner buffer
                if (inputScanner.hasNextLine()) {
                    inputScanner.nextLine(); // Consume the newline character left by nextInt()
                }
            }
        }
    }

    // Other methods for handling user input and navigating through the application can be added here.
    private static void viewWatchList() {
        // Code to view the user's watch list
        System.out.println("Viewing your watch list...");
        // Implement logic to retrieve and display the watch list from the database
        
    }

    private static void updateWatchList() {
        // Code to update the user's watch list
        System.out.println("Updating your watch list...");
        // Implement logic to add or modify items in the watch list
        
    }

    private static void removeFromWatchList() {
        // Code to remove an item from the user's watch list
        System.out.println("Removing an item from your watch list...");
        // Implement logic to remove an item from the watch list
        
    }

    private static void searchContent() {
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
        // Code to exit the application
        System.out.println("Exiting BingeBoard. Thank you for using our service!");
        // Implement logic to clean up resources and exit the application
        
    }
}
