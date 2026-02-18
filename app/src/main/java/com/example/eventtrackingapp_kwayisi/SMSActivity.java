package com.example.eventtrackingapp_kwayisi;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class SMSActivity extends AppCompatActivity{
    private static final int SMS_PERMISSION_CODE = 101;

    Button enableSmsButton;
    TextView statusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        enableSmsButton = findViewById(R.id.enableSmsButton);
        statusText = findViewById(R.id.statusText);

        enableSmsButton.setOnClickListener(v -> checkSmsPermission());
    }

    private void checkSmsPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                == PackageManager.PERMISSION_GRANTED) {

            sendNotificationSms();

        } else {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.SEND_SMS},
                    SMS_PERMISSION_CODE
            );
        }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            String[] permissions,
            int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == SMS_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                statusText.setText("SMS permission granted.");
                sendNotificationSms();

            } else {
                statusText.setText("SMS permission denied. App will continue without notifications.");
            }
        }
    }

    private void sendNotificationSms(){
        try{
            SmsManager smsManager = SmsManager.getDefault();

            //Use your own test number or emulator
            smsManager.sendTextMessage(
                    "5551234567",
                    null,
                    "Reminder: You have an upcoming event!",
                    null,
                    null
            );

            Toast.makeText(this, "SMS notification sent.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Failed to send SMS.", Toast.LENGTH_SHORT).show();
        }
    }
}
