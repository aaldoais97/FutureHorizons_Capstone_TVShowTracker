package com.cognixia.fh.bingeboard.dao;

import java.util.ArrayList;

public interface ProgressListsIntrfc {
    // Getters for ProgressLists attributes
    public int getUserId();
    public ArrayList<ProgressLists.showProgress> getProgressList();

    @Override
    public String toString();
}
