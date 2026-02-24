package com.example.eventtrackingapp_kwayisi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eventtrackingapp_kwayisi.data.local.AppDatabase;
import com.example.eventtrackingapp_kwayisi.data.local.Event;
import com.example.eventtrackingapp_kwayisi.data.local.EventDao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EditEventActivity extends AppCompatActivity {

    EditText eventNameInput, eventDateInput;
    Button updateButton;

    EventDao eventDao;
    int eventID;
    int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);

        eventNameInput = findViewById(R.id.eventNameInput);
        eventDateInput = findViewById(R.id.eventDateInput);
        updateButton = findViewById(R.id.updateButton);

        AppDatabase db = AppDatabase.getInstance(this);
        eventDao = db.eventDao();

        SharedPreferences prefs = getSharedPreferences("session", MODE_PRIVATE);
        userID = prefs.getInt("userID", -1);

        Intent intent = getIntent();
        eventID = intent.getIntExtra("event_id", -1);

        eventNameInput.setText(intent.getStringExtra("event_name"));
        eventDateInput.setText(intent.getStringExtra("event_date"));

        updateButton.setOnClickListener(v -> {
            String name = eventNameInput.getText().toString().trim();
            String dateString = eventDateInput.getText().toString().trim();

            try {
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
                Date date = sdf.parse(dateString);
                long timeMillis = date.getTime();

                Event updatedEvent = new Event(name, dateString, timeMillis, userID);
                updatedEvent.setEventID(eventID);

                new Thread(() -> {
                    eventDao.updateEvent(updatedEvent);
                    runOnUiThread(this::finish);
                }).start();

            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }
}