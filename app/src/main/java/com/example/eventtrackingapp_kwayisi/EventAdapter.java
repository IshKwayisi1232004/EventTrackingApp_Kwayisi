package com.example.eventtrackingapp_kwayisi;

import com.example.eventtrackingapp_kwayisi.data.local.Event;
import com.example.eventtrackingapp_kwayisi.data.local.EventDao;


import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private List<Event> eventList;

    public interface OnEventActionListener {
        void onDelete(Event event);
        void onEdit(Event event);
    }

    private OnEventActionListener listener;

    public EventAdapter(List<Event> eventList, OnEventActionListener listener) {
        this.eventList = eventList;
        this.listener = listener;
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

        holder.eventNameText.setText(event.getEventName());
        holder.eventDateText.setText(event.getEventDate());

        // Edit button click
        holder.editButton.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEdit(event);
            }
        });

        // Delete button click
        holder.deleteButton.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDelete(event);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    static class EventViewHolder extends RecyclerView.ViewHolder {

        TextView eventNameText, eventDateText;
        Button deleteButton;
        Button editButton;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            eventNameText = itemView.findViewById(R.id.rowEventName);
            eventDateText = itemView.findViewById(R.id.rowEventDate);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            editButton = itemView.findViewById(R.id.editButton);
        }
    }
}