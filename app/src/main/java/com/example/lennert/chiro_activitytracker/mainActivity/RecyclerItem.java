package com.example.lennert.chiro_activitytracker.mainActivity;

/**
 * Created by Lennert on 26/03/2018.
 */

public class RecyclerItem {

    private String saturdayDate;
    private String weather;

    public RecyclerItem(String saturdayDate, String weather) {
        this.saturdayDate = saturdayDate;
        this.weather = weather;
    }

    public String getSaturdayDate() {
        return saturdayDate;
    }

    public String getWeather() {
        return weather;
    }
}
