package com.cognixia.fh.bingeboard.userinterface;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.cognixia.fh.bingeboard.ShowFilterOptions;
import com.cognixia.fh.bingeboard.dao.Shows;

/**
 * This class provides a user interface for viewing the TV show catalog.
 * It allows users to filter shows by various criteria such as director, writer, actor, genre, and TV network.
 */
public class ViewCatalog {
    ArrayList<Shows> catalog;

    /**
     * Constructor to initialize the catalog.
     * This constructor initializes the catalog as an empty ArrayList.
     */
    private ViewCatalog() {
        catalog = new ArrayList<>(); // Initialize the catalog
    }

    /**
     * This method displays the catalog menu and allows users to filter shows based on various criteria.
     * It prompts the user for their choice and displays the filtered catalog accordingly.
     *
     * @param inputScanner Scanner object for reading user input
     * @param connection Database connection object
     */
    static void displayMenu(Scanner inputScanner, Connection connection) {
        ViewCatalog catalogInstance = new ViewCatalog(); // Create an instance of Catalog to access its methods

        int choice;
        // Flag to check if the catalog has been displayed, in order to change prompt message
        boolean catalogDisplayed = false; 

        displayMenuOptions();   // Display the menu options for filtering the catalog

        // Loop to handle user input and menu selection
        // The loop continues until the user chooses to return to the main menu
        while(true) {
            // If the catalog has been displayed, prompt the user to return to the main menu or display the catalog menu again
            if (catalogDisplayed) {
                System.out.println("\nEnter '7' to return to the main menu or any other key to display catalog menu:");
                String nextChoice = inputScanner.nextLine();
                if (nextChoice.equals("7")) {
                    return; // Return to the main menu
                } else {
                    displayMenuOptions(); // Display the menu options again
                }
            } else {
                System.out.println("Please enter your choice:");
            }
            
            // Prompt the user for their choice and handle input exceptions
            try {
                choice = inputScanner.nextInt(); // Read the user's choice for filtering
                inputScanner.nextLine(); // Consume any leftover newline character
                System.out.println();   // Print a new line for better readability

                switch (choice) {
                    case 1:
                        // Load and display the entire catalog
                        catalogInstance.viewCatalog(inputScanner, connection, ShowFilterOptions.VIEW_ALL, catalogInstance.catalog);
                        catalogDisplayed = true; // Set flag to true if catalog is displayed
                        break;
                    case 2:
                        // Load and display the catalog filtered by director
                        catalogInstance.viewCatalog(inputScanner, connection, ShowFilterOptions.BY_DIRECTOR, catalogInstance.catalog);
                        catalogDisplayed = true; // Set flag to true if catalog is displayed
                        break;
                    case 3:
                        // Load and display the catalog filtered by writer
                        catalogInstance.viewCatalog(inputScanner, connection, ShowFilterOptions.BY_WRITER, catalogInstance.catalog);
                        catalogDisplayed = true; // Set flag to true if catalog is displayed
                        break;
                    case 4:
                        // Load and display the catalog filtered by actor
                        catalogInstance.viewCatalog(inputScanner, connection, ShowFilterOptions.BY_ACTOR, catalogInstance.catalog);
                        catalogDisplayed = true; // Set flag to true if catalog is displayed
                        break;
                    case 5:
                        // Load and display the catalog filtered by genre
                        catalogInstance.viewCatalog(inputScanner, connection, ShowFilterOptions.BY_GENRE, catalogInstance.catalog);
                        catalogDisplayed = true; // Set flag to true if catalog is displayed
                        break;
                    case 6:
                        // Load and display the catalog filtered by TV network
                        catalogInstance.viewCatalog(inputScanner, connection, ShowFilterOptions.BY_TV_NETWORK, catalogInstance.catalog);
                        catalogDisplayed = true; // Set flag to true if catalog is displayed
                        break;
                    case 7:
                        return; // Return to the main menu
                    default:
                        System.out.println("Invalid option. Please select from the options above.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number corresponding to the menu options."); 
                // Clear the scanner buffer
                inputScanner.nextLine(); // Consume the newline character left by nextInt()
            } catch (Exception e) {
                System.out.println("An error occurred while viewing catalog: " + e.getMessage());
                e.printStackTrace(); // Print the stack trace for debugging purposes
            }
        }
    }

    /**
     * This method displays the catalog based on the selected filter option.
     * It prompts the user for the necessary input based on the filter option and displays the filtered catalog.
     *
     * @param inputScanner Scanner object for reading user input
     * @param connection Database connection object
     * @param filterOption The filter option selected by the user
     * @param catalogArr The ArrayList to store the filtered catalog
     */
    private void viewCatalog(Scanner inputScanner, Connection connection, ShowFilterOptions filterOption, ArrayList<Shows> catalogArr) {        
        switch(filterOption) {
            case VIEW_ALL:
                // Load the entire catalog
                catalogArr = filterCatalog(connection, ShowFilterOptions.VIEW_ALL, "");
                break;
            case BY_DIRECTOR:
                // Prompt the user for the director's name
                System.out.println("Please enter the director's name:");
                String director = inputScanner.nextLine();
                
                // Load catalog based on director name
                catalogArr = filterCatalog(connection, ShowFilterOptions.BY_DIRECTOR, director);
                break;
            case BY_WRITER:
                // Prompt the user for the writer's name
                System.out.println("Please enter the writer's name:");
                String writer = inputScanner.nextLine();

                // Load catalog based on writer name
                catalogArr = filterCatalog(connection, ShowFilterOptions.BY_WRITER, writer);
                break;
            case BY_ACTOR:
                // Prompt the user for the actor's name
                System.out.println("Please enter the actor's name:");
                String actor = inputScanner.nextLine();

                // Load catalog based on actor name
                catalogArr = filterCatalog(connection, ShowFilterOptions.BY_ACTOR, actor);
                break;
            case BY_GENRE:
                // Prompt the user for the genre
                System.out.println("Please enter the genre:");
                String genre = inputScanner.nextLine();

                // Load catalog based on genre
                catalogArr = filterCatalog(connection, ShowFilterOptions.BY_GENRE, genre);
                break;
            case BY_TV_NETWORK:
                // Prompt the user for the TV network
                System.out.println("Please enter the TV network:");
                String tvNetwork = inputScanner.nextLine();

                // Load catalog based on TV network
                catalogArr = filterCatalog(connection, ShowFilterOptions.BY_TV_NETWORK, tvNetwork);
                break;
            default:
                System.out.println("Invalid filter option.");
        }

        // Display the filtered catalog
        if (!catalogArr.isEmpty()) {
            System.out.println("\nFiltered catalog based on your selection:");
            System.out.println("--------------------------------------------------");

            // Iterate through the catalog and display each show's details
            for (Shows show : catalogArr) {
                // Display the details of each show
                System.out.println(show.getName() + "\n\tDirector: " + show.getDirector() + 
                                   " \n\tTV Network: " + show.getTvNetwork() + 
                                   " \n\tGenres: " + String.join(", ", show.getGenres()) +
                                   " \n\tWriters: " + String.join(", ", show.getWriters()) +
                                   " \n\tActors: " + String.join(", ", show.getActors()) +
                                   " \n\tTotal Episodes: " + show.getEpisodeCount());
                System.out.println("--------------------------------------------------");
            }
        } else {
            System.out.println("No shows found for the selected filter.");
        }

    }

    /**
     * This method filters the catalog based on the selected filter option.
     * It retrieves shows from the database and filters them based on the specified criteria.
     *
     * @param connection Database connection object
     * @param filterOption The filter option selected by the user
     * @param filterOption1 The specific value for filtering (e.g., director's name, writer's name, etc.)
     * @return An ArrayList of Shows that match the filter criteria
     */
    private static ArrayList<Shows> filterCatalog(Connection connection, ShowFilterOptions filterOption, String filterOption1) {
        ArrayList<Shows> filteredCatalog = new ArrayList<>(); // Initialize an empty list to hold the filtered catalog
        ArrayList<Shows> allShows = new ArrayList<>(); // Initialize an empty list to hold all shows
        
        // Attempt to retrieve all shows from the database
        try {
            allShows = Shows.allShows(connection); // Default to loading all shows
        } catch (SQLException e) {
            System.out.println("Error retrieving shows from the database: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for debugging purposes
            return filteredCatalog; // Return empty catalog if an error occurs
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while retrieving shows: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for debugging purposes
            return filteredCatalog; // Return empty catalog if an unexpected error occurs

        }

        // Filter the catalog based on the selected filter option
        switch (filterOption) {
        case VIEW_ALL:
            // If viewing all, just return all shows
            filteredCatalog = allShows;
            break;
        case BY_DIRECTOR:
            // Filter shows by director's name
            filteredCatalog = allShows.stream()
                .filter(show -> show.getDirector().equalsIgnoreCase(filterOption1))
                .collect(Collectors.toCollection(ArrayList::new));
            break;
        case BY_WRITER:
            ArrayList<String> writers = new ArrayList<>();

            // Filter shows by writer's name
            filteredCatalog = allShows.stream()
                .filter(show -> show.getWriters().stream()
                    .anyMatch(writer -> writer.equalsIgnoreCase(filterOption1)))
                .collect(Collectors.toCollection(ArrayList::new));
            break;
        case BY_ACTOR:
            ArrayList<String> actors = new ArrayList<>();

            // Filter shows by actor's name
            filteredCatalog = allShows.stream()
                .filter(show -> show.getActors().stream()
                    .anyMatch(actor -> actor.equalsIgnoreCase(filterOption1)))
                .collect(Collectors.toCollection(ArrayList::new));
            break;
        case BY_GENRE:
            ArrayList<String> genres = new ArrayList<>();

            // Filter shows by genre
            filteredCatalog = allShows.stream()
                .filter(show -> show.getGenres().stream()
                    .anyMatch(genre -> genre.equalsIgnoreCase(filterOption1)))
                .collect(Collectors.toCollection(ArrayList::new));
            break;
        case BY_TV_NETWORK:
            // Filter shows by TV network
            filteredCatalog = allShows.stream()
                .filter(show -> show.getTvNetwork().equalsIgnoreCase(filterOption1))
                .collect(Collectors.toCollection(ArrayList::new));
            break;
        default:
            System.out.println("Invalid filter option.");
        }

        return filteredCatalog;
    }

    /**
     * This method displays the menu options for filtering the catalog.
     * It provides a list of available filter options for the user to choose from.
     */
    private static void displayMenuOptions() {
        System.out.println("\nMenu options:");
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
