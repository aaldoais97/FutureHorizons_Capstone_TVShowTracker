package com.cognixia.fh.bingeboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ShowsIntrfc {
    //Getters and Setters for Shows attributes - all fetched from db by id
    public int getId();
    public void setId(int id);
    public String getName();
    public void setName(Connection connection, int showId);
    public String getDirector();
    public void setDirector(Connection connection, int showId);
    public String getTvNetwork();
    public void setTvNetwork(Connection connection, int showId);
    public ArrayList<String> getGenres();
    public void setGenres(Connection connection, int showId);
    public ArrayList<String> getWriters();
    public void setWriters(Connection connection, int showId);
    public ArrayList<String> getActors();
    public void setActors(Connection connection, int showId);
    public int getEpisodeCount();
    public void setEpisodeCount(Connection connection, int showId);

    @Override
    public String toString();

    public static ArrayList<Shows> allShows(Connection connection) {
        ArrayList<Shows> showsList = new ArrayList<>();
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement("SELECT * FROM shows");

            ResultSet rs = stmt.executeQuery();
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
            System.out.println("Error preparing statement for all shows: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }

            return showsList;
    }
}
