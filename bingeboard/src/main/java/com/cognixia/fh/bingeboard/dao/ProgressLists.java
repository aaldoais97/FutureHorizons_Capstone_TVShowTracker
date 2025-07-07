package com.cognixia.fh.bingeboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import com.cognixia.fh.bingeboard.exceptions.ShowNotFoundException;
import com.cognixia.fh.bingeboard.exceptions.ShowNotInProgListException;

/**
 * ProgressLists is a class that manages the progress of shows in a user's watch list.
 * It provides methods to add, update, and retrieve the progress of shows.
 */
public class ProgressLists implements ProgressListsIntrfc {
    // Inner class to represent the progress of a single show
    final public class ShowProgress {
        String showName;
        int showId;
        int totalEpisodes;
        int episodesWatched;

        public ShowProgress(Connection connection, String showName, int userId) throws SQLException, ShowNotFoundException {
            // Check if the showName exists in the database
            try {
                PreparedStatement showIdStmt = connection.prepareStatement("SELECT id FROM shows WHERE name = ?");
                showIdStmt.setString(1, showName);
                ResultSet rs = showIdStmt.executeQuery();
                if (rs.next()) {
                    this.showId = rs.getInt("id");
                    this.setTotalEpisodes(connection, showId); // Set total episodes from the database
                } else {
                    throw new ShowNotFoundException(showName);
                }
            } catch (SQLException e) {
                throw e; // Rethrow the SQLException
            } catch (ShowNotFoundException e) {
                throw e; // Rethrow the ShowNotFoundException
            } catch (Exception e) {
                throw e; // Rethrow the unexpected exception
            }

            // Once the showId is set, we can safely set the showName and episodesWatched
            this.showName = showName;
            this.episodesWatched = 0; // Default to 0 until set

            // Fetch the number of episodes watched by the user for this show
            try {
                PreparedStatement watchedStmt = connection.prepareStatement("""
                                                                    select episodes_completed
                                                                    from shows_progress_lists
                                                                    inner join progress_lists
                                                                    \ton shows_progress_lists.progress_list_id = progress_lists.id
                                                                    inner join shows
                                                                    \ton shows_progress_lists.show_id = shows.id
                                                                    where shows.id = ? and progress_lists.id = ?;""");
                watchedStmt.setInt(1, showId);
                watchedStmt.setInt(2, userId);
                ResultSet rs = watchedStmt.executeQuery();
                if (rs.next()) {
                    this.episodesWatched = rs.getInt("episodes_completed");
                }
            } catch (SQLException e) {
                System.out.println("Error fetching watched episodes: " + e.getMessage());
                throw e;
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
                throw e;
            }
        }

        public String getShowName() {
            return showName;
        }

        public int getShowId() {
            return showId;
        }

        public int getTotalEpisodes() {
            return totalEpisodes;
        }

        public int getEpisodesWatched() {
            return episodesWatched;
        }

        /**
         * Method to watch a certain number of episodes of the show.
         * It updates the episodesWatched count and ensures it does not exceed totalEpisodes.
         */
        public void watchEpisodes(int episodesWatched) {
            // Validate the number of episodes watched
            if (episodesWatched < 0) {
                System.out.println("Cannot watch a negative number of episodes.");
                return;
            }

            // Update the episodesWatched count
            this.episodesWatched += episodesWatched;

            // Ensure episodesWatched does not exceed totalEpisodes
            if (this.episodesWatched > this.totalEpisodes) {
                System.out.println("You cannot watch more episodes than the total available.");
                this.episodesWatched = this.totalEpisodes; // Cap to total episodes
            }
            
        }

        /**
         * Method to set the total number of episodes for the show.
         * It fetches the count from the database based on the showId.
         */
        public void setTotalEpisodes(Connection connection, int showId) throws SQLException, ShowNotFoundException {
            // Fetch the total number of episodes for the show from the database
            try {
                PreparedStatement totalEpisodeStmt = connection.prepareStatement("""
                                                                    select count(episodes.id)
                                                                    from episodes
                                                                    inner join seasons
                                                                    \ton episodes.season_id = seasons.id
                                                                    inner join shows
                                                                    \ton seasons.show_id = shows.id
                                                                    where shows.id = ?;""");
                totalEpisodeStmt.setInt(1, showId);
                ResultSet rs = totalEpisodeStmt.executeQuery();
                if (rs.next()) {
                    this.totalEpisodes = rs.getInt(1);
                } else {
                    throw new ShowNotFoundException(showId);
                }
            } catch (SQLException e) {
                throw e;
            } catch (Exception e) {
                throw e;
            }
        }

        @Override
        public String toString() {
            return "Show: " + showName + ", Total Episodes: " + totalEpisodes + ", Watched Episodes: " + episodesWatched;
        }
    }


    /*
     * ProgressLists class represents a user's progress list for shows.
     * It contains methods to manage the progress of shows, including updating
     * shows in (adding if not in list) removing shows from, and viewing the
     * progress list.
     */
    private int userId;
    private ArrayList<ShowProgress> progressList;

