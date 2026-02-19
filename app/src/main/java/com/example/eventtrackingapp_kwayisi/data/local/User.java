package com.example.eventtrackingapp_kwayisi.data.local;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int userID;

    private String username;
    private String password;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public int getUserID(){
        return userID;
    }

    public void setUserID(int id){
        this.userID = id;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

}
