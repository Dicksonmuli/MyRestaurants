package com.dicksonmully6gmail.myrestaurants;

/**
 * Created by dickson on 5/22/17.
 */

import android.content.Context;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

public class MainActivityInstrumentationTest {




    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void validateEditText() {
        onView(withId(R.id.locationEditText)).perform(typeText("Portland"))
                .check(matches(withText("Portland")));
    }
//    @Test
//    public void locationIsSentToRestaurantsActivity() {
//        String location = "Portland";
//        onView(withId(R.id.locationEditText)).perform(typeText(location));
//        closeSoftKeyboard();
//        onView(withId(R.id.findRestaurantsButton)).perform(click());
//        onView(withId(R.id.resLocationTextView))
//                .check(matches(withText("Here are all the restaurants near: " + location)));
////        onView(withId(R.id.resLocationTextView)).check(matches(not(isDisplayed())));
//    }
}