package com.example.lennert.chiro_activitytracker;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    private Button listSaturdaysButton;
    private Button chiroWebsiteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        listSaturdaysButton = findViewById(R.id.bt_listChiroSaturdays);
        chiroWebsiteButton = findViewById(R.id.bt_chiroWebsite);


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
}
