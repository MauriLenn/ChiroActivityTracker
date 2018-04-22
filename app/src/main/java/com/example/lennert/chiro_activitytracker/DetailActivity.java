package com.example.lennert.chiro_activitytracker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//LES 4

public class DetailActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {


    //LOGGING
    private static final String ON_SAVE_INSTANCE_STATE = "onSaveInstanceState";
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String NAME_ACTIVITY_TEXT_KEY = "name activity";
    private static final String DESCRIPTION_ACTIVITY_TEXT_KEY = "description activity";
    private static final String NUMBER_OF_MEMBERS_TEXT_KEY = "number of members";
    private static final String NUMBER_OF_DRINKS_TEXT_KEY = "number of drinks";
    private static final String RATING_TEXT_KEY = "rating";

    private TextView mTitleSaturday;
    private TextView mNameActivity;
    private TextView mDescriptionActivity;
    private TextView mNumberOfMembers;
    private TextView mNumberOfDrinks;
    private TextView mRating;

    private EditText mEditNameActivity;
    private EditText mEditDescriptionActivity;
    private EditText mEditNumberOfMembers;
    private EditText mEditNumberOfDrinks;

    private Rating mEditRating;
    private Toast mToast;

    private SQLiteDatabase mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mDescriptionActivity = findViewById(R.id.tv_descriptionActivity);
        mNameActivity = findViewById(R.id.tv_nameActivity);
        mNumberOfMembers = findViewById(R.id.tv_numberOfMembers);
        mNumberOfDrinks = findViewById(R.id.tv_numberOfDrinks);
        mRating = findViewById(R.id.tv_rating);

        mEditNameActivity = findViewById(R.id.et_nameActivity);
        mEditDescriptionActivity = findViewById(R.id.et_descriptionActivity);
        mEditNumberOfMembers = findViewById(R.id.et_numberOfMembers);
        mEditNumberOfDrinks = findViewById(R.id.et_numberOfDrinks);

        mTitleSaturday = findViewById(R.id.tv_titleSaturday);

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)){
            String selectedSaturday = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
            mTitleSaturday.setText(selectedSaturday);
        }

        setupSharedPreferences();

        SQLDBHelper sqldbHelper = new SQLDBHelper(this);
        mDB = sqldbHelper.getWritableDatabase();

    }

    private void setupSharedPreferences(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        if (sharedPreferences.getBoolean(getString(R.string.pref_kindOfUser),getResources().getBoolean(R.bool.pref_kindOfUser_default))){
            mNumberOfDrinks.setVisibility(View.GONE);
            mEditNumberOfDrinks.setVisibility(View.GONE);

            mNumberOfMembers.setVisibility(View.GONE);
            mEditNumberOfMembers.setVisibility(View.GONE);
        }
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Use AppCompatActivity's method getMenuInflater to get a handle on the menu inflater */
        MenuInflater inflater = getMenuInflater();
        /* Use the inflater's inflate method to inflate our menu layout to this menu */
        inflater.inflate(R.menu.detail, menu);
        /* Return true so that the menu is displayed in the Toolbar */
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemThatWasSelected = item.getItemId();


        if (menuItemThatWasSelected == R.id.action_ok) {
            //
            //gegevens uploaden
            //
            Intent startMainActivityIntent = new Intent(DetailActivity.this,MainActivity.class);
            startActivity(startMainActivityIntent);
            return true;
        }

        if (menuItemThatWasSelected == R.id.action_back) {
            Intent startMainActivityIntent = new Intent(DetailActivity.this,MainActivity.class);
            startActivity(startMainActivityIntent);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.pref_kindOfUser))){
            if (sharedPreferences.getBoolean(key, getResources().getBoolean(R.bool.pref_kindOfUser_default))){
                mNumberOfDrinks.setVisibility(View.GONE);
                mEditNumberOfDrinks.setVisibility(View.GONE);

                mNumberOfMembers.setVisibility(View.GONE);
                mEditNumberOfMembers.setVisibility(View.GONE);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    }

    private void logAndAppend(String Event) {
        Log.d(TAG, "Event: " + Event);

    }


}
