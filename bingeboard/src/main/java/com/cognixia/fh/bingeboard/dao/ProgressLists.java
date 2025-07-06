package com.cognixia.fh.bingeboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cognixia.fh.bingeboard.exceptions.ShowNotFoundException;

public class ProgressLists implements ProgressListsIntrfc {
    // Inner class to represent the progress of a single show
    final public class showProgress {
        String showName;
        int totalEpisodes;
        int watchedEpisodes;

        public showProgress(Connection connection, String showName, int userId) throws SQLException, ShowNotFoundException {
            // Check if the showName exists in the database
            int showId = 0; // Initialize showId to 0
            try {
                PreparedStatement stmt = connection.prepareStatement("SELECT id FROM shows WHERE name = ?");
                stmt.setString(1, showName);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    showId = rs.getInt("id");
                    this.setTotalEpisodes(connection, showId); // Set total episodes from the database
                } else {
                    throw new ShowNotFoundException(showName);
                }
            } catch (SQLException e) {
                System.out.println("Error fetching show ID: " + e.getMessage());
                throw e; // Rethrow the SQLException
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
                throw e; // Rethrow the unexpected exception
            }

            this.showName = showName;
            this.watchedEpisodes = 0; // Default to 0 until set

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
                    this.watchedEpisodes = rs.getInt("episodes_completed");
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

        public int getTotalEpisodes() {
            return totalEpisodes;
        }

        public int getWatchedEpisodes() {
            return watchedEpisodes;
        }

        public void watchEpisodes(int episodesWatched) {
            if (episodesWatched < 0) {
                System.out.println("Cannot watch a negative number of episodes.");
                return;
            }
            this.watchedEpisodes += episodesWatched;
            if (this.watchedEpisodes > this.totalEpisodes) {
                System.out.println("You cannot watch more episodes than the total available.");
                this.watchedEpisodes = this.totalEpisodes; // Cap to total episodes
            }
        }

        public void setTotalEpisodes(Connection connection, int showId) throws SQLException, ShowNotFoundException {
            try {
                PreparedStatement stmt = connection.prepareStatement("""
                                                                    select count(episodes.id)
                                                                    from episodes
                                                                    inner join seasons
                                                                    \ton episodes.season_id = seasons.id
                                                                    inner join shows
                                                                    \ton seasons.show_id = shows.id
                                                                    where shows.id = ?;""");
                stmt.setInt(1, showId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    this.totalEpisodes = rs.getInt(1);
                } else {
                    throw new ShowNotFoundException("Show with ID " + showId + " not found.");
                }
            } catch (SQLException e) {
                System.out.println("Error fetching episode count: " + e.getMessage());
                throw e;
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
                throw e;
            }
        }

        @Override
        public String toString() {
            return "Show: " + showName + ", Total Episodes: " + totalEpisodes + ", Watched Episodes: " + watchedEpisodes;
        }
    }



    private int userId;
    private int progListId;
    private ArrayList<showProgress> progressList;

    public ProgressLists(Connection connection, int userId) throws SQLException, Exception {
        this.userId = userId;
        this.progListId = userId;
        this.progressList = new ArrayList<>();

        // Initialize the progress list for the user
        try {
            PreparedStatement stmt = connection.prepareStatement("""
                                                                    select shows.name
                                                                    from shows
                                                                    inner join shows_progress_lists
                                                                    \ton shows.id = shows_progress_lists.show_id
                                                                    inner join progress_lists
                                                                    \ton shows_progress_lists.progress_list_id = progress_lists.id
                                                                    where progress_lists.id = ?;""");
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String showName = rs.getString("name");
                this.progressList.add(new showProgress(connection, showName, userId));
            }
        } catch (SQLException e) {
            System.out.println("Error initializing progress list: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            throw e;
        }
    }

    public int getUserId() {
        return userId;
    }

    public int getProgListId() {
        return progListId;
    }

    public ArrayList<showProgress> getProgressList() {
        return progressList;
    }

    public static ProgressLists createProgressList(Connection connection, int userId) {
        return ProgressListsIntrfc.createProgressList(connection, userId); // Pass null for connection as this is a placeholder
    }
}
