package com.udacity.gradle.builditbigger;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.util.Pair;
import android.test.ApplicationTestCase;
import android.test.InstrumentationTestCase;

import com.udacity.gradle.builditbigger.GCE_EndpointsAsyncTask;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

//import android.support.test.runner.AndroidJUnit4;
import android.app.Application;
import android.test.ApplicationTestCase;

import java.util.concurrent.CountDownLatch;


// TODO: Configure Test Task
    /*
        To tie it all together, create a Gradle task that:
        Launches the GCE local development server (in Daemon mode, so it doesn't block further execution)
        Runs all tests
        Shuts the server down again
        Need to run test by
        ./gradlew startAsyncTaskTest
    */
public class GCEBackEndTest extends ApplicationTestCase<Application> {
    CountDownLatch latch = null;

    public GCEBackEndTest () {

        super(Application.class);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        if(latch.getCount() > 0 ) {

            latch.countDown();
        }
    }

    @Override
    protected void setUp() throws Exception {
        // init latch to have count 1
        latch = new CountDownLatch(1); // set 1 as we only have 1 task to wait
        super.setUp();
    }

   // @SuppressLint("NewApi")
    @Test
    public void testGetJokeFromBackendAPI() throws Throwable {
           GCE_EndpointsAsyncTask task = new GCE_EndpointsAsyncTask(new GCE_EndpointsAsyncTask.AsyncResponse() {
               @Override
               public void processFinish(String result) {
                   
                   assertNotNull("Error:Unable to get the joke!",result); // assert the joke is not null!
                   assertTrue("Joke size is 0", (result.length() > 0));
                   latch.countDown(); // decrease the count of latch to 0 to resume the Testing thread

               }
           });

          task.setRunLocal(true); // tried also to run against the local backend app - need to start up the local backend first
          task.execute(new Pair<Context, String>(this.getContext(), "Manfred"));
           // Testing thread will wait util AsyncTask finish its task
          latch.await();


    }

}
