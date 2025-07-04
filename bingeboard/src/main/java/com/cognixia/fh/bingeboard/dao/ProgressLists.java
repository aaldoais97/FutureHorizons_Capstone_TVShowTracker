package com.cognixia.fh.bingeboard.dao;

import java.sql.Connection;

public class ProgressLists implements ProgressListsIntrfc {
    int userId;
    int progListId;

    public ProgressLists(int userId, int progListId) {
        this.userId = userId;
        this.progListId = progListId;
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
