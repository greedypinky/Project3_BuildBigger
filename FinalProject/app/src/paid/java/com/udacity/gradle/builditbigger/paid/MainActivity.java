package com.udacity.gradle.builditbigger.paid;

import android.os.Bundle;
import android.support.v4.BuildConfig;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.udacity.gradle.builditbigger.R;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String PAID_SUFFIX= "paid";
    PaidActivityFragment mGetJokeFragment;
    ArrayList<String> mJokeList = new ArrayList<String>(); // no joke until we get jokes from joke library
    int currentJokeIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paid);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Toast.makeText(this, "what is buildConfig.Flavor?" + BuildConfig.FLAVOR , Toast.LENGTH_SHORT).show();
        if (BuildConfig.FLAVOR.contains(PAID_SUFFIX)) {
            Toast.makeText(this, "This is a paid version", Toast.LENGTH_SHORT).show();
            mGetJokeFragment = (PaidActivityFragment) fragmentManager.findFragmentById(R.id.paid_getjoke_fragment);

        }

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


}
