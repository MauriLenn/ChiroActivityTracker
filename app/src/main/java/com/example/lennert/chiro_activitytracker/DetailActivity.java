package com.example.lennert.chiro_activitytracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

//LES 4

public class DetailActivity extends AppCompatActivity {

    //private CalendarView mCalender;

    private TextView mTitleSaturday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //mCalender = (CalendarView) findViewById(R.id.calendar);

        mTitleSaturday = findViewById(R.id.tv_titleSaturday);

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)){
            String selectedSaturday = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
            mTitleSaturday.setText(selectedSaturday);

        }
    }
}
