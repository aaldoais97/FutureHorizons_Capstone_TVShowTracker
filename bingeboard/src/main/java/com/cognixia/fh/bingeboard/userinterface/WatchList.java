package com.cognixia.fh.bingeboard.userinterface;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.cognixia.fh.bingeboard.dao.ProgressLists;

// This method displays the user's watch list, in order by progress
public class WatchList {
    // This will also call to display filtering options for the watch list
    static void displayMenu(Scanner inputScanner, Connection connection, ProgressLists progressList) {
        // This method will display the user's watch list.
        int choice;

        System.out.println("\nFilter options:");
        System.out.println("===================================");
        System.out.println("1. View All");
        System.out.println("2. In Progress");
        System.out.println("3. Not Started");
        System.out.println("4. Finished");
        System.out.println("5. Return to Main Menu");
        System.out.println("===================================");
        
        while(true) {
            System.out.println("\nPlease enter your choice (enter 5 to return to main menu):");

            try {
                choice = inputScanner.nextInt(); // Read the user's choice for filtering
                inputScanner.nextLine(); // Consume any leftover newline character

                switch (choice) {
                    case 1:
                        // View all shows in the watch list
                    case 2:
                        // View shows in progress
                    case 3:
                        // View shows not started
                    case 4:
                        // View finished shows
                        displayWatchList(inputScanner, connection, choice, progressList);
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

    // This method will view the user's watch list based on the selected filter option
    static void displayWatchList(Scanner inputScanner, Connection connection, int filterOption, ProgressLists progressList) {
        // This method will display the user's watch list based on the selected filter option
        System.out.println("\nYour watch list:");
        System.out.println("------------------------------------");
        
        try {
            if (progressList.getProgressList().isEmpty()) {
                System.out.println("Your watch list is empty.");
                return; // Exit if the watch list is empty
            }

            switch (filterOption) {
                case 1:
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
                case 2:
                    // Show in progress shows
                    progressList.getProgressList().stream()
                            .filter(show -> show.getEpisodesWatched() < show.getTotalEpisodes())
                            .forEach(show -> System.out.println(show.getShowName() + ": " + show.getEpisodesWatched() + "/" + show.getTotalEpisodes() + " episodes watched"));
                    break;
                case 3:
                    // Show not started shows
                    progressList.getProgressList().stream()
                            .filter(show -> show.getEpisodesWatched() == 0)
                            .forEach(show -> System.out.println(show.getShowName() + ": Not started yet"));
                    break;
                case 4:
                    // Show finished shows
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
