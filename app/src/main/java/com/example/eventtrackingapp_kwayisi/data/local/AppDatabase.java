package com.example.eventtrackingapp_kwayisi.data.local;

import android.content.Context;

import androidx.room.RoomDatabase;
import androidx.room.Database;
import androidx.room.Room;

@Database(entities = {User.class, Event.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{
    private static AppDatabase INSTANCE;

    public abstract UserDao userDao();
    public abstract  EventDao eventDao();

    public static synchronized  AppDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    "event_database"
            ).build();
        }
        return INSTANCE;
    }
}
