package com.cognixia.fh.bingeboard.userinterface;

import java.util.Scanner;

import com.cognixia.fh.bingeboard.exceptions.ShowNotFoundException;

/**
 * This class provides a user interface for updating the episodes watched in the watch list.
 * It prompts the user to enter the name of a show and the number of episodes watched.
 * If the show is not found, it throws a ShowNotFoundException.
 */
public class UpdateWatchList {
    // This method displays the menu for updating the watch list.
    // It prompts the user for the name of the show and the number of episodes watched.
    static void displayMenu(Scanner inputScanner) {
        String showName; // Variable to store user's choice
        int episodesWatched; // Variable to store the number of episodes watched

        while(true) {
            System.out.println("Please enter the name of the show:");

            try {
                showName = inputScanner.nextLine(); // Consume any leftover newline character

                // Below line is a placeholder for the actual logic to check if the show exists in the watch list
                if (showName.equals("temp")) {
                    throw new ShowNotFoundException("temp");
                }
            } catch (ShowNotFoundException e) {
                System.out.println(e.getMessage());
                return; // Exit the method if the show is not found
                
            } catch (Exception e) {
                System.out.println("An error occurred while viewing your watch list: " + e.getMessage());
                e.printStackTrace(); // Print the stack trace for debugging purposes
                return; // Exit the method if an error occurs
            }

            try {
                System.out.println("How many episodes have you watched of " + showName + "?");
                episodesWatched = inputScanner.nextInt(); // Read the number of episodes watched
                inputScanner.nextLine(); // Consume any leftover newline character

                // Below line is a placeholder for the actual logic to update the watch list
                System.out.println("Updating " + showName + " with " + episodesWatched + " episodes watched.");
                break; // Exit the loop after updating the watch list
            } catch (Exception e) {
                System.out.println("An error occurred while updating your watch list: " + e.getMessage());
                e.printStackTrace(); // Print the stack trace for debugging purposes
                return; // Exit the method if an error occurs
            }
        }
    }
}
