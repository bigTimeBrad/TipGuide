package com.example.tipguide;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.os.SystemClock;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;

import static android.app.PendingIntent.getActivity;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.doubleClick;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;

import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.SeekBar;


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

    @Test
    public void converterPage(){

        Matcher<View> matcher = allOf(withText("CONVERTER"),
                isDescendantOfA(withId(R.id.tabs)));
        onView(matcher).perform(click());
        SystemClock.sleep(1800);


        onView(withId(R.id.spinnerIn)).perform(click());
        onData(anything()).atPosition(1).perform(click());
        SystemClock.sleep(1800);

        onView(withId(R.id.spinnerOut)).perform(click());
        onData(anything()).atPosition(2).perform(click());
        SystemClock.sleep(1800);


        onView(withId(R.id.buttonConverter)).perform(click());

        SystemClock.sleep(1800);

        //onView(withId(R.id.textViewResult)).check(matches(withText("0.85 EUR")));

    }



    @Test
    public void calculatorToastTest(){

        Matcher<View> matcher = allOf(withText("CALCULATOR"),
                isDescendantOfA(withId(R.id.tabs)));
        onView(matcher).perform(click());
        SystemClock.sleep(1800);


        onView(withId(R.id.bill_value)).perform(typeText("100"));
        Espresso.closeSoftKeyboard();

        SystemClock.sleep(1800);
        onView(withId(R.id.calculate_tips)).perform(click());
        onView(withText("Set values for Tip percent and split number")).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
        SystemClock.sleep(1800);

    }


    @Test
    public void calculatorToastTest2(){

        Matcher<View> matcher = allOf(withText("CALCULATOR"),
                isDescendantOfA(withId(R.id.tabs)));
        onView(matcher).perform(click());
        SystemClock.sleep(1800);

        onView(withId(R.id.calculate_tips)).perform(click());
        onView(withText("All Input field must be filled")).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
        SystemClock.sleep(1800);

    }


    @Test
    public void calculatorTest(){

        Matcher<View> matcher = allOf(withText("CALCULATOR"),
                isDescendantOfA(withId(R.id.tabs)));
        onView(matcher).perform(click());
        SystemClock.sleep(1800);

        //totalBill
        onView(withId(R.id.bill_value)).perform(typeText("100.00"));

        onView(withId(R.id.seekBar)).perform(setProgress(20));
        Espresso.closeSoftKeyboard();



        onView(withId(R.id.seekBar_one)).perform(setProgress(1));
        Espresso.closeSoftKeyboard();


        SystemClock.sleep(1800);
        //Click Button and check the values
        onView(withId(R.id.calculate_tips)).perform(click());
        onView(withId(R.id.total_to_pay_result)).check(matches(withText("120")));
        onView(withId(R.id.total_tip_result)).check(matches(withText("20")));
        onView(withId(R.id.tip_per_person_result)).check(matches(withText("20")));
        onView(withId(R.id.tip_percent)).check(matches(withText("Tip Percent - 20")));
        onView(withId(R.id.split_number)).check(matches(withText("Split Number - 1")));
        SystemClock.sleep(1800);
    }

    public static ViewAction setProgress(int progress) {
        return new ViewAction() {
            @Override
            public void perform(UiController uiController, View view) {
                SeekBar seekBar = (SeekBar) view;
                seekBar.setProgress(progress);
            }
            @Override
            public String getDescription() {
                return "Set a progress on a SeekBar";
            }
            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isAssignableFrom(SeekBar.class);
            }
        };
    }




}