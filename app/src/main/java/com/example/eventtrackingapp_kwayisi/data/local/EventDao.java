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
    void InsertEvent(Event event);

    @Query("SELECT * FROM events")
    List<Event> getAllEvents();

    @Update
    void gpdateEvent(Event event);

    @Delete
    void deleteEvent(Event event);
}
