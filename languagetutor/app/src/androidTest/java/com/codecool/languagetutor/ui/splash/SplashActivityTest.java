package com.codecool.languagetutor.ui.splash;

import com.codecool.languagetutor.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class SplashActivityTest {

    private int expectedTitle;

    @Rule
    public ActivityScenarioRule<SplashActivity> activityScenarioRule =
            new ActivityScenarioRule(SplashActivity.class);

    @Before
    public void excpectedTitle() {
        expectedTitle = R.string.splash_title;
    }

    @Test
    public void test_SplashActivityIsVisible() {
        onView(withId(R.id.splash_activity))
                .check(matches(isDisplayed()));
    }

    @Test
    public void test_titleIsVisible() {
        onView(withId(R.id.title))
                .check(matches(isDisplayed()))
                .check(matches(withText(expectedTitle)));
    }

    @Test
    public void test_flag1IsDisplayed() {
        onView(withId(R.id.flag1))
                .check(matches(isDisplayed()));
    }
}