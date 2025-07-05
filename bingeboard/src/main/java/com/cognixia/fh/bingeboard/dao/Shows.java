package com.cognixia.fh.bingeboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Shows implements ShowsIntrfc {
    int id;
    String name;
    String director;
    String tvNetwork;
    ArrayList<String> genres;
    ArrayList<String> writers;
    ArrayList<String> actors;;
    int episodeCount;

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

    @Override
    public void setName(Connection connection, int id) {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT name FROM shows WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                this.name = rs.getString("name");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching show name: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public String getDirector() {
        return director;
    }

    @Override
    public void setDirector(Connection connection, int showId) {
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
            System.out.println("Error fetching show director: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public String getTvNetwork() {
        return tvNetwork;
    }

    @Override
    public void setTvNetwork(Connection connection, int showId) {
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
            System.out.println("Error fetching show network: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<String> getGenres() {
        return genres;
    }

    @Override
    public void setGenres(Connection connection, int showId) {
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
            System.out.println("Error fetching show genres: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<String> getWriters() {
        return writers;
    }

    @Override
    public void setWriters(Connection connection, int showId) {
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
            System.out.println("Error fetching show writers: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<String> getActors() {
        return actors;
    }

    @Override
    public void setActors(Connection connection, int showId) {
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
            System.out.println("Error fetching show actors: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public int getEpisodeCount() {
        return episodeCount;
    }

    @Override
    public void setEpisodeCount(Connection connection, int showId) {
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
            System.out.println("Error fetching episode count: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static ArrayList<Shows> allShows (Connection connection) {
        return ShowsIntrfc.allShows(connection);
    }

    @Override
    public String toString() {
        return "Shows [id=" + id + ", name=" + name + ", director=" + director + ", tvNetwork=" + tvNetwork
                + ", genres=" + genres + ", writers=" + writers + ", actors=" + actors + ", episodeCount="
                + episodeCount + "]";
    }
}
