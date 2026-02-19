package com.example.eventtrackingapp_kwayisi;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventtrackingapp_kwayisi.data.local.AppDatabase;
import com.example.eventtrackingapp_kwayisi.data.local.User;
import com.example.eventtrackingapp_kwayisi.data.repository.UserRepository;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eventtrackingapp_kwayisi.data.repository.UserRepository;

public class MainActivity extends AppCompatActivity {

    TextView textStatus;
    EditText usernameInput, passwordInput;
    Button loginButton, createAccountButton;

    UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db = AppDatabase.getInstance(this);
        userRepository = new UserRepository(db.userDao());

        textStatus = findViewById(R.id.statusText);
        usernameInput = findViewById(R.id.usernameInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginButton = findViewById(R.id.loginButton);
        createAccountButton = findViewById(R.id.createAccountButton);

        loginButton.setOnClickListener(v -> loginUser());
        createAccountButton.setOnClickListener(v -> createUser());

        if (checkSelfPermission(Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(
                    new String[]{Manifest.permission.SEND_SMS},
                    101
            );
        }


    }

    private void loginUser(){
        // Get the text back from the string
        String username = usernameInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        // Check if the name variable is null or empty
        if(username.isEmpty() || password.isEmpty()){
            textStatus.setText("Please enter both username and password.");
            return;
        }

        // Placeholder logic (replace with database later)
        userRepository.login(username, password, success -> {
            runOnUiThread(() -> {
                if(success){
                    Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();

                    // TRANSITION TO NEXT SCREEN
                    Intent intent = new Intent(MainActivity.this, EventGridActivity.class);
                    startActivity(intent);

                    // Optional: prevent going back to login
                    finish();
                }
                else{
                    textStatus.setText("Invalid login credentials.");
                }
            });
        });
    }

    private void createUser() {

        String username = usernameInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            textStatus.setText("Username and password required.");
            return;
        }

        new Thread(() -> {

            long userId = userRepository.register(username, password);

            runOnUiThread(() -> {

                SharedPreferences prefs =
                        getSharedPreferences("session", MODE_PRIVATE);
                prefs.edit().putInt("userID", (int) userId).apply();

                Toast.makeText(this,
                        "Account created successfully!",
                        Toast.LENGTH_SHORT).show();

                Intent intent =
                        new Intent(MainActivity.this, EventGridActivity.class);
                startActivity(intent);
                finish();
            });

        }).start();
    }
}