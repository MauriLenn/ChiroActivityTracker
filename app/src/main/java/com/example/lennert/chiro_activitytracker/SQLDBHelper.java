package com.example.lennert.chiro_activitytracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.lennert.chiro_activitytracker.SQLContract.*;

/**
 * Created by Lennert on 22/04/2018.
 */

public class SQLDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Chiro.db";
    private static final int DATABASE_VERSION = 1;

    public SQLDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_CHIRO_TABLE = "CREATE TABLE " +
                ChiroActivityEntry.TABLE_NAME + " (" +
                ChiroActivityEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ChiroActivityEntry.COLUMN_DATE + " TEXT NOT NULL," +
                ChiroActivityEntry.COLUMN_NAME_Activity + " TEXT NOT NULL," +
                ChiroActivityEntry.COLUMN_DESCRIPTION_ACTIVITY + " TEXT NOT NULL," +
                ChiroActivityEntry.COLUMN_NUMBER_OF_MEMBERS + " INTEGER," +
                ChiroActivityEntry.COLUMN_NUMBER_OF_DRINKS + " INTEGER," +
                ChiroActivityEntry.COLUMN_RATING + " FLOAT NOT NULL," +
                " UNIQUE (" + ChiroActivityEntry.COLUMN_DATE + ") ON CONFLICT REPLACE);";

        sqLiteDatabase.execSQL(SQL_CREATE_CHIRO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ChiroActivityEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
