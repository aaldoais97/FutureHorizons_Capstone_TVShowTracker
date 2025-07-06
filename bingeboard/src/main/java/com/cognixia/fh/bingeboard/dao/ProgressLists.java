package com.cognixia.fh.bingeboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProgressLists implements ProgressListsIntrfc {
    final class showProgress {
        String showName;
        int totalEpisodes;
        int watchedEpisodes;

        // to do: make this throw shownotfound exception as well as sql and other exceptions
        public showProgress(Connection connection, String showName) {
            // Check if the showName exists in the database
            try {
                PreparedStatement stmt = connection.prepareStatement("SELECT id FROM shows WHERE name = ?");
                stmt.setString(1, showName);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    int showId = rs.getInt("id");
                    this.setTotalEpisodes(connection, showId); // Set total episodes from the database
                } else {
                    System.out.println("Show not found: " + showName);
                    return; // Exit if the show is not found
                }
            } catch (SQLException e) {
                System.out.println("Error fetching show ID: " + e.getMessage());
                e.printStackTrace();
                return; // Exit if there's an error fetching the show ID
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
                e.printStackTrace();
                return; // Exit if there's an unexpected error
            }

            this.showName = showName;
            this.watchedEpisodes = 0; // Default to 0 until set
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

        // to do: make this throw sqlexception and other exceptions
        public void setTotalEpisodes(Connection connection, int showId) {
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
                }
            } catch (SQLException e) {
                System.out.println("Error fetching episode count: " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    int userId;
    int progListId;
    ArrayList<showProgress> progressList;

    public ProgressLists(int userId, int progListId) {
        this.userId = userId;
        this.progListId = progListId;
        this.progressList = new ArrayList<>();
    }

    public int getUserId() {
        return userId;
    }

    public int getProgListId() {
        return progListId;
    }

    public static ProgressLists createProgressList(Connection connection, int userId) {
        return ProgressListsIntrfc.createProgressList(connection, userId); // Pass null for connection as this is a placeholder
    }
}
