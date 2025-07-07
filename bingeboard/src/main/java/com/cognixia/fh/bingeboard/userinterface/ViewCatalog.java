package com.cognixia.fh.bingeboard.userinterface;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.cognixia.fh.bingeboard.ShowFilterOptions;
import com.cognixia.fh.bingeboard.dao.Shows;

// This method will display the menu for the user to filter shows.
public class ViewCatalog {
    ArrayList<Shows> catalog;

    private ViewCatalog() {
        catalog = new ArrayList<>(); // Initialize the catalog
    }

    static void displayMenu(Scanner inputScanner, Connection connection) {
        ViewCatalog catalogInstance = new ViewCatalog(); // Create an instance of Catalog to access its methods

        int choice;
        // Flag to check if the catalog has been displayed, in order to change prompt message
        boolean catalogDisplayed = false; 

        displayMenuOptions();

        while(true) {
            if (catalogDisplayed) {
                System.out.println("\nEnter '7' to return to the main menu or any other key to display catalog menu:");
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
                System.out.println();   // Print a new line for better readability

                switch (choice) {
                    case 1:
                        catalogInstance.viewCatalog(inputScanner, connection, ShowFilterOptions.VIEW_ALL, catalogInstance.catalog);
                        catalogDisplayed = true; // Set flag to true if catalog is displayed
                        break;
                    case 2:
                        catalogInstance.viewCatalog(inputScanner, connection, ShowFilterOptions.BY_DIRECTOR, catalogInstance.catalog);
                        catalogDisplayed = true; // Set flag to true if catalog is displayed
                        break;
                    case 3:
                        catalogInstance.viewCatalog(inputScanner, connection, ShowFilterOptions.BY_WRITER, catalogInstance.catalog);
                        catalogDisplayed = true; // Set flag to true if catalog is displayed
                        break;
                    case 4:
                        catalogInstance.viewCatalog(inputScanner, connection, ShowFilterOptions.BY_ACTOR, catalogInstance.catalog);
                        catalogDisplayed = true; // Set flag to true if catalog is displayed
                        break;
                    case 5:
                        catalogInstance.viewCatalog(inputScanner, connection, ShowFilterOptions.BY_GENRE, catalogInstance.catalog);
                        catalogDisplayed = true; // Set flag to true if catalog is displayed
                        break;
                    case 6:
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
                if (inputScanner.hasNextLine()) {
                    inputScanner.nextLine(); // Consume the newline character left by nextInt()
                }
            } catch (Exception e) {
                System.out.println("An error occurred while viewing catalog: " + e.getMessage());
                e.printStackTrace(); // Print the stack trace for debugging purposes
            }
        }
    }

    private void viewCatalog(Scanner inputScanner, Connection connection, ShowFilterOptions filterOption, ArrayList<Shows> catalogArr) {        
        switch(filterOption) {
            case VIEW_ALL:
                // Load the entire catalog
                catalogArr = loadCatalog(connection, ShowFilterOptions.VIEW_ALL, "");
                break;
            case BY_DIRECTOR:
                System.out.println("Please enter the director's name:");
                String director = inputScanner.nextLine();
                
                // Load catalog based on director name
                catalogArr = loadCatalog(connection, ShowFilterOptions.BY_DIRECTOR, director);
                break;
            case BY_WRITER:
                System.out.println("Please enter the writer's name:");
                String writer = inputScanner.nextLine();

                // Load catalog based on writer name
                catalogArr = loadCatalog(connection, ShowFilterOptions.BY_WRITER, writer);
                break;
            case BY_ACTOR:
                System.out.println("Please enter the actor's name:");
                String actor = inputScanner.nextLine();

                // Load catalog based on actor name
                catalogArr = loadCatalog(connection, ShowFilterOptions.BY_ACTOR, actor);
                break;
            case BY_GENRE:
                System.out.println("Please enter the genre:");
                String genre = inputScanner.nextLine();

                // Load catalog based on genre
                catalogArr = loadCatalog(connection, ShowFilterOptions.BY_GENRE, genre);
                break;
            case BY_TV_NETWORK:
                System.out.println("Please enter the TV network:");
                String tvNetwork = inputScanner.nextLine();

                // Load catalog based on TV network
                catalogArr = loadCatalog(connection, ShowFilterOptions.BY_TV_NETWORK, tvNetwork);
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

    // filterOption is the type of filter to apply, and filterOption1 is the specific value for that filter (if applicable)
    // For example, if filterOption is BY_DIRECTOR, then filterOption1 would be the director's name
    private static ArrayList<Shows> loadCatalog(Connection connection, ShowFilterOptions filterOption, String filterOption1) {
        ArrayList<Shows> loadedCat = new ArrayList<>();
        ArrayList<Shows> allShows = Shows.allShows(connection); // Default to loading all shows

        switch (filterOption) {
        case VIEW_ALL:
            loadedCat = allShows; // If viewing all, just return all shows
            break;
        case BY_DIRECTOR:
            loadedCat = allShows.stream()
                .filter(show -> show.getDirector().equalsIgnoreCase(filterOption1))
                .collect(Collectors.toCollection(ArrayList::new));
            break;
        case BY_WRITER:
            ArrayList<String> writers = new ArrayList<>();

            loadedCat = allShows.stream()
                .filter(show -> show.getWriters().stream()
                    .anyMatch(writer -> writer.equalsIgnoreCase(filterOption1)))
                .collect(Collectors.toCollection(ArrayList::new));
            break;
        case BY_ACTOR:
            ArrayList<String> actors = new ArrayList<>();

            loadedCat = allShows.stream()
                .filter(show -> show.getActors().stream()
                    .anyMatch(actor -> actor.equalsIgnoreCase(filterOption1)))
                .collect(Collectors.toCollection(ArrayList::new));
            break;
        case BY_GENRE:
            ArrayList<String> genres = new ArrayList<>();

            loadedCat = allShows.stream()
                .filter(show -> show.getGenres().stream()
                    .anyMatch(genre -> genre.equalsIgnoreCase(filterOption1)))
                .collect(Collectors.toCollection(ArrayList::new));
            break;
        case BY_TV_NETWORK:
            loadedCat = allShows.stream()
                .filter(show -> show.getTvNetwork().equalsIgnoreCase(filterOption1))
                .collect(Collectors.toCollection(ArrayList::new));
            break;
        default:
            System.out.println("Invalid filter option.");
        }

        return loadedCat;
    }

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
