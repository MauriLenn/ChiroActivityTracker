package com.example.lennert.chiro_activitytracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.widget.RecyclerView;

import com.example.lennert.chiro_activitytracker.SQLContract.*;

/**
 * Created by Lennert on 22/04/2018.
 */

public class SQLDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ChiroActivity.db";
    private static final int DATABASE_VERSION = 1;

    public SQLDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_CHIROACTIVITY_TABLE = "CREATE TABLE " +
                ChiroActivityEntry.TABLE_NAME + " (" +
                ChiroActivityEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ChiroActivityEntry.COLUMN_NAME_Activity + " TEXT NOT NULL," +
                ChiroActivityEntry.COLUMN_DESCRIPTION_ACTIVITY + " TEXT NOT NULL," +
                ChiroActivityEntry.COLUMN_NUMBER_OF_MEMBERS + " INTEGER NOT NULL," +
                ChiroActivityEntry.COLUMN_NUMBER_OF_DRINKS + " INTEGER NOT NULL," +
                ChiroActivityEntry.COLUMN_RATING + " INTEGER NOT NULL" +
                ");";

        sqLiteDatabase.execSQL(SQL_CREATE_CHIROACTIVITY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ChiroActivityEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
