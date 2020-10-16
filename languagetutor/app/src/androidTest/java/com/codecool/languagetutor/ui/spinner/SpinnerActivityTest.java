package com.codecool.languagetutor.ui.spinner;

import com.codecool.languagetutor.R;

import org.junit.Rule;
import org.junit.Test;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import static org.junit.Assert.*;

public class SpinnerActivityTest {

    private Integer option = 10;

    @Rule
    public ActivityScenarioRule<SpinnerActivity> activityScenarioRule =
            new ActivityScenarioRule<SpinnerActivity>(SpinnerActivity.class);

    @Test
    public void test_checkSpinnerShowsCorrect_WhenSelectingOption() {
        onView(withId(R.id.spinner)).perform(click());
        onData(allOf(is(instanceOf(Integer.class)), is(option))).perform(click());
        onView(withId(R.id.spinner)).check(matches(withSpinnerText(option.toString())));
    }
}