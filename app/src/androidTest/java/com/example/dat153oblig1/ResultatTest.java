package com.example.dat153oblig1;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.dat153oblig1.ui.Activites.MainActivity;
import com.example.dat153oblig1.ui.Activites.Quiz;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ResultatTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void scoreTest() {

        onView(withId(R.id.start)).perform(click());
        //starter quiz
        onView(withId(R.id.nameInput)).perform(typeText("Flekken9"), closeSoftKeyboard());
        //skriver inn feil svar
        onView(withId(R.id.checkAnswerButton)).perform(click());
        //
        onView(withId(R.id.checkAnswerButton)).perform(click());
        //
        onView(withId(R.id.nameInput)).perform(typeText("Flekken10"), closeSoftKeyboard());
        //skriver inn feil svar
        onView(withId(R.id.checkAnswerButton)).perform(click());
        //
        onView(withId(R.id.checkAnswerButton)).perform(click());
        //
        onView(withId(R.id.nameInput)).perform(typeText("Flekken11"), closeSoftKeyboard());
        //skriver inn feil svar
        onView(withId(R.id.checkAnswerButton)).perform(click());
        //
        onView(withId(R.id.checkAnswerButton)).perform(click());

        //sjekker at resultatet er 0 av 3
        onView(withId(R.id.scoreText)).check(matches(withText("0/3")));

        /**
         * First time running the app the code-snippet under must be included
         */


        };
    }
