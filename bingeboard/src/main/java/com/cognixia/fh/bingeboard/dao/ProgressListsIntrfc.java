package com.cognixia.fh.bingeboard.dao;

import java.util.ArrayList;

public interface ProgressListsIntrfc {
    public int getUserId();
    public ArrayList<ProgressLists.ShowProgress> getProgressList();

    @Override
    public String toString();
}
