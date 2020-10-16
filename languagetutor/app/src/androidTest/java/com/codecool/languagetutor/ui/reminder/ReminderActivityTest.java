package com.codecool.languagetutor.ui.reminder;

import com.codecool.languagetutor.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class ReminderActivityTest {

    @Rule
    public ActivityScenarioRule<ReminderActivity> activityScenarioRule = new ActivityScenarioRule<ReminderActivity>(ReminderActivity.class);

    @Test
    public void test_ReminderActivityTitle_IsVisible() {
        onView(ViewMatchers.withId(R.id.activity_reminder))
                .check(matches(isDisplayed()));
        onView(withId(R.id.reminder_title))
                .check(matches(withText(R.string.reminder_title)));
    }

    @Test
    public void test_TimePickerAppears_OnButtonClick() {
        onView(withId(R.id.open_time)).perform(click());
        onView(withId(android.R.id.button1)).check(matches(ViewMatchers.isDisplayed()));
    }
}