package com.cognixia.fh.bingeboard.userinterface;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.cognixia.fh.bingeboard.FilterOptions;

// This method will display the menu for the user to filter shows.
public class Catalog {
    static void displayMenu(Scanner inputScanner, Connection connection) {
        int choice;

        System.out.println("Viewing the catalog of shows...");

        // Implement logic to retrieve and display all shows from the database

        System.out.println("Filter options:");
        System.out.println("===================================");
        System.out.println("1. View All");
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
                        System.out.println("Viewing all shows in the catalog...");
                        viewCatalog(inputScanner, connection, FilterOptions.VIEW_ALL);
                        break;
                    case 2:
                        System.out.println("Filtering by Director...");
                        viewCatalog(inputScanner, connection, FilterOptions.BY_DIRECTOR);
                        break;
                    case 3:
                        System.out.println("Filtering by Writer...");
                        viewCatalog(inputScanner, connection, FilterOptions.BY_WRITER);
                        break;
                    case 4:
                        System.out.println("Filtering by Actor...");
                        viewCatalog(inputScanner, connection, FilterOptions.BY_ACTOR);
                        break;
                    case 5:
                        System.out.println("Filtering by Genre...");
                        viewCatalog(inputScanner, connection, FilterOptions.BY_GENRE);
                        break;
                    case 6:
                        System.out.println("Filtering by TV Network...");
                        viewCatalog(inputScanner, connection, FilterOptions.BY_TV_NETWORK);
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
                System.out.println("An error occurred while viewing catalog: " + e.getMessage());
                e.printStackTrace(); // Print the stack trace for debugging purposes
            }
        }
    }

    static void viewCatalog(Scanner inputScanner, Connection connection, FilterOptions filterOption) {
        System.out.println("Viewing the catalog with filter: " + filterOption);
        
        switch(filterOption) {
            case VIEW_ALL:
                System.out.println("Viewing all shows in the catalog...");
                // Implement logic to view all shows in the catalog
                break;
            case BY_DIRECTOR:
                System.out.println("Please enter the director's name:");
                String director = inputScanner.nextLine();
                // Implement logic to filter catalog by director
                break;
            case BY_WRITER:
                System.out.println("Please enter the writer's name:");
                String writer = inputScanner.nextLine();
                // Implement logic to filter catalog by writer
                break;
            case BY_ACTOR:
                System.out.println("Please enter the actor's name:");
                String actor = inputScanner.nextLine();
                // Implement logic to filter catalog by actor
                break;
            case BY_GENRE:
                System.out.println("Please enter the genre:");
                String genre = inputScanner.nextLine();
                // Implement logic to filter catalog by genre
                break;
            case BY_TV_NETWORK:
                System.out.println("Please enter the TV network:");
                String tvNetwork = inputScanner.nextLine();
                // Implement logic to filter catalog by TV network
                break;
            default:
                System.out.println("Invalid filter option.");
        }
    }
}
