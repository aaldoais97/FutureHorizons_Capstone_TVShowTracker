package com.cognixia.fh.bingeboard.userinterface;

import java.util.InputMismatchException;
import java.util.Scanner;

public class WatchList {
    static void displayWatchList(Scanner inputScanner) {
        // This method will display the user's watch list.
        int choice;

        // Code to view the user's watch list
        System.out.println("Viewing your watch list...");

        // Implement logic to retrieve and display the watch list from the database

        System.out.println("Filter options:");
        System.out.println("1. By Progress");
        System.out.println("2. By Director");
        System.out.println("3. By Writer");
        System.out.println("4. By Actor");
        System.out.println("5. By Genre");
        System.out.println("6. By TV Network");
        System.out.println("7. Back to Main Menu");
        
        while(true) {
            System.out.println("Please enter your choice:");

            try {
                choice = inputScanner.nextInt(); // Read the user's choice for filtering
                inputScanner.nextLine(); // Consume any leftover newline character

                switch (choice) {
                    case 1:
                        System.out.println("Filtering by Progress...");
                        // Implement logic to filter watch list by progress
                        break;
                    case 2:
                        System.out.println("Filtering by Director...");
                        // Implement logic to filter watch list by director
                        break;
                    case 3:
                        System.out.println("Filtering by Writer...");
                        // Implement logic to filter watch list by writer
                        break;
                    case 4:
                        System.out.println("Filtering by Actor...");
                        // Implement logic to filter watch list by actor
                        break;
                    case 5:
                        System.out.println("Filtering by Genre...");
                        // Implement logic to filter watch list by genre
                        break;
                    case 6:
                        System.out.println("Filtering by TV Network...");
                        // Implement logic to filter watch list by TV network
                        break;
                    case 7:
                        System.out.println("Returning to the main menu...");
                        return; // Return to the main menu
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
                System.out.println("An error occurred while viewing your watch list: " + e.getMessage());
                e.printStackTrace(); // Print the stack trace for debugging purposes
            }
        }
    }
}
