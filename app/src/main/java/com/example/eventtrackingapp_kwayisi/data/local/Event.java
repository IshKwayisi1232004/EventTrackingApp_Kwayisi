package com.example.eventtrackingapp_kwayisi.data.local;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "events")
public class Event {
    @PrimaryKey(autoGenerate = true)
    private int eventID;

    private String eventName;
    private String eventDate;

    public Event(String eventName, String eventDate){
        this.eventName = eventName;
        this.eventDate = eventDate;
    }

    public int getEventID(){
        return eventID;
    }
    public void setEventID(int id){
        this.eventID = id;
    }

    public String getEventName(){
        return eventName;
    }

    public String getEventDate(){
        return eventDate;
    }
}
