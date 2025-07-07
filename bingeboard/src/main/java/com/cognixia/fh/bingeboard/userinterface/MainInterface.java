package com.cognixia.fh.bingeboard.userinterface;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.cognixia.fh.bingeboard.dao.ProgressLists;
import com.cognixia.fh.bingeboard.dao.Users;

/*
 * MainInterface class provides the main menu for the BingeBoard application.
 * It allows users to view, update, and remove items from their watch list,
 * view the TV show catalog, sign out, or exit the application.
 */
public class MainInterface {
    /**
     * This method displays the main menu of the BingeBoard application.
     * It prompts the user to select an option from the menu and handles user input accordingly.
     * The options include viewing, updating, and removing items from the watch list,
     * viewing the TV show catalog, signing out, or exiting the application.
     *
     * @param inputScanner Scanner object for reading user input
     * @param connection Database connection object
     * @param user Current user object
     * @param progressList ProgressLists object for managing watch lists
     */
    public static void displayMainMenu(Scanner inputScanner, Connection connection, Users user , ProgressLists progressList) {
        int choice;
        boolean exit = false; // Flag to control the exit condition

        // Loop to handle user input and menu selection
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
            System.out.println("===================================\n");

            System.out.println("Please enter your choice:");
            
            // Prompt the user for their choice and handle input exceptions
            try {
                choice = inputScanner.nextInt(); // Read the user's choice
                inputScanner.nextLine(); // Consume any leftover newline character

                switch (choice) {
                    case 1:
                        viewWatchList(inputScanner, connection, user, progressList);
                        break;
                    case 2:
                        updateWatchList(inputScanner, connection, user, progressList);
                        break;
                    case 3:
                        removeFromWatchList(inputScanner, connection, user, progressList);
                        break;
                    case 4:
                        viewCatalog(inputScanner, connection);
                        break;
                    case 5:
                        signOut(inputScanner, connection, user, progressList);
                        break;
                    case 6:
                        exitBingeBoard(user, progressList);
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

    /**
     * This method displays the user's watch list and allows them to interact with it.
     *
     * @param inputScanner Scanner object for reading user input
     * @param connection Database connection object
     * @param user Current user object
     * @param progressList ProgressLists object for managing watch lists
     */
    private static void viewWatchList(Scanner inputScanner, Connection connection, Users user, ProgressLists progressList) {
        // Display the user's watch list and corresponding menu
        ViewProgressList.displayMenu(inputScanner, connection, progressList);
    }

    /**
     * This method allows the user to update their watch list by adding or modifying shows.
     *
     * @param inputScanner Scanner object for reading user input
     * @param connection Database connection object
     * @param user Current user object
     * @param progressList ProgressLists object for managing watch lists
     */
    private static void updateWatchList(Scanner inputScanner, Connection connection, Users user, ProgressLists progressList) {
        // Code to update the user's watch list
        UpdateProgressList.displayMenu(inputScanner, connection, progressList);
    }

    /**
     * This method allows the user to remove an item from their watch list.
     *
     * @param inputScanner Scanner object for reading user input
     * @param connection Database connection object
     * @param user Current user object
     * @param progressList ProgressLists object for managing watch lists
     */
    private static void removeFromWatchList(Scanner inputScanner, Connection connection, Users user, ProgressLists progressList) {
        // Code to remove an item from the user's watch list
        RemoveFromProgressList.displayMenu(inputScanner, connection, progressList);        
    }

    /**
     * This method allows the user to view the catalog of TV shows.
     *
     * @param inputScanner Scanner object for reading user input
     * @param connection Database connection object
     */
    private static void viewCatalog(Scanner inputScanner, Connection connection) {
        // Code to view catalog of TV shows
        ViewCatalog.displayMenu(inputScanner, connection);        
    }

    /**
     * This method handles the sign-out process for the user.
     *
     * @param inputScanner Scanner object for reading user input
     * @param connection Database connection object
     * @param user Current user object
     * @param progressList ProgressLists object for managing watch lists
     */
    private static void signOut(Scanner inputScanner, Connection connection, Users user, ProgressLists progressList) {
        // Sign out the user and clear their credentials
        Login.signOut(inputScanner, connection, progressList);
    }

    /**
     * This method allows the user to exit the BingeBoard application.
     *
     * @param user Current user object
     * @param progressList ProgressLists object for managing watch lists
     */
    private static void exitBingeBoard(Users user, ProgressLists progressList) {
        // Print exit message
        System.out.println("\nExiting BingeBoard. Thank you for using our service!");

        user = null; // Clear the user variable to allow garbage collection
        progressList = null; // Clear the progress list variable to allow garbage collection
    }
}
