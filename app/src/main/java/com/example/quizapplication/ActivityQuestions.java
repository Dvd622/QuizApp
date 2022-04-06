package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class ActivityQuestions extends AppCompatActivity {

    String userAnswer = "";
    Integer questionNumber = 0;
    Integer score = 0;
    String progressString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        TextView progressTextView = findViewById(R.id.progressTextView);
        ProgressBar quizProgressBar = findViewById(R.id.quizProgressBar);
        TextView questionTitleTextView = findViewById(R.id.questionTitleTextView);
        TextView questionDetailTextView = findViewById(R.id.questionDetailTextView);
        Button answer1Button = findViewById(R.id.answer1Button);
        Button answer2Button = findViewById(R.id.answer2Button);
        Button answer3Button = findViewById(R.id.answer3Button);
        Button nextButton = findViewById(R.id.nextButton);

        Intent intentReceive = getIntent();
        String name = intentReceive.getStringExtra("name");

        // question{quizTitle, quizDetails, option1, option2, option3, correct answer}
        String[] question1 = {"Test1", "This is a test1", "answer1 test", "test2", "testing 3", "test2"};
        String[] question2 = {"Testing Title 2","Q Details 2", "t1", "t two", "the answer", "the answer"};
        String[] question3 = {"Testing Title three","Q Details three", "t three", "t too", "not the answer", "t three"};
        String[] question4 = {};
        String[] question5 = {};
        String[][] quiz = {question1, question2, question3};

        questionTitleTextView.setText(quiz[0][0]);
        questionDetailTextView.setText(quiz[0][1]);
        progressString = "1/" + quiz.length;
        progressTextView.setText(progressString);
        answer1Button.setText(quiz[questionNumber][2]);
        answer2Button.setText(quiz[questionNumber][3]);
        answer3Button.setText(quiz[questionNumber][4]);
        quizProgressBar.setProgress(0);
        nextButton.setText(getString(R.string.submitString));

        answer1Button.setOnClickListener(view -> {
            if (nextButton.getText().toString().equals("Submit")) {
                answer1Button.setBackgroundColor(getResources().getColor(R.color.purple_700));
                answer2Button.setBackgroundColor(getResources().getColor(R.color.purple_500));
                answer3Button.setBackgroundColor(getResources().getColor(R.color.purple_500));
                userAnswer = answer1Button.getText().toString();
            }
        });

        answer2Button.setOnClickListener(view -> {
            if (nextButton.getText().toString().equals("Submit")) {
                answer2Button.setBackgroundColor(getResources().getColor(R.color.purple_700));
                answer1Button.setBackgroundColor(getResources().getColor(R.color.purple_500));
                answer3Button.setBackgroundColor(getResources().getColor(R.color.purple_500));
                userAnswer = answer2Button.getText().toString();
            }
        });

        answer3Button.setOnClickListener(view -> {
            if (nextButton.getText().toString().equals("Submit")) {
                answer3Button.setBackgroundColor(getResources().getColor(R.color.purple_700));
                answer1Button.setBackgroundColor(getResources().getColor(R.color.purple_500));
                answer2Button.setBackgroundColor(getResources().getColor(R.color.purple_500));
                userAnswer = answer3Button.getText().toString();
            }
        });

        nextButton.setOnClickListener(view -> {
            if (nextButton.getText().toString().equals("Next")) {
                questionNumber += 1;
                if (questionNumber == quiz.length) { // if end of quiz, go to results activity
                    Intent intentActivityQuestions = new Intent(this, ResultsActivity.class);
                    intentActivityQuestions.putExtra("name", name);
                    String finalScore = score + "/" + quiz.length;
                    intentActivityQuestions.putExtra("score", finalScore);
                    startActivity(intentActivityQuestions);
                } else { // else not end of quiz, update all text to next question
                    quizProgressBar.setProgress(questionNumber*100/quiz.length);
                    progressString = questionNumber.toString() + quiz.length;
                    progressTextView.setText(progressString);
                    questionTitleTextView.setText(quiz[questionNumber][0]);
                    questionDetailTextView.setText(quiz[questionNumber][1]);
                    answer1Button.setText(quiz[questionNumber][2]);
                    answer2Button.setText(quiz[questionNumber][3]);
                    answer3Button.setText(quiz[questionNumber][4]);
                    answer1Button.setBackgroundColor(getResources().getColor(R.color.purple_500));
                    answer2Button.setBackgroundColor(getResources().getColor(R.color.purple_500));
                    answer3Button.setBackgroundColor(getResources().getColor(R.color.purple_500));
                    userAnswer = "";
                    nextButton.setText(getString(R.string.submitString));
                }
            } else if (!userAnswer.equals("")) { // user selected an option
                if (userAnswer.equals(quiz[questionNumber][5])) { // correct answer chosen
                    score += 1;
                } else { // incorrect answer chosen
                    // change background of chosen button answer to red
                    if (answer1Button.getText().toString().equals(userAnswer)) {
                        answer1Button.setBackgroundColor(getResources().getColor(R.color.red));
                    } else if (answer2Button.getText().toString().equals(userAnswer)) {
                        answer2Button.setBackgroundColor(getResources().getColor(R.color.red));
                    } else if (answer3Button.getText().toString().equals(userAnswer)) {
                        answer3Button.setBackgroundColor(getResources().getColor(R.color.red));
                    }
                }
                // change background of correct button answer to green regardless of answer chosen
                if (answer1Button.getText().toString().equals(quiz[questionNumber][5])) {
                    answer1Button.setBackgroundColor(getResources().getColor(R.color.green));
                } else if (answer2Button.getText().toString().equals(quiz[questionNumber][5])) {
                    answer2Button.setBackgroundColor(getResources().getColor(R.color.green));
                } else if (answer3Button.getText().toString().equals(quiz[questionNumber][5])) {
                    answer3Button.setBackgroundColor(getResources().getColor(R.color.green));
                }
                nextButton.setText(R.string.nextString);
            } else { // user did not select an option
                Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show();
            }
        });
    }
}