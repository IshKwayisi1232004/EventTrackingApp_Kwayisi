package com.example.eventtrackingapp_kwayisi;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView textStatus;
    EditText usernameInput, passwordInput;
    Button loginButton, createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textStatus = findViewById(R.id.statusText);
        usernameInput = findViewById(R.id.usernameInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginButton = findViewById(R.id.loginButton);
        createAccountButton = findViewById(R.id.createAccountButton);

        loginButton.setOnClickListener(v -> loginUser());

    }

    private void loginUser(){
        // Get the text back from the string
        String username = usernameInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        // Check if the name variable is null or empty
        if(username != null || !password.isEmpty()){
            textStatus.setText("Please enter both username and password.");
        }

        // Placeholder logic (replace with database later)
        if(username.equals("admin") && password.equals("1234")){
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
    }

    private void createUser() {
        String username = usernameInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            textStatus.setText("Username and password required.");
            return;
        }

        // Placeholder: save to database later
        Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show();
    }
}