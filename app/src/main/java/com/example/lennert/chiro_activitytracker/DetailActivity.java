package com.example.lennert.chiro_activitytracker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);



        //mCalender = (CalendarView) findViewById(R.id.calendar);

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
        /*
        //LES 5
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(NAME_ACTIVITY_TEXT_KEY)) {
                String previousText = savedInstanceState
                        .getString(NAME_ACTIVITY_TEXT_KEY);
                mEditNameActivity.setText(previousText);
            }
            if (savedInstanceState.containsKey(DESCRIPTION_ACTIVITY_TEXT_KEY)) {
                String previousText = savedInstanceState
                        .getString(DESCRIPTION_ACTIVITY_TEXT_KEY);
                mEditDescriptionActivity.setText(previousText);
            }
            if (savedInstanceState.containsKey(NUMBER_OF_MEMBERS_TEXT_KEY)) {
                String previousText = savedInstanceState
                        .getString(NUMBER_OF_MEMBERS_TEXT_KEY);
                mEditNumberOfMembers.setText(previousText);
            }
            if (savedInstanceState.containsKey(NUMBER_OF_DRINKS_TEXT_KEY)) {
                String previousText = savedInstanceState
                        .getString(NUMBER_OF_DRINKS_TEXT_KEY);
                mEditNumberOfDrinks.setText(previousText);
            }
            if (savedInstanceState.containsKey(RATING_TEXT_KEY)) {
                String previousText = savedInstanceState
                        .getString(RATING_TEXT_KEY);
                float previousRating = Float.parseFloat(previousText);
                //mEditRating.setRating(previousRating);
            }

        } */
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
    /*
    //LES 5
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        logAndAppend(ON_SAVE_INSTANCE_STATE);

        String editNameActivity = mEditNameActivity.getText().toString();
        String editDescriptionActivity = mEditDescriptionActivity.getText().toString();
        String editNumberOfMembers = mEditNumberOfMembers.getText().toString();
        String editNumberOfDrinks = mEditNumberOfDrinks.getText().toString();

        outState.putString(NAME_ACTIVITY_TEXT_KEY,editNameActivity);
        outState.putString(DESCRIPTION_ACTIVITY_TEXT_KEY,editDescriptionActivity);
        outState.putString(NUMBER_OF_MEMBERS_TEXT_KEY,editNumberOfMembers);
        outState.putString(NUMBER_OF_DRINKS_TEXT_KEY,editNumberOfDrinks);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            String editRating = String.valueOf(mEditRating.getStarRating());
            outState.putString(RATING_TEXT_KEY,editRating);
        }
        else{
            if (mToast != null){
                mToast.cancel();
            }

            String toastMessage = "This API doesn't support the rating";
            mToast = Toast.makeText(this,toastMessage,Toast.LENGTH_LONG);

            mToast.show();
        }

    } */

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
