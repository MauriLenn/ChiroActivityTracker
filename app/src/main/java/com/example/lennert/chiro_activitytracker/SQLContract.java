package com.example.lennert.chiro_activitytracker;

import android.provider.BaseColumns;

/**
 * Created by Lennert on 22/04/2018.
 */

public class SQLContract {

    public static final class ChiroActivityEntry implements BaseColumns{
        public static final String TABLE_NAME = "Chiro";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_NAME_Activity = "name_activity";
        public static final String COLUMN_DESCRIPTION_ACTIVITY = "description_activity";
        public static final String COLUMN_NUMBER_OF_MEMBERS = "number_of_members";
        public static final String COLUMN_NUMBER_OF_DRINKS = "number_of_drinks";
        public static final String COLUMN_RATING = "rating";

    }
}
