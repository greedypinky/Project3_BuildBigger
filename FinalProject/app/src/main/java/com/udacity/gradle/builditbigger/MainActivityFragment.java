package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.android.gms.ads.MobileAds;


import com.joke.JokesLibraryClass;
import com.project2.myandroidjokelibrary.ShowJokeActivity;
import com.udacity.jokeproject.backend.myApi.MyApi;

import java.io.IOException;
import java.util.ArrayList;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static final String freeApplicationIdSuffix  = ".free";
    private static final String paidApplicationIdSuffix  = ".paid";
    private static final String TAG = MainActivityFragment.class.getSimpleName();
    private ProgressBar mIndicator;
    private Button mPokeJokeButton;
    ArrayList<String> mJokeList = new ArrayList<String>(); // no joke until we get jokes from joke library
    int currentJokeIndex = 0;
    String mJoke = "default joke";
    private static MyApi myApiService = null;
    private InterstitialAd mInterstitial;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mIndicator = (ProgressBar) root.findViewById(R.id.progress_indicator);
        mPokeJokeButton = (Button) root.findViewById(R.id.but_poke_joke);
        AdView mAdView = (AdView) root.findViewById(R.id.adView);

        mPokeJokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Only show when it is a FREE application
                if( mInterstitial!=null && mInterstitial.isLoaded()) {

                    mInterstitial.show();
                }

                //tellJoke(v);
                tellJokeByGCEModule(true);
            }
        });


        //getContext().getApplicationInfo()

        // TODO: Add Interstitial Adv-
        // Follow these instructions to add an interstitial ad to the free version.
        // Display the ad after the user hits the button, and before the joke is shown.
