package com.example.eventtrackingapp_kwayisi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

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

}
