package com.example.lennert.chiro_activitytracker;

import android.content.Context;
import android.content.Intent;
import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//LES 4

public class DetailActivity extends AppCompatActivity {

    //private CalendarView mCalender;

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
            Intent startMainActivityIntent = new Intent(DetailActivity.this,MainActivity.class);
            startActivity(startMainActivityIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
