package com.udacity.gradle.builditbigger;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.action.ViewActions.click;

import org.junit.Rule;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class GCEBackEndTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testGetJoke() throws Throwable {

        assertNotNull(R.id.but_poke_joke);
        onView(withId(R.id.but_poke_joke)).perform(click());
        // check if it is not null
        assertNotNull(GCE_EndpointsAsyncTask.getJoke());

    }

}
