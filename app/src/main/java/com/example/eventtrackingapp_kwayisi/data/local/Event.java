package com.example.eventtrackingapp_kwayisi.data.local;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "events",
        foreignKeys = @ForeignKey(
                entity = User.class,
                parentColumns = "userID",
                childColumns = "userID",
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE
        )
)

public class Event {
    @PrimaryKey(autoGenerate = true)
    private int eventID;

    private int userID;
    private String eventName;
    private String eventDate;

    private long eventTimeMillis;

    public Event(String eventName, String eventDate, long eventTimeMillis, int userID){
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTimeMillis = eventTimeMillis;
        this.userID = userID;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public int getUserID() {
        return userID;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public long getEventTimeMillis() {
        return eventTimeMillis;
    }
}
