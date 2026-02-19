package com.example.eventtrackingapp_kwayisi;

public class Event {
    String name;
    String date;
    long eventTimeMillis;
    int userID;

    public Event(String name, String date, long eventTimeMillis, int userID){
        this.name = name;
        this.date = date;
        this.eventTimeMillis = eventTimeMillis;
        this.userID = userID;
    }

    public long getEventTimeMillis(){
        return eventTimeMillis;
    }
}
