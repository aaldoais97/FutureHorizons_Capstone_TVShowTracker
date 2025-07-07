package com.cognixia.fh.bingeboard.userinterface;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.cognixia.fh.bingeboard.dao.ProgressLists;
import com.cognixia.fh.bingeboard.exceptions.ShowNotInProgListException;

/**
 * This class provides a user interface for removing a show from the user's watch list.
 * It prompts the user to enter the name of the show they want to remove and handles the removal process.
 */
public class RemoveFromProgressList {
    /**
     * This method displays the menu for removing a show from the watch list.
     * It prompts the user for the name of the show they want to remove and handles the removal process.
     *
     * @param inputScanner Scanner object for reading user input
     * @param connection Database connection object
     * @param progressList ProgressLists object for managing watch lists
     */
    static void displayMenu(Scanner inputScanner, Connection connection, ProgressLists progressList) {
        // Prompt the user for the name of the show they want to remove from their watch list
        System.out.println("Enter the name of the show you want to remove from your watchlist (enter 'exit' to return to menu):");
        String showName = inputScanner.nextLine();

        System.out.println(); // Print a new line for better readability

        if (showName.equalsIgnoreCase("exit")) {
            return; // Exit the method if the user wants to return to the menu
        }

        // Attempt to remove the show from the progress list
        try {
            progressList.removeFromProgressList(connection, showName);
        } catch (ShowNotInProgListException e) {
            System.out.println(e.getMessage());
            System.out.println();   // Print a new line for better readability
        } catch (SQLException e) {
            System.out.println("Error removing show from progress list: " + e.getMessage());
            System.out.println();   // Print a new line for better readability
            e.printStackTrace(); // Print the stack trace for debugging purposes
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            System.out.println();   // Print a new line for better readability
            e.printStackTrace(); // Print the stack trace for debugging purposes
            return; // Exit the method if an unexpected error occurs
        }

        // If the show was successfully removed, print a success message
        System.out.println("Successfully removed " + showName + " from your watchlist.\n");
    }
}
