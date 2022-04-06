package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText nameEditText = findViewById(R.id.nameEditText);

        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(view -> {
            if (nameEditText.getText().toString().equals("")) {
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
            } else {
                Intent intentMainActivity = new Intent(this, ActivityQuestions.class);
                intentMainActivity.putExtra("name", nameEditText.getText().toString());
                startActivity(intentMainActivity);
            }
        });
    }
}