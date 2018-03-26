package com.example.lennert.chiro_activitytracker;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Lennert on 8/03/2018.
 */
/*
public class fetchDataFromInternet extends AsyncTask<String, Void, String[]> {
    private String data = "";
    private String[] dataParsed;
    private String singleParsed = "";

    @Override
    protected String[] doInBackground(String... params) { //background thread
        try {
            URL url = new URL("https://api.myjson.com/bins/x7art"); //data fetched from this data
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(); //er wordt een connectie gemaakt
            InputStream inputStream = httpURLConnection.getInputStream(); //de datastream
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream)); // de data wordt gelezen

            String line = "";
            while (line != null){
                line = bufferedReader.readLine();
                data = data + line;

            }

            JSONArray JA = new JSONArray(data);
            for (int i= 0; i<JA.length();i++){
                JSONObject JO = (JSONObject) JA.get(i);
                singleParsed = JO.get("day") + " " + JO.get("month") + " " + JO.get("year") + "\n";//een single json object van de array

                dataParsed[i] = singleParsed;
                //dataParsed = dataParsed + singleParsed;
            }

            return dataParsed;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String[] saturdays) { //Ui thread
        super.onPostExecute(saturdays);

        MainActivity.mAdapter.setmSaturdays(dataParsed);

        //MainActivity.mSaturday.setText(this.dataParsed);

    }
} */
