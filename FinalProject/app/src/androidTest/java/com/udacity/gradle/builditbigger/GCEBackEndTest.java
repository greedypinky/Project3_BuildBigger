package com.udacity.gradle.builditbigger;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.udacity.gradle.builditbigger.paid.PaidActivityFragment;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.action.ViewActions.click;

import org.junit.Rule;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class GCEBackEndTest {

    private IdlingResource mIdlingResource;

    @Before
    public void registerIdlingResource() {


    }

    @After
    public void unregisterIdlingResource() {


    }

    @Rule
    //public ActivityTestRule<PaidActivityFragment extends Activity> activityTestRule = new ActivityTestRule<>(PaidActivityFragment.class);
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testGetJoke() throws Throwable {

        assertNotNull(R.id.but_poke_joke);
        onView(withId(R.id.but_poke_joke)).perform(click());
        onView(allOf(withId(com.project2.myandroidjokelibrary.R.id.joke_content), withText(startsWith("joke")))).check(matches(isDisplayed
                ()));

    }

}
