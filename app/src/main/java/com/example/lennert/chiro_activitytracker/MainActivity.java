package com.example.lennert.chiro_activitytracker;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterRecycler.ListItemClickListener {


    //public static TextView mSaturday;

    private static final String URL_DATA = "https://api.myjson.com/bins/rxshb";

    //LES 3
    public AdapterRecycler mAdapter;
    private RecyclerView mRecyclerView;
    private List<RecyclerItem> mRecyclerItems;

    private Toast mToast;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mSaturday = findViewById(R.id.saturday_item);

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
        Intent intent_detailActivity = new Intent(MainActivity.this, DetailActivity.class);
        //startActivity(intent_detailActivity);

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


        if (menuItemThatWasSelected == R.id.action_settings) {
            Intent startSettingsActivityIntent = new Intent(MainActivity.this,SettingsActivity.class);
            startActivity(startSettingsActivityIntent);
            return true;
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
                        String singleParsed;
                        singleParsed = JO.getString("day") + " " + JO.getString("month") + " " + JO.getString("year");//een single json object van de array
                        RecyclerItem recyclerItem = new RecyclerItem(singleParsed);
                        mRecyclerItems.add(recyclerItem);
                    }


                    mAdapter = new AdapterRecycler(mRecyclerItems, getApplicationContext(),MainActivity.this);
                    mRecyclerView.setAdapter(mAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
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
}
