package com.example.tipguide;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.startsWith;

import android.support.test.rule.ActivityTestRule;



@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);


    @Test
    public void onCreateClickTabs(){

        onView(withText("HOME")).check(matches(withText("HOME")));

        onView(withId(R.id.viewpager)).perform(swipeLeft());
        onView(withText("CONVERTER")).check(matches(withText("CONVERTER")));

        onView(withId(R.id.viewpager)).perform(swipeLeft());
        onView(withText("CALCULATOR")).check(matches(withText("CALCULATOR")));
    }

    @Test
    public void homePageSpinner(){
        //onView(withText("HOME")).check(matches(withText("HOME")));

        onView(withText("-------")).perform(click());

        onData(hasToString(startsWith("Morocco")))
                .perform(click());

        onView(withId(R.id.update))
                .perform(click());
        
    }


}