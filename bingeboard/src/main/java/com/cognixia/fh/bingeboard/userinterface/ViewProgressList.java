package com.cognixia.fh.bingeboard.userinterface;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.cognixia.fh.bingeboard.ProgressListFilterOptions;
import com.cognixia.fh.bingeboard.dao.ProgressLists;

/**
 * This class provides a user interface for viewing the user's watch list.
 * It allows users to filter their watch list based on different progress options.
 */
public class ViewProgressList {
    /**
     * This method displays the menu for viewing the user's watch list.
     * It prompts the user to select a filter option and displays the watch list accordingly.
     *
     * @param inputScanner Scanner object for reading user input
     * @param connection Database connection object
     * @param progressList ProgressLists object for managing watch lists
     */
    static void displayMenu(Scanner inputScanner, Connection connection, ProgressLists progressList) {
        int choice;

        // Display the menu options to filter the watch list
        System.out.println("\nFilter options:");
        System.out.println("===================================");
        System.out.println("1. View All");
        System.out.println("2. In Progress");
        System.out.println("3. Not Started");
        System.out.println("4. Finished");
        System.out.println("5. Return to Main Menu");
        System.out.println("===================================");
        
        // Loop to handle user input and menu selection
        // This loop will continue until the user chooses to return to the main menu
        while(true) {
            System.out.println("\nPlease enter your choice (enter 5 to return to main menu):");

            // Prompt the user for their choice and handle input exceptions
            try {
                choice = inputScanner.nextInt(); // Read the user's choice for filtering
                inputScanner.nextLine(); // Consume any leftover newline character

                switch (choice) {
                    case 1:
                        // View all shows in the watch list
                        displayWatchList(inputScanner, connection, ProgressListFilterOptions.VIEW_ALL, progressList);
                        break;
                    case 2:
                        // View shows in progress
                        displayWatchList(inputScanner, connection, ProgressListFilterOptions.IN_PROGRESS, progressList);
                        break;
                    case 3:
                        // View shows not started
                        displayWatchList(inputScanner, connection, ProgressListFilterOptions.VIEW_ALL, progressList);
                        break;
                    case 4:
                        // View finished shows
                        displayWatchList(inputScanner, connection, ProgressListFilterOptions.COMPLETED, progressList);
                        break;
                    case 5:
                        return; // Return to the main menu;
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

    /**
     * This method displays the user's watch list based on the selected filter option.
     *
     * @param inputScanner Scanner object for reading user input
     * @param connection Database connection object
     * @param filterOption The filter option selected by the user
     * @param progressList ProgressLists object for managing watch lists
     */
    static void displayWatchList(Scanner inputScanner, Connection connection, ProgressListFilterOptions filterOption, ProgressLists progressList) {
        // Display watch list header
        System.out.println("\nYour watch list:");
        System.out.println("------------------------------------");
        
        // Get the user's watch list and filter it based on the selected option
        // If the watch list is empty, print a message and exit
        try {
            if (progressList.getProgressList().isEmpty()) {
                System.out.println("Your watch list is empty.");
                return; // Exit if the watch list is empty
            }

            switch (filterOption) {
                case VIEW_ALL:
                    // Show all shows
                    progressList.getProgressList().forEach(show -> {
                    boolean complete = show.getEpisodesWatched() == show.getTotalEpisodes();
                    boolean notStarted = show.getEpisodesWatched() == 0;

                    // If not complete and not started, show the number of episodes watched
                    // If complete, show "Completed"
                    // If not started, show "Not started yet"
                    String status;
                    if (complete) {
                        status = "Completed";
                    } else if (notStarted) {
                        status = "Not started yet";
                    } else {
                        status = show.getEpisodesWatched() + "/" + show.getTotalEpisodes() + " episodes watched";
                    }
                    System.out.println(show.getShowName() + ": " + status);
                    });
                    break;
                case IN_PROGRESS:
                    // Show in progress shows
                    progressList.getProgressList().stream()
                            .filter(show -> show.getEpisodesWatched() < show.getTotalEpisodes())
                            .forEach(show -> System.out.println(show.getShowName() + ": " + show.getEpisodesWatched() + "/" + show.getTotalEpisodes() + " episodes watched"));
                    break;
                case NOT_STARTED:
                    // Show not started shows
                    progressList.getProgressList().stream()
                            .filter(show -> show.getEpisodesWatched() == 0)
                            .forEach(show -> System.out.println(show.getShowName() + ": Not started yet"));
                    break;
                case COMPLETED:
                    // Show completed shows
                    progressList.getProgressList().stream()
                            .filter(show -> show.getEpisodesWatched() == show.getTotalEpisodes())
                            .forEach(show -> System.out.println(show.getShowName() + ": Completed"));
                    break;
                default:
                    System.out.println("Invalid option. Please select from the options above.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while filtering by progress: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for debugging purposes
            inputScanner.nextLine(); // Clear the scanner buffer
        }
    }
}