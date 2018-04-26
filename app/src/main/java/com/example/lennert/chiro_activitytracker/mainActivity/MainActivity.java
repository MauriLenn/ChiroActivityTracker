package com.example.lennert.chiro_activitytracker.mainActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lennert.chiro_activitytracker.detailActivity.DetailActivity;
import com.example.lennert.chiro_activitytracker.R;
import com.example.lennert.chiro_activitytracker.settingsActivity.SettingsActivity;
import com.example.lennert.chiro_activitytracker.startActivity.StartActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterRecycler.ListItemClickListener, SharedPreferences.OnSharedPreferenceChangeListener {

    //LOGGING
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final String DATA = "loading data";

    //LES 2
    private static final String URL_DATA = "https://api.myjson.com/bins/12ud1j";

    //LES 3
    public AdapterRecycler mAdapter;
    private RecyclerView mRecyclerView;
    private List<RecyclerItem> mRecyclerItems;

    private Toast mToast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //LES 2
        //fetchDataFromInternet process = new fetchDataFromInternet();
        //process.execute();

        loadRecyclerViewData();

        //LES 3
        mRecyclerView = findViewById(R.id.rec_saturdays);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);

        mRecyclerItems = new ArrayList<>();

        //LES 4

        setupSharedPreferences();
    }


    //LES 1
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Use AppCompatActivity's method getMenuInflater to get a handle on the menu inflater */
        MenuInflater inflater = getMenuInflater();
        /* Use the inflater's inflate method to inflate our menu layout to this menu */
        inflater.inflate(R.menu.main, menu);
        /* Return true so that the menu is displayed in the Toolbar */
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemThatWasSelected = item.getItemId();


        if (menuItemThatWasSelected == R.id.action_settingsActivity) {
            Intent startSettingsActivityIntent = new Intent(MainActivity.this,SettingsActivity.class);
            startActivity(startSettingsActivityIntent);
            return true;
        }

        if (menuItemThatWasSelected == R.id.action_startActivity) {
            Intent startStartActivityIntent = new Intent(MainActivity.this,StartActivity.class);
            startActivity(startStartActivityIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    //LES 2

    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String data) {
                progressDialog.dismiss();
                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject(data);
                    JSONArray JA = jsonObject.getJSONArray("saturdays");

                    for (int i= 0; i<JA.length();i++){
                        JSONObject JO = JA.getJSONObject(i);
                        String date= JO.getString("day") + " " + JO.getString("month") + " " + JO.getString("year");//een single json object van de array
                        String weather = JO.getString("weather");
                        RecyclerItem recyclerItem = new RecyclerItem(date,weather);
                        mRecyclerItems.add(recyclerItem);
                    }


                    mAdapter = new AdapterRecycler(mRecyclerItems, getApplicationContext(),MainActivity.this);
                    mRecyclerView.setAdapter(mAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();

                    //LOGGING

                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        logAndAppend(DATA);
    }

    //LES 3
    @Override
    public void onListItemClick(int clickedItemIndex) {

        RecyclerItem selectedSaturday = mRecyclerItems.get(clickedItemIndex);
        String saturdayDate = selectedSaturday.getSaturdayDate();

        Intent startDetailActivityIntent = new Intent(MainActivity.this, DetailActivity.class);

        startDetailActivityIntent.putExtra(Intent.EXTRA_TEXT, saturdayDate);

        startActivity(startDetailActivityIntent);
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
           mRecyclerView.setBackgroundColor(getResources().getColor(R.color.kabouterBrown));
        }
        else if (newColorKey.equals(getString(R.string.pref_color_lightGreen_value))) {
            mRecyclerView.setBackgroundColor(getResources().getColor(R.color.speelclubGreen));
        }
        else if (newColorKey.equals(getString(R.string.pref_color_darkGreen_value))) {
            mRecyclerView.setBackgroundColor(getResources().getColor(R.color.rakkerGreen));
        }
        else if (newColorKey.equals(getString(R.string.pref_color_red_value))) {
            mRecyclerView.setBackgroundColor(getResources().getColor(R.color.topperRed));
        }
        else if (newColorKey.equals(getString(R.string.pref_color_blue_value))) {
            mRecyclerView.setBackgroundColor(getResources().getColor(R.color.kerelBlue));
        }
        else if (newColorKey.equals(getString(R.string.pref_color_orange_value))) {
            mRecyclerView.setBackgroundColor(getResources().getColor(R.color.aspiOrange));
        }
        else {
            mRecyclerView.setBackgroundColor(getResources().getColor(R.color.white));
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    }

    //logging

    private void logAndAppend(String Event) {
        Log.d(LOG_TAG, "Event: " + Event);
    }
}

