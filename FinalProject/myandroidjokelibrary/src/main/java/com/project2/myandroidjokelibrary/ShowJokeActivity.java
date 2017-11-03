package com.project2.myandroidjokelibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class ShowJokeActivity extends AppCompatActivity {
    private static final String TAG = ShowJokeActivity.class.getSimpleName();
    public static final String JOKE_KEY = "joke_key";
    public static final String JOKE_LIST_KEY = "joke_list_key";
    public String mCurrentJoke = "default";
    //JokeFragment mJokeFragment;
    private TextView mJokeTextContent;
    ArrayList<String> mJokeList = new ArrayList<String>(); // no joke until we get jokes from joke library
    int currentJokeIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.e(TAG, "AndroidLibrary - onCreate!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        // inflat the JokeContent
        mJokeTextContent = (TextView) findViewById(R.id.joke_content);

      //  FragmentManager fragmentManager = getSupportFragmentManager();
       // mJokeFragment = (JokeFragment) fragmentManager.findFragmentById(R.id.joke_fragment);

        if (savedInstanceState!= null) {

//            if (savedInstanceState.containsKey(ShowJokeActivity.JOKE_LIST_KEY)) {
//                mJokeList = savedInstanceState.getStringArrayList(ShowJokeActivity.JOKE_LIST_KEY);
//
//            }

            if (savedInstanceState.containsKey(ShowJokeActivity.JOKE_KEY)) {
                mCurrentJoke = savedInstanceState.getString(ShowJokeActivity.JOKE_KEY);
                updateJokeContent(mCurrentJoke);
            }
        } else {
            // start new activity
            Log.d(TAG,"Start ShowJokeActivity from MainActivity!");
            Intent intent = getIntent();
            Bundle bundle = intent.getBundleExtra("bundle");
            // TODO: get the joke out from intent
            // String joke = (String) bundle.get(JOKE_KEY);
            if (bundle != null && bundle.containsKey(ShowJokeActivity.JOKE_KEY)) {
                mCurrentJoke = bundle.getString(ShowJokeActivity.JOKE_KEY);
                updateJokeContent(mCurrentJoke);
            }

        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // if home button is selected
        switch (item.getItemId()) {
            // add this back to end the current activity
            case android.R.id.home:
                Log.d(TAG, "home/up button is clicked");
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO : save the current Joke when rotation occurs
        outState.putString(JOKE_KEY, mCurrentJoke);
        // outState.putStringArrayList(JOKE_LIST_KEY, mJokeList);
        super.onSaveInstanceState(outState);
    }

    /**
     * set current joke
     * @param joke
     */
    private void setCurrentJoke(String joke) {
        mCurrentJoke = joke;
    }

    private String getNextJoke() {
        String joke  = null;
        if(mJokeList!=null && mJokeList.size() > 1) {
            // we have jokes
            int jokeIndex = currentJokeIndex + 1;
            if (jokeIndex == mJokeList.size()-1) {
                // last joke from the list
                joke =  mJokeList.get(jokeIndex);
                // reset currentJokeIndex to 0 again
                currentJokeIndex = 0;
                //return joke;
            }
            if (jokeIndex < mJokeList.size()-1) {
                joke = mJokeList.get(jokeIndex);
                currentJokeIndex++; // increase the index to get Next Joke
                //return joke;
            }

        }

        return joke;
    }

    public void updateJokeList(ArrayList<String> jokeList) {
        mJokeList = jokeList;
    }

    public void updateJokeContent(String joke) {
        mCurrentJoke = joke;
        mJokeTextContent.setText(mCurrentJoke);
    }
}
