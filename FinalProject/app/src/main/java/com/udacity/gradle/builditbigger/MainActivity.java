package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;


import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    MainActivityFragment mGetJokeFragment;
    ArrayList<String> mJokeList = new ArrayList<String>(); // no joke until we get jokes from joke library
    int currentJokeIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        mGetJokeFragment = (MainActivityFragment) fragmentManager.findFragmentById(R.id.getjoke_fragment);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Press the button will trigger this method
//    public void tellJoke(View view)
//    {
//        // TODO: Add a loading indicator
//        // that is shown while the joke is being retrieved,
//        // and disappears when the joke is ready. This tutorial is a good place to start.
//        Toast.makeText(this, "Poke a joke from Joke Library!!", Toast.LENGTH_SHORT).show();
//        if (mJokeList!=null && mJokeList.size() == 0) {
//            Log.e(TAG,"Get Joke from Java Library");
//            // TODO: we need to get the data from the Joke library
//            Log.d(TAG,"call the GetJokeAsyncTask");
//            //mGetJokeFragment.showProgressIndicator();
//            // new GetJokeAsyncTask().execute();
//            //mGetJokeFragment.removeProgressIndicator();
//            ArrayList<String> jokeListFromJavaLib = JokesLibraryClass.getJokes();
//            mJokeList = jokeListFromJavaLib;
//
//        }
//
//        // else we have joke data
//        // TODO: pass the Joke here as the Intent to the AndroidLibrary 's Class
//        Log.e(TAG,"Pass the Data to the ShowJokeActivity");
//        Bundle bundle = new Bundle();
//        bundle.putStringArrayList(ShowJokeActivity.JOKE_LIST_KEY, mJokeList);
//        Intent jokeIntent = new Intent(getApplicationContext(), ShowJokeActivity.class);
//        startActivity(jokeIntent);
//
//    }


//    private String getNextJoke() {
//        String joke  = null;
//         if(mJokeList!=null && mJokeList.size() > 1) {
//             // we have jokes
//             int jokeIndex = currentJokeIndex + 1;
//             if(jokeIndex == mJokeList.size()-1) {
//                 // last joke from the list
//                 joke =  mJokeList.get(jokeIndex);
//                 // reset currentJokeIndex to 0 again
//                 currentJokeIndex = 0;
//                 //return joke;
//             }
//             if(jokeIndex < mJokeList.size()-1) {
//                 joke = mJokeList.get(jokeIndex);
//                 currentJokeIndex++; // increase the index to get Next Joke
//                 //return joke;
//             }
//
//         }
//
//         return joke;
//    }

    // TODO: Configure Test Task
    /*
        To tie it all together, create a Gradle task that:
        Launches the GCE local development server (in Daemon mode, so it doesn't block further execution)
        Runs all tests
        Shuts the server down again
    */





}
