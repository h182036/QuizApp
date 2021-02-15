package com.example.dat153oblig1.ui.Activites;

import com.example.dat153oblig1.Katt;
import com.example.dat153oblig1.R;
import com.example.dat153oblig1.Spørsmål;
import com.example.dat153oblig1.ViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import java.util.ArrayList;

import android.view.View;
import android.os.Bundle;


public class DBActivity extends AppCompatActivity {

    private ArrayList<Katt> katter = new ArrayList<>();

    private RecyclerView recyclerView;
    private FloatingActionButton fab;



    @Override
    protected void onRestart() {
        super.onRestart();
        recreate();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        /**
         *  Fetches images and names from the global class Spørsmål.
         */
        katter = ((Spørsmål) this.getApplication()).getKatter();

        /**
         *  Connecting the fab and the recyclerView.
         */
        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recyclerview);

        /**
         * ViewAdapter for handling the RecyclerView.
         */
        ViewAdapter myAdapter = new ViewAdapter(this, katter);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        /**
         *  New activity
         */
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DBActivity.this, NyKattActivity.class);
                startActivity(intent);
            }
        });
    }
}
