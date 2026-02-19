package com.example.eventtrackingapp_kwayisi;
import com.example.eventtrackingapp_kwayisi.data.local.AppDatabase;
import com.example.eventtrackingapp_kwayisi.data.local.EventDao;
import com.example.eventtrackingapp_kwayisi.data.local.Event;
import com.example.eventtrackingapp_kwayisi.data.utils.SMSHelper;


import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EventGridActivity extends AppCompatActivity{

    EditText eventNameInput, eventDateInput;
    Button addEventButton;
    RecyclerView eventRecyclerView;

    ArrayList<Event> eventList;
    EventAdapter adapter;

    EventDao eventDao;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_grid);

        // Bind UI
        eventNameInput = findViewById(R.id.eventNameInput);
        eventDateInput = findViewById(R.id.eventDateInput);
        addEventButton = findViewById(R.id.addEventButton);
        eventRecyclerView = findViewById(R.id.eventRecyclerView);

        // Data source
        eventList = new ArrayList<>();

        // RecyclerView setup
        adapter = new EventAdapter(eventList);
        eventRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        eventRecyclerView.setAdapter(adapter);

        AppDatabase db = AppDatabase.getInstance(this);
        eventDao = db.eventDao();

        SharedPreferences prefs = getSharedPreferences("session", MODE_PRIVATE);
        int userID = prefs.getInt("userID", -1);

        new Thread(() -> {
            List<Event> userEvents = eventDao.getAllEvents(userID);

            runOnUiThread(() -> {
                eventList.clear();
                eventList.addAll(userEvents);
                adapter.notifyDataSetChanged();
            });
        }).start();

        // Add button logic
        addEventButton.setOnClickListener(v -> {
            String name = eventNameInput.getText().toString().trim();
            String dateString = eventDateInput.getText().toString().trim();

            if (!name.isEmpty() && !dateString.isEmpty()) {
                try{
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm", Locale.getDefault());

                    Date date = sdf.parse(dateString);
                    long eventTimeMillis = date.getTime();

                    //Create event object
                    Event event = new Event(name, dateString, eventTimeMillis, userID);

                    //Save to database here
                    scheduleReminder(eventTimeMillis, name);

                    new Thread(() -> {
                        eventDao.insertEvent(event);

                        runOnUiThread(() -> {
                            eventList.add(event);
                            adapter.notifyItemInserted(eventList.size() - 1);
                        });
                    }).start();

                    eventNameInput.setText("");
                    eventDateInput.setText("");
                }
                catch(ParseException e){
                    e.printStackTrace();
                }

            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(
                        new String[]{Manifest.permission.POST_NOTIFICATIONS},
                        300
                );
            }
        }
    }



    private void scheduleReminder(long eventTimeMillis, String eventName) {

        AlarmManager alarmManager =
                (AlarmManager) getSystemService(ALARM_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (!alarmManager.canScheduleExactAlarms()) {

                Intent intent = new Intent(
                        android.provider.Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM
                );
                startActivity(intent);

                Toast.makeText(this,
                        "Please allow exact alarms in settings.",
                        Toast.LENGTH_LONG).show();

                return;
            }
        }

        Intent intent = new Intent(this, ReminderReceiver.class);
        intent.putExtra("eventName", eventName);

        if (eventTimeMillis <= System.currentTimeMillis()) {
            Toast.makeText(this,
                    "Event time must be in the future",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this,
                (int) System.currentTimeMillis(),
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                eventTimeMillis,
                pendingIntent
        );
    }
}