//
//   TODO:     Step 3: Create GCE Module
//        This next task will be pretty tricky. Instead of pulling jokes directly from our Java library, we'll set up a GCE development server, and pull our jokes from there. Follow the instructions in this tutorial to add a GCE module to your project:
//
//        Introduce a project dependency between your Java library and your GCE module, and modify the GCE starter code to pull jokes from your Java library. Create an Async task to retrieve jokes. Make the button kick off a task to retrieve a joke, then launch the activity from your Android Library to display it.
//
//  TODO:      Step 4: Add Functional Tests
//        Add code to test that your Async task successfully retrieves a non-empty string. For a refresher on setting up Android tests, check out demo 4.09.
//
//  TODO:      Step 5: Add a Paid Flavor
//        Add free and paid product flavors to your app. Remove the ad (and any dependencies you can) from the paid flavor. IMPORTANT: You do not need Google AdMob in the Manifest of the paid version of the app. Make sure to include this only with the free/ manifest. Also make sure to add Google Play Services only to the free version of the app in Gradle.
//
/*
        if (BuildConfig.FLAVOR.contains(freeApplicationIdSuffix)) {
            // initializse Mobile Ads SDK with the AdMob App ID
            //MobileAds.initialize(getActivity(),"your admod app id");
            mInterstitial= new InterstitialAd(getContext());
            mInterstitial.setAdUnitId("ID"); // TODO: replace ID please

           // AdView mAdView = (AdView) root.findViewById(R.id.adView);
            // Create an ad request. Check logcat output for the hashed device ID to
            // get test ads on a physical device. e.g.
            // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .build();
            // mAdView.loadAd(adRequest);
            mInterstitial.loadAd(adRequest);

        } else {

            Log.d(TAG, "Great! This is a PAID version - no ads!!");
        } */

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

    // Press the button will trigger this method
    public void tellJoke(View view)
    {
        // TODO: Add a loading indicator
        // that is shown while the joke is being retrieved,
        // and disappears when the joke is ready. This tutorial is a good place to start.
        Toast.makeText(getContext(), "Poke a joke from Joke Library!!", Toast.LENGTH_SHORT).show();
        if (mJokeList!=null && mJokeList.size() == 0) {
            Log.e(TAG,"Get Joke from Java Library");
            // TODO: we need to get the data from the Joke library
            Log.d(TAG,"call the GetJokeAsyncTask");
            // Get the Joke data by using the AsyncTask
            new GetJokeAsyncTask().execute();

            //ArrayList<String> jokeListFromJavaLib = JokesLibraryClass.getJokes();
            //mJokeList = jokeListFromJavaLib;

        }

        // else we have joke data
        // TODO: pass the Joke here as the Intent to the AndroidLibrary 's Class
        Log.e(TAG,"Pass the Data to the ShowJokeActivity");
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(ShowJokeActivity.JOKE_LIST_KEY, mJokeList);
        Intent jokeIntent = new Intent(getActivity(), ShowJokeActivity.class);
        jokeIntent.putExtra("bundle",bundle);
        startActivity(jokeIntent);

    }

    public void tellJokeByGCEModule(boolean runByEndPoint) {

       // To make the actual call, invoke this EndpointsAsyncTask from one of your Android activities. For example, to execute it from MainActivity class, add the following code snippet to MainActivity.onCreate method:
        if (runByEndPoint) {
            /*
               Introduce a project dependency between your Java library and your GCE module,
               and modify the GCE starter code to pull jokes from your Java library.
               Create an Async task to retrieve jokes.
               Make the button kick off a task to retrieve a joke,
               then launch the activity from your Android Library to display it.
             */
            new GCE_EndpointsAsyncTask().execute(new Pair<Context, String>(getActivity(), "Manfred"));
        } else {
            // Get the Joke data by using the AsyncTask
            new GetJokeAsyncTask().execute();
        }
    }

    // Use an async task to do the data fetch off of the main thread.

    public class GetJokeAsyncTask extends AsyncTask<Void, Void, ArrayList<String>> {
        // Invoked on UI thread before the task is executed on a background thread
        @Override
        protected void onPreExecute() {
            showProgressIndicator();
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
            // init the local Joke list after getting it from the Joke library
            mJokeList = jokeList;
            removeProgressIndicator();
        }
    }

    public class GCEModuleAsyncTask extends AsyncTask<Void, Void, ArrayList<String>> {



        // Invoked on a background thread
        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            // Make the query to get the data
            showProgressIndicator();
            // Use GCEModule Server to get the Data
            return null;
        }


        // Invoked on UI thread
        @Override
        protected void onPostExecute(ArrayList<String> jokeList) {
            super.onPostExecute(jokeList);
            // init the local Joke list after getting it from the Joke library
            mJokeList = jokeList;
            removeProgressIndicator();

        }
    }


   /*
   1.gcloud init
Welcome! This command will take you through the configuration of gcloud.
   You are logged in as: [marukotest888@gmail.com].
  2.gcloud app create
Pick cloud project to use:
 [1] fir-demo-project
 [2] pinky-project
 [3] telljokeproject
 [4] Create a new project
Please enter numeric choice or text value (must exactly match list
item):  3

   Creating App Engine application in project [telljokeproject] and region [us-central]....done.
            Success! The app is now created. Please use `gcloud app deploy` to deploy your first app.
            mobiles-MacBook-Pro:bin ritalaw$ gcloud app deploy
    WARNING: Automatic app detection is currently in Beta
    Deployment to Google App Engine requires an app.yaml file. This
    command will run `gcloud beta app gen-config` to generate an app.yaml
    file for you in the current directory (if the current directory does
            not contain an App Engine service, please answer "no").
            descriptor:      [/Users/ritalaw/Downloads/google-cloud-sdk/bin/app.yaml]
source:          [/Users/ritalaw/Downloads/google-cloud-sdk/bin]
target project:  [telljokeproject]
target service:  [default]
target version:  [20171023t095607]
target url:      [https://telljokeproject.appspot.com]

 3. gcloud app deploy
 4. https://cloud.google.com/endpoints/docs/frameworks/java/calling-from-android
            */
    // known issue
    // https://github.com/google/apis-client-generator/issues/34
    // name : JokeProject
    // id : jokeproject-183804
     class GCE_EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
        // private static MyApi myApiService = null;
        //private MyApi myApiService = null;
        private Context context;

        @Override
        protected void onPreExecute() {
            showProgressIndicator();
            super.onPreExecute();
        }

        // https://discussions.udacity.com/t/project-build-it-bigger-error-while-creating-a-google-cloud-endpoints-gce/388662
        @Override
        protected String doInBackground(Pair<Context, String>... params) {
            if(myApiService == null) {  // Only do this once
                /*
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        // options for running against local devappserver
                        // - 10.0.2.2 is localhost's IP address in Android emulator
                        // - turn off compression when running against local devappserver
                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                // end options for devappserver
                */

//                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
//                        .setRootUrl("https://android-app-backend.appspot.com/_ah/api/");

                // where android-app-backend corresponds to your own Project ID created in section 2.2.
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        .setRootUrl("https://jokeproject-183804.appspot.com/_ah/api/");

                myApiService = builder.build();
            }

            context = params[0].first;
            String name = params[0].second;

            try {
                // return myApiService.sayHi(name).execute().getData();
                return myApiService.sayJoke().execute().getData();
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
            // init the joke data after getting it from the EndPoint's API Service
            Log.d(TAG,"Joke from the EndPoint's API Service:" + result);
            mJoke = result;
            mJokeList.add(mJoke);
            removeProgressIndicator();
        }
    }


}