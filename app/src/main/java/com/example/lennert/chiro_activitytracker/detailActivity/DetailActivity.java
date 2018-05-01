package com.example.lennert.chiro_activitytracker.detailActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lennert.chiro_activitytracker.mainActivity.MainActivity;
import com.example.lennert.chiro_activitytracker.R;

import java.util.ArrayList;

//LES 4

public class DetailActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {


    //LOGGING
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final String SAVE = "activity is saved and go main";
    private static final String EXIT = "go back to main";
    private static final String DB_OUT = "implemented info out of database";
    private static final String DB_IN =  "implemented activity in database: ";
    //

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
    private RatingBar mEditRating;

    private SQLiteDatabase mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mTitleSaturday = findViewById(R.id.tv_titleSaturday);

        mDescriptionActivity = findViewById(R.id.tv_descriptionActivity);
        mNameActivity = findViewById(R.id.tv_nameActivity);
        mNumberOfMembers = findViewById(R.id.tv_numberOfMembers);
        mNumberOfDrinks = findViewById(R.id.tv_numberOfDrinks);
        mRating = findViewById(R.id.tv_rating);

        mEditNameActivity = findViewById(R.id.et_nameActivity);
        mEditDescriptionActivity = findViewById(R.id.et_descriptionActivity);
        mEditNumberOfMembers = findViewById(R.id.et_numberOfMembers);
        mEditNumberOfDrinks = findViewById(R.id.et_numberOfDrinks);
        mEditRating = findViewById(R.id.rating);


