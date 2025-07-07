package com.cognixia.fh.bingeboard.userinterface;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.cognixia.fh.bingeboard.dao.ProgressLists;
import com.cognixia.fh.bingeboard.exceptions.ShowNotFoundException;

/**
 * This class provides a user interface for updating the user's watch list.
 * It prompts the user to enter the name of the show and the number of episodes watched,
 * and updates the watch list accordingly.
 */
public class UpdateProgressList {
    /**
     * This method displays the menu for updating the user's watch list.
     * It prompts the user for the name of the show and the number of episodes watched,
     * and updates the watch list accordingly.
     *
     * @param inputScanner Scanner object for reading user input
     * @param connection Database connection object
     * @param progressList ProgressLists object for managing watch lists
     */
    static void displayMenu(Scanner inputScanner, Connection connection, ProgressLists progressList) {
        String showName; // Variable to store user's choice
        int episodesWatched;

        // Prompt the user for the name of the show they want to update in their watch list until they enter 'exit'
        while(true) {
            System.out.println("\nPlease enter the name of the show (enter 'exit' to return to menu):");

            showName = inputScanner.nextLine();

            if (showName.equalsIgnoreCase("exit")) {
                return; // Exit the method if the user wants to return to the menu
            }

            // If user enters a show name, proceed to ask for the number of episodes watched
            try {
                System.out.println("How many episodes have you watched of " + showName + "?");
                episodesWatched = inputScanner.nextInt(); // Read the number of episodes watched
                inputScanner.nextLine(); // Consume any leftover newline character
                System.out.println();   // Print a new line for better readability

                if (episodesWatched < 0) {
                    System.out.println("Cannot watch a negative number of episodes. Please try again.");
                    continue; // Prompt the user to enter a valid number of episodes
                }

                // Update the watch list with the show name and episodes watched
                progressList.updateProgressList(connection, showName, episodesWatched); // Update the watch list with the show name and episodes watched

                // If the update is successful, print a success message, otherwise catch exceptions
                System.out.println("Progress updated for show: " + showName);
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input. Please enter a valid number for episodes watched.");
                inputScanner.nextLine(); // Clear the invalid input
            } catch (ShowNotFoundException e) {
                System.out.println("Show not found in your watch list: " + e.getMessage());
                System.out.println();   // Print a new line for better readability
            } catch (SQLException e) {
                System.out.println("An error occurred while updating your watch list: " + e.getMessage());
                System.out.println();   // Print a new line for better readability
                e.printStackTrace(); // Print the stack trace for debugging purposes
            } catch (Exception e) {
                System.out.println("An unexpected error occurred while updating your watch list: " + e.getMessage());
                System.out.println();   // Print a new line for better readability
                e.printStackTrace(); // Print the stack trace for debugging purposes
                return; // Exit the method if an error occurs
            }
        }
    }
}
