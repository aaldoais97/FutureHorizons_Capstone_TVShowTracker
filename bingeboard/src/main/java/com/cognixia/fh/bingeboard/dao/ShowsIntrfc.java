package com.cognixia.fh.bingeboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ShowsIntrfc {
    public int getId();
    public void setId(int id);
    public String getName();
    public void setName(Connection connection, int showId) throws SQLException, Exception;
    public String getDirector();
    public void setDirector(Connection connection, int showId) throws SQLException, Exception;
    public String getTvNetwork();
    public void setTvNetwork(Connection connection, int showId) throws SQLException, Exception;
    public ArrayList<String> getGenres();
    public void setGenres(Connection connection, int showId) throws SQLException, Exception;
    public ArrayList<String> getWriters();
    public void setWriters(Connection connection, int showId) throws SQLException, Exception;
    public ArrayList<String> getActors();
    public void setActors(Connection connection, int showId) throws SQLException, Exception;
    public int getEpisodeCount();
    public void setEpisodeCount(Connection connection, int showId) throws SQLException, Exception;

    @Override
    public String toString();

    /**
     * Static method to fetch all shows from the database.
     */
    public static ArrayList<Shows> allShows(Connection connection) throws SQLException, Exception {
        ArrayList<Shows> showsList = new ArrayList<>();
        PreparedStatement allShowsStmt = null;

        // Fetch all shows from the database
        try {
            allShowsStmt = connection.prepareStatement("SELECT * FROM shows");

            ResultSet rs = allShowsStmt.executeQuery();
            while (rs.next()) {
                Shows show = new Shows();
                show.setId(rs.getInt("id"));
                show.setName(connection, show.getId());
                show.setDirector(connection, show.getId());
                show.setTvNetwork(connection, show.getId());
                show.setGenres(connection, show.getId());
                show.setWriters(connection, show.getId());
                show.setActors(connection, show.getId());
                show.setEpisodeCount(connection, show.getId());
                showsList.add(show);
            }
        } catch (SQLException e) {
            throw e;    // Rethrow the exception to be handled by the caller
        } catch (Exception e) {
            throw e;    // Rethrow the unexpected exception
        }

            return showsList;
    }
}
