package com.example.lennert.chiro_activitytracker.mainActivity;

/**
 * Created by Lennert on 26/03/2018.
 */

public class RecyclerItem {

    private String saturdayDate;
    private String weather;
    private String temperature;

    public RecyclerItem(String saturdayDate, String weather,String temperature) {
        this.saturdayDate = saturdayDate;
        this.weather = weather;
        this.temperature = temperature;
    }

    public String getSaturdayDate() {
        return saturdayDate;
    }

    public String getWeather() {
        return weather;
    }

    public String getTemperature() {
        return temperature;
    }
}
