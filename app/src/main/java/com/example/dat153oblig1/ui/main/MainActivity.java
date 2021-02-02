package com.example.dat153oblig1.ui.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.graphics.drawable.BitmapDrawable;

import android.graphics.drawable.Drawable;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;

import android.widget.ImageButton;

import android.database.Cursor;
import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;

import com.example.dat153oblig1.R;
import com.example.dat153oblig1.SQLiteDB;
import com.example.dat153oblig1.Spørsmål;

import java.util.UUID;


public class MainActivity extends AppCompatActivity {

    public static SQLiteDB SQLiteHelper;
    ImageButton startButton;
    Button manageButton;


    @Override
    protected void onRestart() {
        super.onRestart();
        recreate();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        startButton = findViewById(R.id.start);
        manageButton = findViewById(R.id.manage);



        /**
         * Fetches the name shared preferences
         */
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String name = pref.getString("name", "");

        /**
         * Creates SQLite database and creates the database if it is not created
         */
        SQLiteHelper = new SQLiteDB(this, "PeopleDB.sqlite", null, 1);
        SQLiteHelper.queryData("CREATE TABLE IF NOT EXISTS KATT(id VARCHAR PRIMARY KEY, image BLOB, name VARCHAR)");


        /**
         * Get all the data from SQLite and puts them in a cursor
         */
        Cursor cursor = MainActivity.SQLiteHelper.getData("SELECT * FROM KATT");

        /**
         * Adds testpersons for testing getResources().getDrawable(R.drawable.myImage);
         */

        if(cursor.getCount() == 0 ) {
            SQLiteHelper.insertData(UUID.randomUUID().toString(),"Flekken1", getResources().getDrawable(R.drawable.flekken1));
            SQLiteHelper.insertData(UUID.randomUUID().toString(), "flekken2", getResources().getDrawable(R.drawable.flekken2));
            SQLiteHelper.insertData(UUID.randomUUID().toString(), "flekken3", getResources().getDrawable(R.drawable.flekken3));
        } else if(cursor.getCount() == 1){
            SQLiteHelper.insertData(UUID.randomUUID().toString(),  "flekken2", getResources().getDrawable(R.drawable.flekken2));
            SQLiteHelper.insertData(UUID.randomUUID().toString(),  "flekken3", getResources().getDrawable(R.drawable.flekken3));
        } else if(cursor.getCount() == 2){
            SQLiteHelper.insertData(UUID.randomUUID().toString(),  "flekken3", getResources().getDrawable(R.drawable.flekken3));
        }

        ((Spørsmål) this.getApplication()).clear();
        /**
         * Fetches the saved questions using SQLite
         */
        while (cursor.moveToNext()) {
            String sqlId = cursor.getString(0);
            String sqlName = cursor.getString(1);
            byte[] sqlImage = cursor.getBlob(2);

            Drawable DrawableImage = new BitmapDrawable(getResources(), BitmapFactory.decodeByteArray(sqlImage, 0, sqlImage.length));

            ((Spørsmål) this.getApplication()).addKatt(sqlId, sqlName, DrawableImage);
        }

        /**
         * Get the amount of people in the database
         */
        final int count = ((Spørsmål) this.getApplication()).getCount();

        /**
         *  Starts new activity.
         */
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count == 0) {
                    Toast.makeText(getApplicationContext(), "No questions in the database!",
                            Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, Quiz.class);
                    startActivity(intent);
                }
            }
        });


        /**
         *  Starts new activity.
         */
        manageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DBActivity.class);
                startActivity(intent);
            }
        });


    }

    /**
     * Add images and names of the cats
     */
    public void addQuestions() {
        ((Spørsmål) this.getApplication()).addKatt("", "flekken1", getResources().getDrawable(R.drawable.flekken1));
        ((Spørsmål) this.getApplication()).addKatt("", "flekken2", getResources().getDrawable(R.drawable.flekken2));
        ((Spørsmål) this.getApplication()).addKatt("", "flekken3", getResources().getDrawable(R.drawable.flekken3));

    }

}