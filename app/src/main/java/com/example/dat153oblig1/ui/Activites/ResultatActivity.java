package com.example.dat153oblig1.ui.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.ImageButton;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;

import com.example.dat153oblig1.R;

public class ResultatActivity extends AppCompatActivity {

    ImageButton homeButton;
    ImageButton restartButton;
    TextView scoreText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);

        homeButton = findViewById(R.id.homeButton);
        restartButton = findViewById(R.id.restartButton);
        scoreText = findViewById(R.id.scoreText);

        Bundle bundle = getIntent().getExtras();

        int score = bundle.getInt("score");
        int amount = bundle.getInt("amount");

        scoreText.setText(score + "/" + amount);

        /**
         * Starts the MainActivity and deletes the activity stack.
         */
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultatActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });

        /**
         *  Finishes this activity and restarts the quiz.
         */
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

    }
}