        //MENU
        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)){
            String selectedSaturday = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
            mTitleSaturday.setText(selectedSaturday);
        }

        //PREFERENCES
        setupSharedPreferences();

        //DATABASE SQL
        SQLDBHelper sqldbHelper = new SQLDBHelper(this);
        mDB = sqldbHelper.getWritableDatabase();
        setDetailActivity();
    }


    //MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemThatWasSelected = item.getItemId();

        if (menuItemThatWasSelected == R.id.action_ok) {
            addToDatabase(item);

            Intent startMainActivityIntent = new Intent(DetailActivity.this,MainActivity.class);
            infoLogAndAppend(SAVE);
            startActivity(startMainActivityIntent);
            return true;
        }

        if (menuItemThatWasSelected == R.id.action_back) {
            Intent startMainActivityIntent = new Intent(DetailActivity.this,MainActivity.class);
            infoLogAndAppend(EXIT);
            startActivity(startMainActivityIntent);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    //PREFERENCES
    private void setupSharedPreferences(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        if (sharedPreferences.getBoolean(getString(R.string.pref_kindOfUser),getResources().getBoolean(R.bool.pref_kindOfUser_default))){
            mNumberOfDrinks.setVisibility(View.GONE);
            mEditNumberOfDrinks.setVisibility(View.GONE);

            mNumberOfMembers.setVisibility(View.GONE);
            mEditNumberOfMembers.setVisibility(View.GONE);
        }
        loadColorFromPreferences(sharedPreferences);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
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
        if (key.equals(getString(R.string.pref_color_key))){
            loadColorFromPreferences(sharedPreferences);
        }
    }

    private void loadColorFromPreferences(SharedPreferences sharedPreferences){
        setColor(sharedPreferences.getString(getString(R.string.pref_color_key),getString(R.string.pref_color_neutral_value)));
    }

    public void setColor(String newColorKey) {
        if (newColorKey.equals(getString(R.string.pref_color_brown_value))) {
           mTitleSaturday.setBackgroundColor(getResources().getColor(R.color.kabouterBrown));
        }
        else if (newColorKey.equals(getString(R.string.pref_color_lightGreen_value))) {
            mTitleSaturday.setBackgroundColor(getResources().getColor(R.color.speelclubGreen));
        }
        else if (newColorKey.equals(getString(R.string.pref_color_darkGreen_value))) {
            mTitleSaturday.setBackgroundColor(getResources().getColor(R.color.rakkerGreen));
        }
        else if (newColorKey.equals(getString(R.string.pref_color_red_value))) {
            mTitleSaturday.setBackgroundColor(getResources().getColor(R.color.topperRed));
        }
        else if (newColorKey.equals(getString(R.string.pref_color_blue_value))) {
            mTitleSaturday.setBackgroundColor(getResources().getColor(R.color.kerelBlue));
        }
        else if (newColorKey.equals(getString(R.string.pref_color_orange_value))) {
            mTitleSaturday.setBackgroundColor(getResources().getColor(R.color.aspiOrange));
        }
        else {
            mTitleSaturday.setBackgroundColor(getResources().getColor(R.color.white));
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    }


    //DATABASE SQL
    private void setDetailActivity(){
        ArrayList<String> chiroActivity = new ArrayList<>();

        Cursor cursorNameActivity = getNameActivity();
        Cursor cursorDescriptionActivity = getDescriptionActivity();
        Cursor cursorMembers = getMembers();
        Cursor cursorDrinks = getDrinks();
        Cursor cursorRating = getRating();

        if (cursorNameActivity != null && cursorNameActivity.moveToFirst()){
            String nameActivity = cursorNameActivity.getString(cursorNameActivity.getColumnIndex("name_activity"));
            chiroActivity.add(nameActivity);
            mEditNameActivity.setText(chiroActivity.get(0));
            cursorNameActivity.close();
        }
        if (cursorDescriptionActivity != null && cursorDescriptionActivity.moveToFirst()){
            String descriptionActivity = cursorDescriptionActivity.getString(cursorDescriptionActivity.getColumnIndex("description_activity"));
            chiroActivity.add(descriptionActivity);
            mEditDescriptionActivity.setText(chiroActivity.get(1));
            cursorDescriptionActivity.close();
        }
        if (cursorMembers != null && cursorMembers.moveToFirst()){
            String members = cursorMembers.getString(cursorMembers.getColumnIndex("number_of_members"));
            chiroActivity.add(members);
            mEditNumberOfMembers.setText(chiroActivity.get(2));
            cursorMembers.close();
        }
        if (cursorDrinks != null && cursorDrinks.moveToFirst()){
            String drinks = cursorDrinks.getString(cursorDrinks.getColumnIndex("number_of_drinks"));
            chiroActivity.add(drinks);
            mEditNumberOfDrinks.setText(chiroActivity.get(3));
            cursorDrinks.close();
        }
        if (cursorRating != null && cursorRating.moveToFirst()){
            String rating = cursorRating.getString(cursorRating.getColumnIndex("rating"));
            chiroActivity.add(rating);
            mEditRating.setRating(Float.parseFloat(chiroActivity.get(4)));
            cursorRating.close();
        }
        infoLogAndAppend(DB_OUT);
        chiroActivity.clear();
    }

    public Cursor getNameActivity(){
        String query = "SELECT name_activity FROM Chiro WHERE date = '" + mTitleSaturday.getText().toString()+ "'";
        return mDB.rawQuery(query,null);
    }

    private Cursor getDescriptionActivity(){
        String query = "SELECT description_activity FROM Chiro WHERE date = '" + mTitleSaturday.getText().toString()+ "'";
        return mDB.rawQuery(query,null);
    }

    private Cursor getMembers(){
        String query = "SELECT number_of_members FROM Chiro WHERE date = '" + mTitleSaturday.getText().toString()+ "'";
        return mDB.rawQuery(query,null);
    }

    private Cursor getDrinks(){
        String query = "SELECT number_of_drinks FROM Chiro WHERE date = '" + mTitleSaturday.getText().toString()+ "'";
        return mDB.rawQuery(query,null);
    }

    private Cursor getRating(){
        String query = "SELECT rating FROM Chiro WHERE date = '" + mTitleSaturday.getText().toString()+ "'";
        return mDB.rawQuery(query,null);
    }

    private void addToDatabase (MenuItem item){
        if (mEditNameActivity.getText().length() == 0 ||
                mEditDescriptionActivity.getText().length() == 0) {
            return;
        }
        String date = mTitleSaturday.getText().toString();
        String name = mEditNameActivity.getText().toString();
        String description = mEditDescriptionActivity.getText().toString();
        int members = 0;
        int drinks = 0;

        float rating = 0;

        try {
            members = Integer.parseInt(mEditNumberOfMembers.getText().toString());
            drinks = Integer.parseInt(mEditNumberOfDrinks.getText().toString());

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                rating = mEditRating.getRating();
            } else
            {
                String message = "starRating is not supported because of the API " + Build.VERSION.SDK_INT
                        + " is too low";
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
            }
        } catch (NumberFormatException ex) {
            errorLogAndAppend("Failed to parse text to number: " + ex.getMessage());
        }
        addNewChiroActivity(date,name,description,members,drinks ,rating );
        infoLogAndAppend(DB_IN + date + " " + name);
    }

    private long addNewChiroActivity(String date,String name, String description, int members, int drinks, float rating){
        ContentValues cv = new ContentValues();
        cv.put(SQLContract.ChiroActivityEntry.COLUMN_DATE, date);
        cv.put(SQLContract.ChiroActivityEntry.COLUMN_NAME_Activity, name);
        cv.put(SQLContract.ChiroActivityEntry.COLUMN_DESCRIPTION_ACTIVITY, description);
        cv.put(SQLContract.ChiroActivityEntry.COLUMN_NUMBER_OF_MEMBERS, members);
        cv.put(SQLContract.ChiroActivityEntry.COLUMN_NUMBER_OF_DRINKS, drinks);
        cv.put(SQLContract.ChiroActivityEntry.COLUMN_RATING, rating);
        return mDB.insert(SQLContract.ChiroActivityEntry.TABLE_NAME,null,cv);
    }


    //LOGGING
    private void infoLogAndAppend(String Event) {
        Log.i(LOG_TAG, "Event: " + Event);
    }

    private void errorLogAndAppend(String Event) {
        Log.e(LOG_TAG, "Event: " + Event);
    }
}
