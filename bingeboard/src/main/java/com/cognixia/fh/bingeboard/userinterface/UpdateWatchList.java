package com.cognixia.fh.bingeboard.userinterface;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.cognixia.fh.bingeboard.dao.ProgressLists;
import com.cognixia.fh.bingeboard.exceptions.ShowNotFoundException;

/**
 * This class provides a user interface for updating the episodes watched in the watch list.
 * It prompts the user to enter the name of a show and the number of episodes watched.
 * If the show is not found, it throws a ShowNotFoundException.
 */
public class UpdateWatchList {
    // This method displays the menu for updating the watch list.
    // It prompts the user for the name of the show and the number of episodes watched.
    static void displayMenu(Scanner inputScanner, Connection connection, ProgressLists progressList) {
        String showName; // Variable to store user's choice
        int episodesWatched; // Variable to store the number of episodes watched

        while(true) {
            System.out.println("Please enter the name of the show (enter 'exit' to return to menu):");

            showName = inputScanner.nextLine();

                if (showName.equalsIgnoreCase("exit")) {
                    return; // Exit the method if the user wants to return to the menu
                }

            try {
                System.out.println("How many episodes have you watched of " + showName + "?");
                episodesWatched = inputScanner.nextInt(); // Read the number of episodes watched
                inputScanner.nextLine(); // Consume any leftover newline character

                if (episodesWatched < 0) {
                    System.out.println("Cannot watch a negative number of episodes. Please try again.");
                    continue; // Prompt the user to enter a valid number of episodes
                }

                progressList.updateProgressList(connection, showName, episodesWatched); // Update the watch list with the show name and episodes watched
                System.out.println("Progress updated for show: " + showName);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number for episodes watched.");
                inputScanner.nextLine(); // Clear the invalid input
            } catch (ShowNotFoundException e) {
                System.out.println("Show not found in your watch list: " + e.getMessage());
                return; // Exit the method if the show is not found
            } catch (SQLException e) {
                System.out.println("An error occurred while updating your watch list: " + e.getMessage());
                e.printStackTrace(); // Print the stack trace for debugging purposes
                return; // Exit the method if an SQL error occurs
            } catch (Exception e) {
                System.out.println("An error occurred while viewing your watch list: " + e.getMessage());
                e.printStackTrace(); // Print the stack trace for debugging purposes
                return; // Exit the method if an error occurs
            }
        }
    }
}
