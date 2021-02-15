package com.example.dat153oblig1.ui.Activites;


import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;
import android.os.Bundle;

import com.example.dat153oblig1.R;

public class NavnActivity extends AppCompatActivity {

    EditText nameInput;
    Button saveButton;

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Please enter your name first!",
                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navn);

        nameInput = findViewById(R.id.nameInputSharedPreferences);
        saveButton = findViewById(R.id.saveNameSharedPreferences);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!nameInput.getText().toString().equals("")) {
                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putString("name", nameInput.getText().toString());
                    myEdit.commit();
                    finish();
                }
            }
        });
    }
}