    /**
     * Constructor to initialize the ProgressLists for a user.
     * It fetches the user's progress list from the database and populates it.
     *
     * @param connection The database connection
     * @param userId The ID of the user whose progress list is to be initialized
     * @throws SQLException If there is an error accessing the database
     * @throws Exception If there is an unexpected error
     */
    public ProgressLists(Connection connection, int userId) throws SQLException, Exception {
        this.userId = userId;
        this.progressList = new ArrayList<>();

        // Initialize the progress list for the user
        try {
            PreparedStatement progListStmt = connection.prepareStatement("""
                                                                    select shows.name
                                                                    from shows
                                                                    inner join shows_progress_lists
                                                                    \ton shows.id = shows_progress_lists.show_id
                                                                    inner join progress_lists
                                                                    \ton shows_progress_lists.progress_list_id = progress_lists.id
                                                                    where progress_lists.id = ?;""");
            progListStmt.setInt(1, userId);
            ResultSet rs = progListStmt.executeQuery();
            while (rs.next()) {
                String showName = rs.getString("name");
                this.progressList.add(new ShowProgress(connection, showName, userId));
            }
        } catch (SQLException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public int getUserId() {
        return userId;
    }

    @Override
    public ArrayList<ShowProgress> getProgressList() {
        return progressList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Progress List for User ID: ").append(userId).append("\n");
        for (ShowProgress show : progressList) {
            sb.append(show.toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Method to update the progress list by adding or updating a show.
     * If the show already exists, it updates the watched episodes.
     * If not, it creates a new ShowProgress object and adds it to the list.
     *
     * @param connection The database connection
     * @param showName The name of the show to be added or updated
     * @param episodesWatched The number of episodes watched
     * @throws ShowNotFoundException If the show is not found in the database
     * @throws SQLException If there is an error accessing the database
     */
    public void updateProgressList(Connection connection, String showName, int episodesWatched) throws ShowNotFoundException, SQLException {
        // Check if the show already exists in the progress list. If so, update its watched episodes
        for (ShowProgress show : progressList) {
            if (show.getShowName().equalsIgnoreCase(showName)) {
                try {
                    show.watchEpisodes(episodesWatched); // Update the watched episodes for the existing show
                    PreparedStatement stmt = connection.prepareStatement("""
                                                                    update shows_progress_lists
                                                                    set episodes_completed = ?
                                                                    where show_id = ? and progress_list_id = ?;""");
                    stmt.setInt(1, show.getEpisodesWatched());
                    stmt.setInt(2, show.getShowId());
                    stmt.setInt(3, this.userId);
                    stmt.executeUpdate();
                } catch (SQLException e) {
                    throw e; // Exit if an error occurs
                } catch (Exception e) {
                    throw e; // Exit if an unexpected error occurs
                }

                return;
            }
        }

        // If not, create a new showProgress object and add it to the list
        try {
            ShowProgress newShow = new ShowProgress(connection, showName, userId);
            newShow.watchEpisodes(episodesWatched); // Set the watched episodes for the new show
            progressList.add(newShow);
        } catch (ShowNotFoundException e) {
            throw e;
        } catch (SQLException e) {
            throw e; // Rethrow the exception to be handled by the caller
        } catch (Exception e) {
            throw e; // Rethrow the exception to be handled by the caller
        }
    }

    /**
     * Method to remove a show from the progress list.
     * It checks if the show exists in the list and removes it if found.
     * If not found, it throws a ShowNotInProgListException.
     *
     * @param connection The database connection
     * @param showName The name of the show to be removed
     * @throws SQLException If there is an error accessing the database
     * @throws ShowNotInProgListException If the show is not found in the progress list
     */
    public void removeFromProgressList(Connection connection, String showName) throws SQLException, ShowNotInProgListException {
        // Remove the show from the progress list if it exists
        Optional<ShowProgress> showToRemove = progressList.stream()
                                            .filter(show -> show.getShowName().equalsIgnoreCase(showName))
                                            .findFirst();

        if (showToRemove.isPresent()) {
            progressList.remove(showToRemove.get());
        } else {
            throw new ShowNotInProgListException(showName);
        }
        
        int showId = 0; // Initialize showId to 0

        // Fetch the showId from the database using the showName
        try {
            PreparedStatement showIdStmt = connection.prepareStatement("SELECT id FROM shows WHERE name = ?");
            showIdStmt.setString(1, showName);
            ResultSet rs = showIdStmt.executeQuery();
            if (rs.next()) {
                showId = rs.getInt("id");
            } else {
                throw new ShowNotInProgListException(showName);
            }

            // Remove the show from the progress list in the database
            PreparedStatement deleteStmt = connection.prepareStatement("""
                                                DELETE FROM shows_progress_lists
                                                WHERE shows_progress_lists.show_id = ? AND shows_progress_lists.progress_list_id = ?;""");
            
            deleteStmt.setInt(1, showId);
            deleteStmt.setInt(2, this.userId);
            deleteStmt.executeUpdate();
        } catch (SQLException e) {
            throw e; // Rethrow the exception to be handled by the caller
        } catch (Exception e) {
            throw e; // Rethrow the exception to be handled by the caller
        }
    }
}
