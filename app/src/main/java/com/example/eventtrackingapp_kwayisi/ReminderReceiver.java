package com.example.eventtrackingapp_kwayisi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.eventtrackingapp_kwayisi.data.utils.SMSHelper;

public class ReminderReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent){

        String eventName = intent.getStringExtra("eventName");

        // Show notification
        showNotification(context, eventName);

        // If permission is granted, send SMS
        if(SMSHelper.hasPermission(context)){
            SMSHelper.sendSms(
                    "5551234567",
                    "Reminder: Your event \"" + eventName + "\" is happening now!"
            );
        }
    }

    private void showNotification(Context context, String eventName) {

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, "event_channel")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("Event Reminder")
                        .setContentText("Your event \"" + eventName + "\" is happening now!")
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setAutoCancel(true);

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(context);

        notificationManager.notify(
                (int) System.currentTimeMillis(),
                builder.build()
        );
    }


}
