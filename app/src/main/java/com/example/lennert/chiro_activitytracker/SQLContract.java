package com.example.lennert.chiro_activitytracker;

import android.provider.BaseColumns;

/**
 * Created by Lennert on 22/04/2018.
 */

public class SQLContract {

    public static final class ChiroActivityEntry implements BaseColumns{
        public static final String TABLE_NAME = "ChiroActivity";
        public static final String COLUMN_NAME_Activity = "name activity";
        public static final String COLUMN_DESCRIPTION_ACTIVITY = "description activity";
        public static final String COLUMN_NUMBER_OF_MEMBERS = "number of members";
        public static final String COLUMN_NUMBER_OF_DRINKS = "number of drinks";
        public static final String COLUMN_RATING = "rating";

    }
}
