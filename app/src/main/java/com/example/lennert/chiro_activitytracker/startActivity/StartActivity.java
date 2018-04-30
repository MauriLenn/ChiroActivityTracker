package com.example.lennert.chiro_activitytracker.startActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.example.lennert.chiro_activitytracker.mainActivity.MainActivity;
import com.example.lennert.chiro_activitytracker.R;
import com.example.lennert.chiro_activitytracker.programActivity.ProgramActivity;
import com.example.lennert.chiro_activitytracker.settingsActivity.SettingsActivity;

public class StartActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private Button mlistSaturdaysButton;
    private Button mchiroWebsiteButton;

    private DatePicker mCalendar;
    private ScrollView mscrollview;
    private ImageView mChiroIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mlistSaturdaysButton = findViewById(R.id.bt_listChiroSaturdays);
        mchiroWebsiteButton = findViewById(R.id.bt_chiroWebsite);

        mCalendar = findViewById(R.id.calendar);
        mscrollview = findViewById(R.id.background_startActivity);
        mChiroIcon = findViewById(R.id.ic_chiro_herk_big);

        setupSharedPreferences();
    }

    public void onClickOpenProgramActivityButton (View view) {
        Intent startProgramActivityIntent = new Intent(StartActivity.this, ProgramActivity.class);

        if (startProgramActivityIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(startProgramActivityIntent);
        }
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
            setTheme(R.style.kabouterTheme);
            setContentView(R.layout.activity_start);

        }
        else if (newColorKey.equals(getString(R.string.pref_color_lightGreen_value))) {
            setTheme(R.style.speelclubTheme);
            setContentView(R.layout.activity_start);

        }
        else if (newColorKey.equals(getString(R.string.pref_color_darkGreen_value))) {
            setTheme(R.style.rakkerTheme);
            setContentView(R.layout.activity_start);

        }
        else if (newColorKey.equals(getString(R.string.pref_color_red_value))) {
            setTheme(R.style.topperTheme);
            setContentView(R.layout.activity_start);
        }
        else if (newColorKey.equals(getString(R.string.pref_color_blue_value))) {
            setTheme(R.style.kerelTheme);
            setContentView(R.layout.activity_start);
        }
        else if (newColorKey.equals(getString(R.string.pref_color_orange_value))) {
            setTheme(R.style.aspisTheme);
            setContentView(R.layout.activity_start);
        }
        else {
            setTheme(R.style.AppTheme);
            setContentView(R.layout.activity_start);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    }
}
