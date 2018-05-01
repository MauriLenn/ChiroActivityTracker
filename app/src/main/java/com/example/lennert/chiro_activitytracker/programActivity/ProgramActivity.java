package com.example.lennert.chiro_activitytracker.programActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lennert.chiro_activitytracker.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProgramActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    //LOGGING
    private static final String LOG_TAG = ProgramActivity.class.getSimpleName();
    private static final String DATA = "data loaded";
    private static final String JSONE = "JSON parsing exception: ";
    private static final String BACK = "go to Startactivity";
    //

    private static final String URL_DATA = "https://api.myjson.com/bins/1a3dnb";

    public AdapterRecycler2 mAdapter;
    private RecyclerView mRecyclerView;
    private List<RecyclerItem2> mRecyclerItem2s;
    private TextView mSaturdayDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);

        mRecyclerView = findViewById(R.id.rec_programs);
        mSaturdayDate = findViewById(R.id.tv_saturdayDate);

        //MENU
        ActionBar actionBar = this.getSupportActionBar();

        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //RECYCLERVIEW
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerItem2s = new ArrayList<>();

        loadData();

        //PREFERENCES
        setupSharedPreferences();
    }


    //MENU
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemThatWasSelected = item.getItemId();
        if (menuItemThatWasSelected == R.id.home) {
            infoLogAndAppend(BACK);
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }


    //RECYCLERVIEW
    private void loadData() {
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
                    String date = jsonObject.getString("date");
                    mSaturdayDate.setText(date);
                    JSONArray JA = jsonObject.getJSONArray("programs");

                    for (int i= 0; i<JA.length();i++){
                        JSONObject JO = JA.getJSONObject(i);
                        String branch = JO.getString("branch");
                        String program = JO.getString("program");
                        RecyclerItem2 recyclerItem = new RecyclerItem2(branch,program);
                        mRecyclerItem2s.add(recyclerItem);
                    }


                    mAdapter = new AdapterRecycler2(mRecyclerItem2s, getApplicationContext());
                    mRecyclerView.setAdapter(mAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                    errorLogAndAppend(JSONE + e.getMessage());
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
        infoLogAndAppend(DATA);
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
            mSaturdayDate.setBackgroundColor(getResources().getColor(R.color.kabouterBrown));
        }
        else if (newColorKey.equals(getString(R.string.pref_color_lightGreen_value))) {
            mSaturdayDate.setBackgroundColor(getResources().getColor(R.color.speelclubGreen));
        }
        else if (newColorKey.equals(getString(R.string.pref_color_darkGreen_value))) {
            mSaturdayDate.setBackgroundColor(getResources().getColor(R.color.rakkerGreen));
        }
        else if (newColorKey.equals(getString(R.string.pref_color_red_value))) {
            mSaturdayDate.setBackgroundColor(getResources().getColor(R.color.topperRed));
        }
        else if (newColorKey.equals(getString(R.string.pref_color_blue_value))) {
            mSaturdayDate.setBackgroundColor(getResources().getColor(R.color.kerelBlue));
        }
        else if (newColorKey.equals(getString(R.string.pref_color_orange_value))) {
            mSaturdayDate.setBackgroundColor(getResources().getColor(R.color.aspiOrange));
        }
        else {
            mSaturdayDate.setBackgroundColor(getResources().getColor(R.color.white));
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    }

    //LOGGING
    private void infoLogAndAppend(String Event) {
        Log.i(LOG_TAG, "Event: " + Event);
    }

    private void errorLogAndAppend(String Event) {
        Log.e(LOG_TAG, "Event: " + Event);
    }
}
