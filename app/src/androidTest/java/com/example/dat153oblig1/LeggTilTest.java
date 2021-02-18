package com.example.dat153oblig1;


import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.ContactsContract;

import androidx.annotation.Nullable;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.test.InstrumentationRegistry;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.example.dat153oblig1.ui.Activites.DBActivity;
import com.example.dat153oblig1.ui.Activites.NyKattActivity;
import com.example.dat153oblig1.SQLiteDB;


import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static androidx.core.util.Preconditions.checkNotNull;
import static androidx.test.InstrumentationRegistry.getContext;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertTrue;
/**

@RunWith(AndroidJUnit4.class)
@LargeTest
//test

public class LeggTilTest {

    // FOR DATA
    private SQLiteDB database;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception {
        this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                SQLiteDB.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void closeDb() throws Exception {
        database.close();
    }



    // DATA SET FOR TEST


    private static String id = "katt5";
    private static Katt katteksempel = new Katt(id, "Flekken5", getContext().getResources().getDrawable(R.drawable.flekken4));


    @Test
    public void insertAndGetUser() throws InterruptedException {
        // BEFORE : Adding a new user
        this.database.insertData(katteksempel);
        // TEST
        Katt katt = LiveDataTestUtil.getValue(this.database.getData("SELECT * FROM KATT"));
        assertTrue(katt.getNavn().equals(katteksempel.getNavn()) && katt.getId() == id);
    }
}


*/