package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        TextView congratulationsTextView = findViewById(R.id.congratulationsTextView);
        TextView scoreTextView = findViewById(R.id.scoreTextView);
        Button newQuizButton = findViewById(R.id.newQuizButton);
        Button finishButton = findViewById(R.id.finishButton);

        Intent intentReceive = getIntent();
        String name = intentReceive.getStringExtra("name");
        String score = intentReceive.getStringExtra("score");

        String congratulations = "Congratulations " + name + "!";
        congratulationsTextView.setText(congratulations);
        scoreTextView.setText(score);

        newQuizButton.setOnClickListener(view -> {
            Intent intentResultsActivity = new Intent(this, MainActivity.class);
            intentResultsActivity.putExtra("name", name);
            startActivity(intentResultsActivity);
        });
    }
}