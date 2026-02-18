package com.example.eventtrackingapp_kwayisi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private ArrayList<Event> eventList;

    public EventAdapter(ArrayList<Event> eventList) {
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_event_row, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = eventList.get(position);

        holder.eventNameText.setText(event.name);
        holder.eventDateText.setText(event.date);

        holder.deleteButton.setOnClickListener(v -> {
            eventList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, eventList.size());
        });
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    static class EventViewHolder extends RecyclerView.ViewHolder {

        TextView eventNameText, eventDateText;
        Button deleteButton;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            eventNameText = itemView.findViewById(R.id.rowEventName);
            eventDateText = itemView.findViewById(R.id.rowEventDate);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
