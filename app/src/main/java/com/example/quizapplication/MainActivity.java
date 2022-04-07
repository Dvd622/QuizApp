package com.example.quizapplication;

// TO DO: startActivityForResult() - save name at end of quiz
// TO DO: Finish button
// TO DO: add actual questions

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
                startActivityForResult(intentMainActivity, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_CANCELED && requestCode==1) {
            finish();
            System.exit(0);
        }
    }
}