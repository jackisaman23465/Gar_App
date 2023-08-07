package com.example.aa.myapplication;

public class UserSpot {
    private int id = 0;
    private String username;
    private String password;

    public UserSpot(String username, String password){
        this(0, username, password);
    }

    public UserSpot(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(int id){
        return id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
