package com.codecool.languagetutor.ui.training;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codecool.languagetutor.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class TrainingActivityTest {

    @Rule
    public ActivityScenarioRule<TrainingActivity> activityScenarioRule =
            new ActivityScenarioRule(TrainingActivity.class);


    @Test
    public void test_progressBar_IsVisible() {
        onView(withId(R.id.training_activity))
                .check(matches(isDisplayed()));
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()));
    }

    @Test
    public void test_BackgroundChanged_WhenWordSubmitted() {
        String word = "word";
        String defaultBackground = "#96c9e6";
        onView(withId(R.id.training_activity))
                .check(matches(isDisplayed()));
        onView(allOf(withId(R.id.answer), isDescendantOfA(firstChildOf(withId(R.id.pager)))))
                .check(matches(isDisplayed()))
                .perform(typeText(word), closeSoftKeyboard());
        onView(allOf(withId(R.id.check_button), isDescendantOfA(firstChildOf(withId(R.id.pager)))))
                .perform(click());
        onView(allOf(withId(R.id.answer), isDescendantOfA(firstChildOf(withId(R.id.pager)))))
                .check(matches(not((new BackgroundColorMatcher(defaultBackground)))));
    }

    static class BackgroundColorMatcher extends BoundedMatcher<View, TextView> {

        private int color;

        private BackgroundColorMatcher(String color) {
            super(TextView.class);
            this.color = Color.parseColor(color);
        }

        @Override
        protected boolean matchesSafely(TextView item) {
            return item.getCurrentHintTextColor() == this.color;
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("with hint text color:")
                    .appendValue(color);

        }
    }
    public static Matcher<View> firstChildOf(final Matcher<View> parentMatcher) {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("with first child view");
            }

            @Override
            public boolean matchesSafely(View view) {

                if (!(view.getParent() instanceof ViewGroup)) {
                    return parentMatcher.matches(view.getParent());
                }
                ViewGroup group = (ViewGroup) view.getParent();
                return parentMatcher.matches(view.getParent()) && group.getChildAt(0).equals(view);

            }
        };
    }
}
