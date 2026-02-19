package com.example.eventtrackingapp_kwayisi.data.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EventDao {
    @Insert
    void insertEvent(Event event);

    @Query("SELECT * FROM events WHERE userID = :userID")
    List<Event> getAllEvents(int userID);

    @Update
    void updateEvent(Event event);

    @Delete
    void deleteEvent(Event event);
}
