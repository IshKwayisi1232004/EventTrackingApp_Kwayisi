package com.example.eventtrackingapp_kwayisi;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class EventGridActivity extends AppCompatActivity{
    EditText eventNameInput, eventDateInput;
    Button addEventButton;
    RecyclerView eventRecyclerView;

    ArrayList<Event> eventList;
    EventAdapter adapter;

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

        // Add button logic
        addEventButton.setOnClickListener(v -> {
            String name = eventNameInput.getText().toString().trim();
            String date = eventDateInput.getText().toString().trim();

            if (!name.isEmpty() && !date.isEmpty()) {
                eventList.add(new Event(name, date));
                adapter.notifyItemInserted(eventList.size() - 1);

                eventNameInput.setText("");
                eventDateInput.setText("");
            }
        });
    }
}
