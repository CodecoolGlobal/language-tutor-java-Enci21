package com.codecool.languagetutor.ui.history;

import com.codecool.languagetutor.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import androidx.test.espresso.PerformException;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.espresso.contrib.RecyclerViewActions;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class HistoryActivityTest {

    @Rule
    public ActivityScenarioRule<HistoryActivity> activityScenarioRule =
            new ActivityScenarioRule<HistoryActivity>(HistoryActivity.class);

    @Test
    public void test_RecyclerViewInView() {
        onView(ViewMatchers.withId(R.id.historyRecyclerView))
                .check(matches(isDisplayed()));
    }

    @Test(expected = PerformException.class)
    public void test_itemWithText_doesNotExist() {
        onView(ViewMatchers.withId(R.id.historyRecyclerView))
                .perform(RecyclerViewActions.scrollTo(
                        hasDescendant(withText("not in the list"))
                ));
    }

}
