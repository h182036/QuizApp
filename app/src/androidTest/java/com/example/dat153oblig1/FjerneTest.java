package com.example.dat153oblig1;


import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.example.dat153oblig1.ui.Activites.DBActivity;


import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;


import static androidx.core.util.Preconditions.checkNotNull;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.action.ViewActions.click;


@RunWith(AndroidJUnit4.class)
@LargeTest


public class FjerneTest {


    @Rule
    public ActivityTestRule<DBActivity> mActivityRule = new ActivityTestRule<>(
            DBActivity.class);
    private DBActivity MyViewHolder = null;


    @Before

    public void setActivity(){
        MyViewHolder = mActivityRule.getActivity();
    }

    public void FjerneTest() {

        onData(withItemContent("Flekken1")).perform(click());
        //velger fra listen
        onData(withItemContent("Yes")).perform(click());




    }


    public static Matcher<Katt> withItemContent(String expectedText) {

        checkNotNull(expectedText);
        return withItemContent((expectedText));
    }
}
