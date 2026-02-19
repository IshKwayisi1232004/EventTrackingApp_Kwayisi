package com.example.eventtrackingapp_kwayisi.data.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
public class SMSHelper {

    public static boolean hasPermission(Context context){
        return ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.SEND_SMS
        ) == PackageManager.PERMISSION_GRANTED;
    }
    public static void sendSms(String phoneNumber, String message){
            SmsManager smsManager = SmsManager.getDefault();

            //Use your own test number or emulator
            smsManager.sendTextMessage(
                    "5551234567",
                    null,
                    "Reminder: You have an upcoming event!",
                    null,
                    null
            );

            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
    }

}
