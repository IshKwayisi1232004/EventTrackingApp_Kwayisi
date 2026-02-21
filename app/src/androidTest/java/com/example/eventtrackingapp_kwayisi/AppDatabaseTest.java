package com.example.eventtrackingapp_kwayisi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteException;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.eventtrackingapp_kwayisi.data.local.AppDatabase;
import com.example.eventtrackingapp_kwayisi.data.local.Event;
import com.example.eventtrackingapp_kwayisi.data.local.EventDao;
import com.example.eventtrackingapp_kwayisi.data.local.User;
import com.example.eventtrackingapp_kwayisi.data.local.UserDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class AppDatabaseTest {

    private AppDatabase db;
    private UserDao userDao;
    private EventDao eventDao;

    @Before
    public void createDb() {

        Context context =
                ApplicationProvider.getApplicationContext();

        db = Room.inMemoryDatabaseBuilder(
                        context,
                        AppDatabase.class
                )
                .allowMainThreadQueries()
                .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        db.execSQL("PRAGMA foreign_keys=ON;");
                    }

                    @Override
                    public void onOpen(@NonNull SupportSQLiteDatabase db) {
                        super.onOpen(db);
                        db.execSQL("PRAGMA foreign_keys=ON;");
                    }
                })
                .build();

        userDao = db.userDao();
        eventDao = db.eventDao();

        db.getOpenHelper().getWritableDatabase()
                .query("PRAGMA foreign_keys;");
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void insertUser_savesUserCorrectly() {

        User user = new User("testuser", "123456");
        long id = userDao.insertUser(user);

        assertTrue(id > 0);
    }

    @Test
    public void loginWithCorrectCredentials(){
        User user = new User("userTest", "123456");
        userDao.insertUser(user);

        User found = userDao.getUser("userTest");

        assertEquals("123456", found.getPassword());
    }

    @Test
    public void insertEvent(){
        User user = new User("userTest", "123456");
        long userID = userDao.insertUser(user);

        Event event = new Event(
                "Meeting",
                "02/20/2026 12:30",
                System.currentTimeMillis(),
                (int) userID
        );

        eventDao.insertEvent(event);

        List<Event> events = eventDao.getAllEvents((int) userID);

        assertEquals(1, events.size());
    }

    @Test
    public void insertEvent_withoutValidUser_shouldFail(){
        Event event = new Event(
                "No Event",
                "02/20/2026 12:30",
                System.currentTimeMillis(),
                9999
        );

        try {
            eventDao.insertEvent(event);
            fail("Expected SQLiteConstraintException");
        }
        catch (SQLiteConstraintException e){
            assertTrue(e.getMessage().contains("FOREIGN KEY"));
        }
    }
}