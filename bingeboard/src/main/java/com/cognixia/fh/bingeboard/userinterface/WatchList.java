package com.cognixia.fh.bingeboard.userinterface;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.cognixia.fh.bingeboard.FilterOptions;

// This method displays the user's watch list, in order by progress
public class WatchList {
    // This will also call to display filtering options for the watch list
    static void displayMenu(Scanner inputScanner, Connection connection) {
        // This method will display the user's watch list.
        int choice;

        // Code to view the user's watch list
        System.out.println("Viewing your watch list...");

        // Implement logic to retrieve and display the watch list from the database

        System.out.println("Filter options:");
        System.out.println("===================================");
        System.out.println("1. By Progress");
        System.out.println("2. By Director");
        System.out.println("3. By Writer");
        System.out.println("4. By Actor");
        System.out.println("5. By Genre");
        System.out.println("6. By TV Network");
        System.out.println("7. Back to Main Menu");
        System.out.println("===================================\n");
        
        while(true) {
            System.out.println("Please enter your choice:");

            try {
                choice = inputScanner.nextInt(); // Read the user's choice for filtering
                inputScanner.nextLine(); // Consume any leftover newline character

                switch (choice) {
                    case 1:
                        System.out.println("Filtering by Progress...");
                        displayWatchList(inputScanner, connection, FilterOptions.BY_PROGRESS);
                        break;
                    case 2:
                        System.out.println("Filtering by Director...");
                        displayWatchList(inputScanner, connection, FilterOptions.BY_DIRECTOR);
                        break;
                    case 3:
                        System.out.println("Filtering by Writer...");
                        displayWatchList(inputScanner, connection, FilterOptions.BY_WRITER);
                        break;
                    case 4:
                        System.out.println("Filtering by Actor...");
                        displayWatchList(inputScanner, connection, FilterOptions.BY_ACTOR);
                        break;
                    case 5:
                        System.out.println("Filtering by Genre...");
                        displayWatchList(inputScanner, connection, FilterOptions.BY_GENRE);
                        break;
                    case 6:
                        System.out.println("Filtering by TV Network...");
                        displayWatchList(inputScanner, connection, FilterOptions.BY_TV_NETWORK);
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

    // This method will view the user's watch list based on the selected filter option
    static void displayWatchList(Scanner inputScanner, Connection connection, FilterOptions filterOption) {
        System.out.println("Viewing your watch list with filter: " + filterOption);

        // Prompt the user to enter the filter criteria
        switch (filterOption) {
            case BY_PROGRESS:
                System.out.println("Please choose between the following options:");
                System.out.println("1. In Progress");
                System.out.println("2. Not Started");
                System.out.println("3. Finished");
                
                try {
                    int choice = inputScanner.nextInt();
                    inputScanner.nextLine(); // Consume any leftover newline character
                    switch (choice) {
                        case 1:
                            // Show in progress shows
                            break;
                        case 2:
                            // Show not started shows
                            break;
                        case 3:
                            // Show finished shows
                            break;
                        default:
                            System.out.println("Invalid option. Please select from the options above.");
                    }
                } catch (Exception e) {
                }
                break;
            case BY_DIRECTOR:
                System.out.println("Please enter the director's name:");
                String director = inputScanner.nextLine();
                // Show shows by the specified director
                break;
            case BY_WRITER:
                System.out.println("Please enter the writer's name:");
                String writer = inputScanner.nextLine();
                // Show shows by the specified writer
                break;
            case BY_ACTOR:
                System.out.println("Please enter the actor's name:");
                String actor = inputScanner.nextLine();
                // Show shows by the specified actor
                break;
            case BY_GENRE:
                System.out.println("Please enter the genre:");
                String genre = inputScanner.nextLine();
                // Show shows by the specified genre
                break;
            case BY_TV_NETWORK:
                System.out.println("Please enter the TV network:");
                String tvNetwork = inputScanner.nextLine();
                // Show shows by the specified TV network
                break;
            default:
                System.out.println("Invalid filter option.");
        }
    }
}
