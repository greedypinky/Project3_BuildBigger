package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.util.Log;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.jokeproject.backend.myApi.MyApi;

import java.io.IOException;


public class GCE_EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {

    private static MyApi myApiService = null;
    private Context context;

    public AsyncResponse delegate = null; // activity will implement this method


    public GCE_EndpointsAsyncTask(AsyncResponse asyncResponse) {

        delegate = asyncResponse;
    }

    public GCE_EndpointsAsyncTask() {

    }

    @Override
    protected void onPreExecute() {
        //showProgressIndicator();
        super.onPreExecute();
    }

    // https://discussions.udacity.com/t/project-build-it-bigger-error-while-creating-a-google-cloud-endpoints-gce/388662
    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if(myApiService == null) {  // Only do this once

            // options for running against local devappserver
            // - 10.0.2.2 is localhost's IP address in Android emulator
            // - turn off compression when running against local devappserver
//                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
//                        new AndroidJsonFactory(), null)
//                        .setRootUrl("http://192.168.1.71:8080/_ah/api/")
//                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
//                            @Override
//                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
//                                abstractGoogleClientRequest.setDisableGZipContent(true);
//                            }
//                        });
                // end options for devappserver

            //  Go to https://console.cloud.google.com/ to check the deployed link
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://telljokeproject.appspot.com/_ah/api/");


            myApiService = builder.build();
        }

        context = params[0].first;
        String name = params[0].second;

        try {
            // return myApiService.sayHi(name).execute().getData();
            return myApiService.sayJoke().execute().getJoke();
        } catch (IOException e) {
            return e.getMessage();
        }

    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context, "OnPostExecute:" + result, Toast.LENGTH_LONG).show();
        // init the joke data after getting it from the EndPoint's API Service
        Log.d("GCE_EndpointsAsyncTask","Joke from the EndPoint's API Service:" + result);
        if (delegate!=null) {
            delegate.processFinish(result); // use callback to pass back the result
        }
    }


    // Call back method
    public interface AsyncResponse {

        void processFinish(String result);

    }

    @Override
    protected void onCancelled(String s) {
        Log.d("GCE_EndpointsAsyncTask","The task has been cancelled!!" );
        super.onCancelled(s);
        if (delegate!=null) {
            delegate.processFinish(null); // use callback to pass back the result
        }
    }
}


