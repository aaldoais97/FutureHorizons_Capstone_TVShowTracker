package com.cognixia.fh.bingeboard.dao;

public interface UsersIntrfc {
    public int getId(); // User ID will not be changed after creation

    public String getUsername();
    
    public void setUsername(String username);
    
    public String getPassword();
    
    public void setPassword(String password);
    
    public String getFirstName();
    
    public void setFirstName(String firstName);
    
    public String getLastName();
    
    public void setLastName(String lastName);
}   