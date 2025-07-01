package com.cognixia.fh.bingeboard.dao;

public class ShowProgressModel {
    int userId;
    int showId;
    int episodesCompleted;
    int totalEpisodes;

    public int getUserId() {
        return userId;
    }

    public int getShowId() {
        return showId;
    }

    public int getEpisodesCompleted() {
        return episodesCompleted;
    }
    public void setEpisodesCompleted(int episodesCompleted) {
        this.episodesCompleted = episodesCompleted;
    }
    public int getTotalEpisodes() {
        return totalEpisodes;
    }
}
