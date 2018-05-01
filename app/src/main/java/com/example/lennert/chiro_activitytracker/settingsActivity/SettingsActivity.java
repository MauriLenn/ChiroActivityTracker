package com.example.lennert.chiro_activitytracker.settingsActivity;

import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.lennert.chiro_activitytracker.R;

public class SettingsActivity extends AppCompatActivity{

    private static final String LOG_TAG = SettingsActivity.class.getSimpleName();
    private static final String BACK = "go to Startactivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ActionBar actionBar = this.getSupportActionBar();

        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemThatWasSelected = item.getItemId();
        if (menuItemThatWasSelected == R.id.home) {
            infoLogAndAppend(BACK);
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }

    //LOGGING
    private void infoLogAndAppend(String Event) {
        Log.i(LOG_TAG, "Event: " + Event);
    }
}
