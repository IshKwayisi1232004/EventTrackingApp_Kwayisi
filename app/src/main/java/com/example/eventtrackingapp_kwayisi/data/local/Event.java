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

    private Event(String eventName, String eventDate){
        this.eventName = eventName;
        this.eventDate = eventDate;
    }
}
