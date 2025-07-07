package com.cognixia.fh.bingeboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * Shows is a class that represents a TV show in the database.
 * It contains attributes such as id, name, director, and network,
 * as well as methods to access and modify these attributes.
 */
public class Shows implements ShowsIntrfc {
    int id;
    String name;
    String director;
    String tvNetwork;
    ArrayList<String> genres;
    ArrayList<String> writers;
    ArrayList<String> actors;;
    int episodeCount;

    // Default constructor initializes all attributes to default values
    public Shows() {
        this.id = 0;
        this.name = "";
        this.genres = new ArrayList<>();
        this.director = "";
        this.writers = new ArrayList<>();
        this.actors = new ArrayList<>();
        this.episodeCount = 0;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * Method to set the name of the show.
     * It fetches the name from the database based on the show ID.
     */
    @Override
    public void setName(Connection connection, int id) throws SQLException, Exception {
        // Fetch the name of the show from the database using the provided ID
        try {
            PreparedStatement showNameStmt = connection.prepareStatement("SELECT name FROM shows WHERE id = ?");
            showNameStmt.setInt(1, id);
            ResultSet rs = showNameStmt.executeQuery();
            if (rs.next()) {
                this.name = rs.getString("name");
            }
        } catch (SQLException e) {
            throw e;    // Rethrow the exception to be handled by the caller
        } catch (Exception e) {
            throw e;    // Rethrow the unexpected exception
        }
    }

    @Override
    public String getDirector() {
        return director;
    }

    /**
     * Method to set the director of the show.
     * It fetches the director's name from the database based on the show ID.
     */
    @Override
    public void setDirector(Connection connection, int showId) throws SQLException, Exception {
        try {
            PreparedStatement stmt = connection.prepareStatement("""
                                                                 select directors.name
                                                                 from directors
                                                                 inner join shows
                                                                 \ton directors.id = shows.director_id
                                                                 where shows.id = ?;""");
            stmt.setInt(1, showId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                this.director = rs.getString("name");
            }
        } catch (SQLException e) {
            throw e;    // Rethrow the exception to be handled by the caller
        } catch (Exception e) {
            throw e;    // Rethrow the unexpected exception
        }
    }

    @Override
    public String getTvNetwork() {
        return tvNetwork;
    }

    /**
     * Method to set the TV network of the show.
     * It fetches the network name from the database based on the show ID.
     */
    @Override
    public void setTvNetwork(Connection connection, int showId) throws SQLException, Exception {
        try {
            PreparedStatement stmt = connection.prepareStatement("""
                                                                 select networks.name
                                                                 from networks
                                                                 inner join shows
                                                                 \ton networks.id = shows.network_id
                                                                 where shows.id = ?;""");
            stmt.setInt(1, showId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                this.tvNetwork = rs.getString("name");
            }
        } catch (SQLException e) {
            throw e;    // Rethrow the exception to be handled by the caller
        } catch (Exception e) {
            throw e;    // Rethrow the unexpected exception
        }
    }

    @Override
    public ArrayList<String> getGenres() {
        return genres;
    }

    /**
     * Method to set the genres of the show.
     * It fetches the genres from the database based on the show ID.
     */
    @Override
    public void setGenres(Connection connection, int showId) throws SQLException, Exception {
        try {
            PreparedStatement stmt = connection.prepareStatement("""
                                                                 select genres.name
                                                                 from genres
                                                                 inner join shows_genres
                                                                 on genres.id = shows_genres.genre_id
                                                                 inner join shows
                                                                 on shows.id = shows_genres.show_id
                                                                 where shows.id = ?
                                                                 order by genres.name;""");
            stmt.setInt(1, showId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                this.genres.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            throw e;    // Rethrow the exception to be handled by the caller
        } catch (Exception e) {
            throw e;    // Rethrow the unexpected exception
        }
    }

    @Override
    public ArrayList<String> getWriters() {
        return writers;
    }

    /**
     * Method to set the writers of the show.
     * It fetches the writers from the database based on the show ID.
     */
    @Override
    public void setWriters(Connection connection, int showId) throws SQLException, Exception {
        try {
            PreparedStatement stmt = connection.prepareStatement("""
                                                                 select writers.name
                                                                 from writers
                                                                 inner join shows_writers
                                                                 \ton writers.id = shows_writers.writer_id
                                                                 inner join shows
                                                                 \ton shows.id = shows_writers.show_id
                                                                 where shows.id = ?
                                                                 order by writers.name;""");
            stmt.setInt(1, showId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                this.writers.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            throw e;    // Rethrow the exception to be handled by the caller
        } catch (Exception e) {
            throw e;    // Rethrow the unexpected exception
        }
    }

    @Override
    public ArrayList<String> getActors() {
        return actors;
    }

    /**
     * Method to set the actors of the show.
     * It fetches the actors from the database based on the show ID.
     */
    @Override
    public void setActors(Connection connection, int showId) throws SQLException, Exception {
        try {
            PreparedStatement stmt = connection.prepareStatement("""
                                                                 select actors.name
                                                                 from actors
                                                                 inner join shows_actors
                                                                 \ton actors.id = shows_actors.actor_id
                                                                 inner join shows
                                                                 \ton shows.id = shows_actors.show_id
                                                                 where shows.id = ?
                                                                 order by actors.name;""");
            stmt.setInt(1, showId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                this.actors.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            throw e;    // Rethrow the exception to be handled by the caller
        } catch (Exception e) {
            throw e;    // Rethrow the unexpected exception
        }
    }

    @Override
    public int getEpisodeCount() {
        return episodeCount;
    }

    /**
     * Method to set the episode count of the show.
     * It fetches the count of episodes from the database based on the show ID.
     */
    @Override
    public void setEpisodeCount(Connection connection, int showId) throws SQLException, Exception {
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
                this.episodeCount = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw e;    // Rethrow the exception to be handled by the caller
        } catch (Exception e) {
            throw e;    // Rethrow the unexpected exception
        }
    }

    public static ArrayList<Shows> allShows (Connection connection) throws SQLException, Exception {
        return ShowsIntrfc.allShows(connection);
    }

    @Override
    public String toString() {
        return "Shows [id=" + id + ", name=" + name + ", director=" + director + ", tvNetwork=" + tvNetwork
                + ", genres=" + genres + ", writers=" + writers + ", actors=" + actors + ", episodeCount="
                + episodeCount + "]";
    }
}
