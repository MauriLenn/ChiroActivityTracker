package com.example.lennert.chiro_activitytracker;


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.Toolbar;

import static java.security.AccessController.getContext;

public class StartActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private Button mlistSaturdaysButton;
    private Button mchiroWebsiteButton;

    private DatePicker mCalendar;
    private ScrollView mscrollview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mlistSaturdaysButton = findViewById(R.id.bt_listChiroSaturdays);
        mchiroWebsiteButton = findViewById(R.id.bt_chiroWebsite);

        mCalendar = findViewById(R.id.calendar);

        mscrollview = findViewById(R.id.background_startActivity);

        setupSharedPreferences();
    }



    public void onClickOpenMainActivityButton(View view){
        Intent startMainActivityIntent = new Intent(StartActivity.this, MainActivity.class);

        if (startMainActivityIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(startMainActivityIntent);
        }

    }

    public void onClickOpenChiroWebsiteButton(View view){
        String urlAsString = "http://chiro-herk.be/menu.php";
        openChiroWebsite(urlAsString);
    }

    private void openChiroWebsite(String url){
        Uri chiroWebpage = Uri.parse(url);

        Intent chiroWebsiteIntent = new Intent(Intent.ACTION_VIEW,chiroWebpage);
        if (chiroWebsiteIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(chiroWebsiteIntent);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Use AppCompatActivity's method getMenuInflater to get a handle on the menu inflater */
        MenuInflater inflater = getMenuInflater();
        /* Use the inflater's inflate method to inflate our menu layout to this menu */
        inflater.inflate(R.menu.start, menu);
        /* Return true so that the menu is displayed in the Toolbar */
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemThatWasSelected = item.getItemId();


        if (menuItemThatWasSelected == R.id.action_settingsActivity) {
            Intent startSettingsActivityIntent = new Intent(StartActivity.this,SettingsActivity.class);
            startActivity(startSettingsActivityIntent);
        }

        return super.onOptionsItemSelected(item);

    }

    //PREFERENCES
    private void setupSharedPreferences(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        loadColorFromPreferences(sharedPreferences);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    private void loadColorFromPreferences(SharedPreferences sharedPreferences){
        setColor(sharedPreferences.getString(getString(R.string.pref_color_key),getString(R.string.pref_color_neutral_value)));
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.pref_color_key))){
            loadColorFromPreferences(sharedPreferences);
        }
    }

    public void setColor(String newColorKey) {
        if (newColorKey.equals(getString(R.string.pref_color_brown_value))) {
            //getApplication().setTheme(R.style.kabouterTheme);

            mCalendar.setBackgroundColor(getResources().getColor(R.color.kabouterBrown));
            mCalendar.setDrawingCacheBackgroundColor(getResources().getColor(R.color.kabouterBrown));
            mscrollview.setBackgroundColor(getResources().getColor(R.color.kabouterBrown));

        }
        else if (newColorKey.equals(getString(R.string.pref_color_lightGreen_value))) {
            //getApplication().setTheme(R.style.speelclubTheme);

            mCalendar.setBackgroundColor(getResources().getColor(R.color.speelclubGreen));
            mCalendar.setDrawingCacheBackgroundColor(getResources().getColor(R.color.speelclubGreen));
            mscrollview.setBackgroundColor(getResources().getColor(R.color.speelclubGreen));
        }
        else if (newColorKey.equals(getString(R.string.pref_color_darkGreen_value))) {
            //getApplication().setTheme(R.style.rakkerTheme);

            mCalendar.setBackgroundColor(getResources().getColor(R.color.rakkerGreen));
            mCalendar.setDrawingCacheBackgroundColor(getResources().getColor(R.color.rakkerGreen));
            mscrollview.setBackgroundColor(getResources().getColor(R.color.rakkerGreen));
        }
        else if (newColorKey.equals(getString(R.string.pref_color_red_value))) {
            //getApplication().setTheme(R.style.topperTheme);

            mCalendar.setBackgroundColor(getResources().getColor(R.color.topperRed));
            mCalendar.setDrawingCacheBackgroundColor(getResources().getColor(R.color.topperRed));
            mscrollview.setBackgroundColor(getResources().getColor(R.color.topperRed));
        }
        else if (newColorKey.equals(getString(R.string.pref_color_blue_value))) {
            //getApplication().setTheme(R.style.kerelTheme);

            mCalendar.setBackgroundColor(getResources().getColor(R.color.kerelBlue));
            mCalendar.setDrawingCacheBackgroundColor(getResources().getColor(R.color.kerelBlue));
            mscrollview.setBackgroundColor(getResources().getColor(R.color.kerelBlue));
        }
        else if (newColorKey.equals(getString(R.string.pref_color_orange_value))) {
            //getApplication().setTheme(R.style.aspisTheme);

            mCalendar.setBackgroundColor(getResources().getColor(R.color.aspiOrange));
            mCalendar.setDrawingCacheBackgroundColor(getResources().getColor(R.color.aspiOrange));
            mscrollview.setBackgroundColor(getResources().getColor(R.color.aspiOrange));
        }
        else {
            //getApplication().setTheme(R.style.AppTheme);
            //mCalendar.setBackgroundColor(getResources().getColor(R.color.n));
            //mCalendar.setDrawingCacheBackgroundColor(getResources().getColor(R.color.kabouterBrown));
            mCalendar.setBackgroundColor(getResources().getColor(R.color.white));
            mCalendar.setDrawingCacheBackgroundColor(getResources().getColor(R.color.white));
            mscrollview.setBackgroundColor(getResources().getColor(R.color.white));
        }

    }
}
