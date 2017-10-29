package com.udacity.gradle.builditbigger.paid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.project2.myandroidjokelibrary.ShowJokeActivity;
import com.udacity.gradle.builditbigger.BuildConfig;
import com.udacity.gradle.builditbigger.GCE_EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.GetJokeAsyncTask;
import com.udacity.gradle.builditbigger.R;

import java.util.ArrayList;

//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;
//import com.google.android.gms.ads.InterstitialAd;
//import com.google.android.gms.ads.MobileAds;


/**
 * A placeholder fragment containing a simple view.
 */
public class PaidActivityFragment extends Fragment implements GCE_EndpointsAsyncTask.AsyncResponse, GetJokeAsyncTask.AsyncResponse  {

    // App ID: ca-app-pub-3160158119336562~7703910721
    /*Follow the SDK integration guide. Specify ad type, size, and placement when you integrate the code.
    App ID: ca-app-pub-3160158119336562~7703910721
    Ad unit ID: ca-app-pub-3160158119336562/5237884703
    */
    private static final String AD_MOB_APP_ID = "ca-app-pub-3160158119336562~7703910721";
    private static final String AD_MOB_UNIT_ID = "ca-app-pub-3160158119336562/5237884703";
    private static final String freeApplicationIdSuffix  = "free";
    private static final String paidApplicationIdSuffix  = "paid";
    private static final String TAG = com.udacity.gradle.builditbigger.paid.PaidActivityFragment.class.getSimpleName();
    private ProgressBar mIndicator;
    private Button mPokeJokeButton;
    ArrayList<String> mJokeList = new ArrayList<String>(); // no joke until we get jokes from joke library
    int currentJokeIndex = 0;
    String mJoke = "default joke";
    // private static MyApi myApiService = null;
    // private InterstitialAd mInterstitial;

    public PaidActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.activity_paid, container, false);

        mIndicator = (ProgressBar) root.findViewById(R.id.progress_indicator);
        mPokeJokeButton = (Button) root.findViewById(R.id.but_poke_joke);
        // AdView mAdView = (AdView) root.findViewById(R.id.adView);

        mPokeJokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO: Add Interstitial Adv-
                // Follow these instructions to add an interstitial ad to the free version.
                // Display the ad after the user hits the button, and before the joke is shown.

                // Only show when it is a FREE application
//                if( mInterstitial!=null && mInterstitial.isLoaded()) {
//                    mInterstitial.show();
//                } else {
//
//                    Toast.makeText(getContext(), "Do not show the Ads!", Toast.LENGTH_SHORT).show();
//                }

                boolean useBackEnd=true;
                tellJokeByGCEModule(useBackEnd);
            }
        });


//
//  TODO:      Step 4: Add Functional Tests
//        Add code to test that your Async task successfully retrieves a non-empty string. For a refresher on setting up Android tests, check out demo 4.09.
//
//  TODO:      Step 5: Add a Paid Flavor
//        Add free and paid product flavors to your app. Remove the ad (and any dependencies you can) from the paid flavor. IMPORTANT: You do not need Google AdMob in the Manifest of the paid version of the app. Make sure to include this only with the free/ manifest. Also make sure to add Google Play Services only to the free version of the app in Gradle.
//
        Toast.makeText(getContext(), "what is buildConfig.Flavor?" + BuildConfig.FLAVOR , Toast.LENGTH_SHORT).show();
//        if (BuildConfig.FLAVOR.contains(freeApplicationIdSuffix)) {
//            Toast.makeText(getContext(), "This is a free version", Toast.LENGTH_SHORT).show();
//            Log.d(TAG, "This is a free version - show ads!!");
//            // initializse Mobile Ads SDK with the AdMob App ID
//            MobileAds.initialize(getActivity(), AD_MOB_APP_ID);
//
//            mInterstitial= new InterstitialAd(getContext());
//            mInterstitial.setAdUnitId(AD_MOB_UNIT_ID); // TODO: replace ID please
//
//           // AdView mAdView = (AdView) root.findViewById(R.id.adView);
//            // Create an ad request. Check logcat output for the hashed device ID to
//            // get test ads on a physical device. e.g.
//            // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
//            AdRequest adRequest = new AdRequest.Builder()
//                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
//                    .build();
//            // mAdView.loadAd(adRequest);
//            mInterstitial.loadAd(adRequest);
//            // mInterstitial.setAdListener(this);
//
//        } else {
//            Toast.makeText(getContext(), "This is a paid version", Toast.LENGTH_SHORT).show();
//            Log.d(TAG, "Great! This is a PAID version - no ads!!");
//        }

        return root;
    }

    /**
     * set progress indicator to gone when joke is retreived.
     */
    public void removeProgressIndicator() {
        if (mIndicator!=null) {
            mIndicator.setVisibility(View.GONE);
        }
    }

    /**
     * set progress indicator while loading Joke data
     */
    public void showProgressIndicator() {
        if (mIndicator!=null) {
            mIndicator.setVisibility(View.VISIBLE);
        }
    }


