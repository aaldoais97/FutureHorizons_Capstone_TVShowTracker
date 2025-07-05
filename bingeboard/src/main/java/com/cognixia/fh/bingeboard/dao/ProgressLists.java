package com.cognixia.fh.bingeboard.dao;

import java.sql.Connection;
import java.util.ArrayList;

public class ProgressLists implements ProgressListsIntrfc {
    class showProgress {
        int showId;
        int totalEpisodes;
        int watchedEpisodes;
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
