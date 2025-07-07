# Project Overview

## App Name: BingeBoard
## Update Date: 7/7/2025

## Objective
This project serves to create a hub application where users can track the progress of their own viewing of TV shows. There is a database associated with all shows available to track (including its data which includes seasons, episodes, writers, actors, directors, genres, and tv networks), as well as user data (including each user's progress list).

## Scope
This project is designed and implemented keeping in mind the requirements specified in the Project Requirements Document(given by Cognixia; see Documentation folder). As a result, while 10 shows and their associated data are required, 20 shows, including all respective data are inserted in the database. Only the first 3 seasons, up to 10 episodes each are inserted given the timeline of 2 weeks and the requirement of manual data entry. The database is designed to be hosted locally. The app can be used by multiple users/

## Database
The database for BingeBoard includes 14 tables (see ER diagram within Documentation folder). These tables store data on show, user, and progress list information for BingeBoard app functionality. As stated in the scope section, there are only 20 shows entered into the database. User and progress list data for each user, is modified through the app. Show data is entered manually through SQL scripts as of now.

## App Structure
### Architecture: Model, View, Controller (MVC)

### Base Project Files (No Project)
  - BingeBoardRunner: Entry point for the app
  - ProgressListFilterOptions: Enum for progress list filter options
  - ShowFilterOptions: Enum for show filter options (currently only used for catalog feature)

### connection Package
  - ConnectionManager: Establishes the connection to the database, using data included in the config.properties file (credentials differ from system to system)

### dao Package (Model)
  - ProgressLists: Implements ProgressListsIntrfc; Stores the user's watch list, and has methods to update, remove from, and fetch the user's watch list
  - ProgressListsIntrfc: Defines required methods for any progress list classes implementing this
  - Shows: Implements ShowsIntrfc; Stores data relating to shows in the database, and has methods to set/get data based on show ID, and has a method to fetch all shows from the database
  - ShowsIntrfc: Defines required methods for any show classes implementing this
  - Users: Implements UsersIntrfc; Stores user data, and has methods to set/get user data, validate given credentials, insert a new user, and check if a username exists in the database
  - UsersIntrfc: Defines required methods for any user classes implementing this

### exceptions Package
  - ShowNotFoundException: Thrown when show is not found in the database (message by ID or by name)
  - ShowNotInProgListException: Thrown when show is not found in the user's progress list (message only by name)

### userinterface Package (View/Controller)
  - Login: Defines methods for the start page of the app, as well as the signin and signup pages
  - MainInterface: Defines the methods for the main menu of the app
  - RemoveFromProgressList: Defines the methods for the menu and logic of the remove-from-progress-list feature
  - UpdateProgressList: Defines the methods for the menu and logic of the update-progress-list and add-to-progress-list features
  - ViewCatalog: Defines the methods for the menu and logic to view shows in the database
  - ViewProgressList: Defines the methods for the menu and logic to view shows in the user's watch list

## Tests
Thorough manual unit, integration, and system tests were performed on this project. Automated tests may be added in the future, but as of this update, only manual tests were performed.



# To Test/Run This Project
## Database Setup
  - Navigate to "SQL Scripts" folder within this repo
  - Open and run in order: BingeBoard Schema Script > THEN > insert_shows_and_production_info > THEN > insert_episodes

## App Setup
  - Run the project
  - Follow the prompts to create an account
  - Utilize app features to manage your watch list, view catalog, and/or create new accounts, given the catalog of shows in the app, testing improper inputs along the way



# Limitations
  - Only the first 3 seasons, first 10 episodes each are included for each show
  - Only 20 shows are included
  - No integration with streaming platforms such as Hulu or Netflix, resulting in requirement for manual (not in app) show entry and manual (in app) progress entry
  - In rare cases, technically, a user can be created without a progress list. In this case, the app will exit, but the user may still exist. If this happens, admin team can manually remove the user from the database
  - This is a console app without a GUI



# Future Improvements
  - Include a scraper and/or API integration with streaming platforms such as Netflix or Hulu for show and user progress list data
  - Allow an optional first and last name for users, which would change the welcome message from displaying username to displaying first name
  - Include functionality for an admin user to add and remove shows and related data from the database
