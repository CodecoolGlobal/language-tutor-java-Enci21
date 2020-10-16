package com.codecool.languagetutor.ui;

import com.codecool.languagetutor.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.MediumTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class MainActivityTest {

    private int buttonAdd;
    private int buttonTrain;
    private int buttonHistory;

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule =
            new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Before
    public void expectedButtonTexts() {
        buttonAdd = R.string.add_word_btn_label;
        buttonTrain = R.string.train_btn_label;
        buttonHistory = R.string.history_btn_label;
    }

    @Test
    public void test_mainActivity_IsVisible() {
        onView(withId(R.id.main_activity))
                .check(matches(isDisplayed()));
    }

    @Test
    public void test_menuButtonAddWord_IsCorrect() {
        onView(withId(R.id.button_add_word))
                .check(matches(isDisplayed()))
                .check(matches(withText(buttonAdd)));
    }

    @Test
    public void test_menuButtonTrain_IsCorrect() {
        onView(withId(R.id.button_training))
                .check(matches(isDisplayed()))
                .check(matches(withText(buttonTrain)));
    }

    @Test
    public void test_menuButtonHistory_IsCorrect() {
        onView(withId(R.id.button_history))
                .check(matches(isDisplayed()))
                .check(matches(withText(buttonHistory)));
    }

    @Test
    public void test_AddWordButtonNavigates_ToAddWordActivty() {
        onView(withId(R.id.button_add_word))
                .perform(click());
        onView(withId(R.id.add_word_activity))
                .check(matches(isDisplayed()));
    }

    @Test
    public void test_trainButtonNavigates_ToSpinnerActivity() {
        onView(withId(R.id.button_training))
                .perform(click());
        onView(withId(R.id.spinner_activity))
                .check(matches(isDisplayed()));
    }

    @Test
    public void test_historyButtonNavigates_ToHistoryActivity() {
        onView(withId(R.id.button_history))
                .perform(click());
        onView(withId(R.id.history_activity))
                .check(matches(isDisplayed()));
    }
}