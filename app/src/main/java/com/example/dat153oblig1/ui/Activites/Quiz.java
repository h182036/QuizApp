package com.example.dat153oblig1.ui.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.ImageView;
import android.widget.EditText;
import android.widget.TextView;
import android.graphics.Color;
import android.content.Intent;

import java.util.Collections;

import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;

import com.example.dat153oblig1.Katt;
import com.example.dat153oblig1.R;
import com.example.dat153oblig1.Spørsmål;


public class Quiz extends AppCompatActivity {

    TextView correctAnswersText;
    EditText nameInput;
    Button checkAnswerButton;
    ImageView imageOfKatt;
    TextView scoreoutof;

    private ArrayList<Katt> katter = new ArrayList<>();


    int score = 0;
    int amountOfQuestions = 0;
    int questionNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        nameInput = findViewById(R.id.nameInput);
        checkAnswerButton = findViewById(R.id.checkAnswerButton);
        imageOfKatt = findViewById(R.id.imageKatt);
        correctAnswersText = findViewById(R.id.correctAnswersText);
        scoreoutof = findViewById(R.id.scoreoutof);

        /**
         * Fetches the lists from the global class.
         */
        katter = ((Spørsmål) this.getApplication()).getKatter();

        /**
         * Shuffles the list
         */
        Collections.shuffle(katter);


        amountOfQuestions = katter.size();

        /**
         * Sets the first image to start the quiz.
         */
        startQuiz();

        /**
         * CheckAnswerButton.
         */
        checkAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!nameInput.getText().toString().equals("")) {
                    if (questionNumber < katter.size()) {
                        if (checkAnswerButton.getText().toString() == "next") {
                            nextQuestion();
                        } else {
                            checkAnswer();
                        }
                    } else {
                        if (nameInput.getText().toString().toLowerCase().equals(katter.get(questionNumber).getNavn().toLowerCase())) {

                            finished();
                        } else {
                            checkAnswer();
                            if (checkAnswerButton.getText().toString() == "next") {
                                finished();
                            }
                        }
                    }
                } else {
                    Toast.makeText(Quiz.this, "Text box is empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    /**
     * Sets the first image to start the quiz.
     */
    public void startQuiz() {
        imageOfKatt.setImageDrawable(katter.get(questionNumber).getImg());
        scoreoutof.setText("Score: " + score + "/" + katter.size());
    }

    /**
     * Function for checking if an answer is correct.
     * If image matches the name inputted by the user the score is increased and the next question is showed.
     */
    public void checkAnswer() {
        if (katter.get(questionNumber).getNavn().toLowerCase().equals(nameInput.getText().toString().toLowerCase())) {
            score++;
            correctAnswersText.setText("Correct!");
            correctAnswersText.setTextColor(Color.GREEN);
            checkAnswerButton.setText("next");
        } else {
            correctAnswersText.setText("Wrong! " + katter.get(questionNumber).getNavn());
            correctAnswersText.setTextColor(Color.RED);
            checkAnswerButton.setText("next");
        }
    }

    /**
     * Function that starts the new activity and sends with the score and the amount of questions.
     */
    public void finished() {
        Intent intent = new Intent(Quiz.this, ResultatActivity.class);
        intent.putExtra("score", score);
        intent.putExtra("amount", amountOfQuestions);
        startActivity(intent);
    }

    /**
     * Function for viewing the next question.
     */
    public void nextQuestion() {
        questionNumber++;
        nameInput.setText("");
        correctAnswersText.setText("");
        scoreoutof.setText("Score: " + score + "/" + katter.size());
        checkAnswerButton.setText("Check Answer");

        if (questionNumber > katter.size() - 1) {
            finished();
        } else {
            imageOfKatt.setImageDrawable(katter.get(questionNumber).getImg());
        }
    }

    /**
     * if the user wants to restart the quiz, the activity is re-created.
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        recreate();
    }
}