//   TODO:     Step 3: Create GCE Module
//        This next task will be pretty tricky. Instead of pulling jokes directly from our Java library, we'll set up a GCE development server, and pull our jokes from there. Follow the instructions in this tutorial to add a GCE module to your project:
//        Introduce a project dependency between your Java library and your GCE module, and modify the GCE starter code to pull jokes from your Java library. Create an Async task to retrieve jokes. Make the button kick off a task to retrieve a joke, then launch the activity from your Android Library to display it.
    public void tellJokeByGCEModule(boolean runByEndPoint) {

        showProgressIndicator();

       // To make the actual call, invoke this EndpointsAsyncTask from one of your Android activities. For example, to execute it from MainActivity class, add the following code snippet to MainActivity.onCreate method:
        if (runByEndPoint) {
            /*
               Introduce a project dependency between your Java library and your GCE module,
               and modify the GCE starter code to pull jokes from your Java library.
               Create an Async task to retrieve jokes.
               Make the button kick off a task to retrieve a joke,
               then launch the activity from your Android Library to display it.
             */
            new GCE_EndpointsAsyncTask(this).execute(new Pair<Context, String>(getActivity(), "Manfred"));
        } else {
            // Get the Joke data by using the AsyncTask
            new GetJokeAsyncTask(this).execute();
        }

    }

    // callback from the EndpointAsyncTask to pass back the result to the Fragment
    @Override
    public void processFinish(String result) {
        mJoke = result;
        mJokeList.add(mJoke);
        Toast.makeText(getContext(), "OnPostExecute:" + result, Toast.LENGTH_LONG).show();

        // remove the progress indicator
        removeProgressIndicator();

        // else we have joke data
        // TODO: pass the Joke here as the Intent to the AndroidLibrary 's Class
        Log.e(TAG,"Pass the Data to the ShowJokeActivity");
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(ShowJokeActivity.JOKE_LIST_KEY, mJokeList);
        Intent jokeIntent = new Intent(getActivity(), ShowJokeActivity.class);
        jokeIntent.putExtra("bundle",bundle);
        startActivity(jokeIntent);
    }

    @Override
    public void getFromLibraryProcessFinish(ArrayList<String> jokeList) {

        mJokeList = jokeList;
        if(mJokeList!= null && mJokeList.size() == 0) {
            mJokeList.add(getString(R.string.defaultJoke));
        }
        // remove the progress indicator
        removeProgressIndicator();

        // else we have joke data
        // TODO: pass the Joke here as the Intent to the AndroidLibrary 's Class
        Log.e(TAG,"Pass the Data to the ShowJokeActivity");
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(ShowJokeActivity.JOKE_LIST_KEY, mJokeList);
        Intent jokeIntent = new Intent(getActivity(), ShowJokeActivity.class);
        jokeIntent.putExtra("bundle",bundle);
        startActivity(jokeIntent);
    }

    // Use an async task to do the data fetch off of the main thread.

//    public class GetJokeAsyncTask extends AsyncTask<Void, Void, ArrayList<String>> {
//        // Invoked on UI thread before the task is executed on a background thread
//        @Override
//        protected void onPreExecute() {
//            showProgressIndicator();
//            super.onPreExecute();
//        }
//
//        // Invoked on a background thread
//        @Override
//        protected ArrayList<String> doInBackground(Void... params) {
//            // Use the Java library's to get the Joke data
//            ArrayList<String> jokeListFromJavaLib = JokesLibraryClass.getJokes();
//            return jokeListFromJavaLib;
//        }
//
//        // Invoked on UI thread
//        @Override
//        protected void onPostExecute(ArrayList<String> jokeList) {
//            super.onPostExecute(jokeList);
//            // init the local Joke list after getting it from the Joke library
//            mJokeList = jokeList;
//            removeProgressIndicator();
//
//            // Start the ShowJokeActivity to show the Joke
//            Log.e(TAG,"Pass the Data to the ShowJokeActivity");
//            Bundle bundle = new Bundle();
//            bundle.putStringArrayList(ShowJokeActivity.JOKE_LIST_KEY, mJokeList);
//            Intent jokeIntent = new Intent(getActivity(), ShowJokeActivity.class);
//            jokeIntent.putExtra("bundle",bundle);
//            startActivity(jokeIntent);
//        }
//    }
//

}
