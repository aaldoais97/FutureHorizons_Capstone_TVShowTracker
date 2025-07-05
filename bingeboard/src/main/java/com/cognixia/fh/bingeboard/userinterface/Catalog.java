package com.cognixia.fh.bingeboard.userinterface;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Filter;

import com.cognixia.fh.bingeboard.FilterOptions;
import com.cognixia.fh.bingeboard.dao.Shows;

// This method will display the menu for the user to filter shows.
public class Catalog {
    ArrayList<Shows> catalog;

    static void displayMenu(Scanner inputScanner, Connection connection) {
        int choice;
        // Flag to check if the catalog has been displayed, in order to change prompt message
        boolean catalogDisplayed = false; 

        System.out.println("Viewing the catalog of shows...");

        // Implement logic to retrieve and display all shows from the database

        displayMenuOptions();

        while(true) {
            if (catalogDisplayed) {
                System.out.println("Enter '7' to return to the main menu or any other key to display catalog menu:");
                String nextChoice = inputScanner.nextLine();
                if (nextChoice.equals("7")) {
                    return; // Return to the main menu
                } else {
                    displayMenuOptions();
                }
            } else {
                System.out.println("Please enter your choice:");
            }

            try {
                choice = inputScanner.nextInt(); // Read the user's choice for filtering
                inputScanner.nextLine(); // Consume any leftover newline character

                switch (choice) {
                    case 1:
                        System.out.println("Viewing all shows in the catalog...");
                        viewCatalog(inputScanner, connection, FilterOptions.VIEW_ALL);
                        catalogDisplayed = true; // Set flag to true if catalog is displayed
                        break;
                    case 2:
                        System.out.println("Filtering by Director...");
                        viewCatalog(inputScanner, connection, FilterOptions.BY_DIRECTOR);
                        catalogDisplayed = true; // Set flag to true if catalog is displayed
                        break;
                    case 3:
                        System.out.println("Filtering by Writer...");
                        viewCatalog(inputScanner, connection, FilterOptions.BY_WRITER);
                        catalogDisplayed = true; // Set flag to true if catalog is displayed
                        break;
                    case 4:
                        System.out.println("Filtering by Actor...");
                        viewCatalog(inputScanner, connection, FilterOptions.BY_ACTOR);
                        catalogDisplayed = true; // Set flag to true if catalog is displayed
                        break;
                    case 5:
                        System.out.println("Filtering by Genre...");
                        viewCatalog(inputScanner, connection, FilterOptions.BY_GENRE);
                        catalogDisplayed = true; // Set flag to true if catalog is displayed
                        break;
                    case 6:
                        System.out.println("Filtering by TV Network...");
                        viewCatalog(inputScanner, connection, FilterOptions.BY_TV_NETWORK);
                        catalogDisplayed = true; // Set flag to true if catalog is displayed
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

    private void viewCatalog(Scanner inputScanner, Connection connection, FilterOptions filterOption) {        
        switch(filterOption) {
            case VIEW_ALL:
                catalog = loadCatalog(connection, FilterOptions.VIEW_ALL);
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

    // filterOption is the type of filter to apply, and filterOption1 is the specific value for that filter (if applicable)
    // For example, if filterOption is BY_DIRECTOR, then filterOption1 would be the director's name
    private static ArrayList<Shows> loadCatalog(Connection connection, FilterOptions filterOption, String filterOption1) {
        ArrayList<Shows> catalog = new ArrayList<>();

        PreparedStatement stmt = null;

        try {
            switch (filterOption) {
            case VIEW_ALL:
                stmt = connection.prepareStatement("SELECT * FROM shows");
                break;
            case BY_DIRECTOR:
                stmt = connection.prepareStatement("SELECT * FROM shows WHERE director = ?");
                stmt.setString(1, filterOption1); // Set the director's name in the prepared statement
                break;
            case BY_WRITER:
                stmt = connection.prepareStatement("SELECT * FROM shows WHERE writer = ?");
                stmt.setString(1, filterOption1); // Set the writer's name in the prepared statement
                break;
            case BY_ACTOR:
                stmt = connection.prepareStatement("SELECT * FROM shows WHERE actor = ?");
                stmt.setString(1, filterOption1); // Set the actor's name in the prepared statement
                break;
            case BY_GENRE:
                stmt = connection.prepareStatement("SELECT * FROM shows WHERE genre = ?");
                stmt.setString(1, filterOption1); // Set the genre in the prepared statement
                break;
            case BY_TV_NETWORK:
                stmt = connection.prepareStatement("SELECT * FROM shows WHERE tv_network = ?");
                stmt.setString(1, filterOption1); // Set the TV network in the prepared statement
                break;
            default:
                // This will never happen, but just in case, display all shows
                stmt = connection.prepareStatement("SELECT * FROM shows");
                break;
            }

            var resultSet = stmt.executeQuery();

            
        } catch (SQLException e) {
            System.out.println("An SQL error occurred while loading the catalog: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for debugging purposes
            return catalog; // Return an empty catalog if there's an error
        } catch (Exception e) {
            System.out.println("An error occurred while loading the catalog: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for debugging purposes
            return catalog; // Return an empty catalog if there's an error
        }

        return catalog;
    }

    private static void displayMenuOptions() {
        System.out.println("Menu options:");
        System.out.println("===================================");
        System.out.println("1. View All");
        System.out.println("2. By Director");
        System.out.println("3. By Writer");
        System.out.println("4. By Actor");
        System.out.println("5. By Genre");
        System.out.println("6. By TV Network");
        System.out.println("7. Back to Main Menu");
        System.out.println("===================================\n");
    }
}
