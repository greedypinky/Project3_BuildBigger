package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.joke.JokesLibraryClass;
import com.project2.myandroidjokelibrary.ShowJokeActivity;

import java.util.ArrayList;

public class GetJokeAsyncTask extends AsyncTask<Void, Void, ArrayList<String>> {

    private final String TAG = GetJokeAsyncTask.class.getSimpleName();
    // Invoked on UI thread before the task is executed on a background thread

    public GetJokeAsyncTask.AsyncResponse delegate = null; // activity will implement this method

    public GetJokeAsyncTask(AsyncResponse asyncResponse) {
        delegate = asyncResponse;
    }

    @Override
    protected void onPreExecute() {
       // showProgressIndicator();
        super.onPreExecute();
    }

    // Invoked on a background thread
    @Override
    protected ArrayList<String> doInBackground(Void... params) {
        // Use the Java library's to get the Joke data
        ArrayList<String> jokeListFromJavaLib = JokesLibraryClass.getJokes();
        return jokeListFromJavaLib;
    }

    // Invoked on UI thread
    @Override
    protected void onPostExecute(ArrayList<String> jokeList) {
        super.onPostExecute(jokeList);

        Log.d("GCE_EndpointsAsyncTask","Joke from the EndPoint's API Service:" + jokeList.size());
        if (delegate!=null) {
            delegate.getFromLibraryProcessFinish(jokeList); // use callback to pass back the result
        }
        // init the local Joke list after getting it from the Joke library
        // mJokeList = jokeList;
        //removeProgressIndicator();

        // Start the ShowJokeActivity to show the Joke
//        Log.e(TAG,"Pass the Data to the ShowJokeActivity");
//        Bundle bundle = new Bundle();
//        bundle.putStringArrayList(ShowJokeActivity.JOKE_LIST_KEY, mJokeList);
//        Intent jokeIntent = new Intent(getActivity(), ShowJokeActivity.class);
//        jokeIntent.putExtra("bundle",bundle);
//        startActivity(jokeIntent);
    }

    // Call back method
    public interface AsyncResponse {

        void getFromLibraryProcessFinish(ArrayList<String> jokeList);

    }
}
