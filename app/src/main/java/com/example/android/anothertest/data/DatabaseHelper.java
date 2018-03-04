package com.example.android.anothertest.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.android.anothertest.data.DatabaseContract.AscentEntry;
import com.example.android.anothertest.data.DatabaseContract.CalendarTrackerEntry;
import com.example.android.anothertest.data.DatabaseContract.ClimbLogEntry;
import com.example.android.anothertest.data.DatabaseContract.GradeListEntry;
import com.example.android.anothertest.data.DatabaseContract.GradeTypeEntry;
import com.example.android.anothertest.data.DatabaseContract.WorkoutListEntry;
import com.example.android.anothertest.data.DatabaseContract.WorkoutLogEntry;
import com.example.android.anothertest.data.DatabaseContract.WorkoutTypeEntry;

import java.util.Calendar;

/**
 * Created by Bobek on 22/01/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // public static final String LOG_TAG = DatabaseHelper.class.getSimpleName();
    public static final String LOG_TAG = "DatabaseHelper";

    /**
     * Name of the database file
     */
    private static final String DATABASE_NAME = "database.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link DatabaseHelper}.
     *
     * @param context of the app
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create a String that contains the SQL statement to create the ascent types table
        String SQL_CREATE_ASCENTTYPE_TABLE = "CREATE TABLE " + AscentEntry.TABLE_NAME + " ("
                + AscentEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, "
                + AscentEntry.COLUMN_ASCENTTYPENAME + " TEXT, "
                + AscentEntry.COLUMN_DESCRIPTION + " TEXT);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_ASCENTTYPE_TABLE);

        // Create a String that contains the SQL statement to create the calendar tracker table
        String SQL_CREATE_CALENDERTRACKER_TABLE = "CREATE TABLE " + CalendarTrackerEntry.TABLE_NAME + " ("
                + CalendarTrackerEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, "
                + CalendarTrackerEntry.COLUMN_DATE + " INTEGER NOT NULL UNIQUE, "
                + CalendarTrackerEntry.COLUMN_WORKOUTCOUNT + " INTEGER, "
                + CalendarTrackerEntry.COLUMN_CLIMBCOUNT + " INTEGER);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_CALENDERTRACKER_TABLE);

        // Create a String that contains the SQL statement to create the climb log table
        String SQL_CREATE_CLIMBLOG_TABLE = "CREATE TABLE " + ClimbLogEntry.TABLE_NAME + " ("
                + ClimbLogEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, "
                + ClimbLogEntry.COLUMN_DATE + " INTEGER NOT NULL, "
                + ClimbLogEntry.COLUMN_NAME + " TEXT, "
                + ClimbLogEntry.COLUMN_GRADETYPECODE + " INTEGER, "
                + ClimbLogEntry.COLUMN_GRADECODE + " INTEGER, "
                + ClimbLogEntry.COLUMN_ASCENTTYPECODE + " INTEGER, "
                + ClimbLogEntry.COLUMN_LOCATION + " TEXT, "
                + ClimbLogEntry.COLUMN_FIRSTASCENTCODE + " INTEGER, "
                + ClimbLogEntry.COLUMN_LOGTAG + " INTEGER);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_CLIMBLOG_TABLE);

        // Create a String that contains the SQL statement to create the climb log table
        String SQL_CREATE_GRADELIST_TABLE = "CREATE TABLE " + GradeListEntry.TABLE_NAME + " ("
                + GradeListEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, "
                + GradeListEntry.COLUMN_GRADETYPECODE + " INTEGER NOT NULL, "
                + GradeListEntry.COLUMN_GRADENAME + " TEXT, "
                + GradeListEntry.COLUMN_RELATIVEDIFFICULTY + " REAL);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_GRADELIST_TABLE);

        // Create a String that contains the SQL statement to create the climb log table
        String SQL_CREATE_GRADETYPE_TABLE = "CREATE TABLE " + GradeTypeEntry.TABLE_NAME + " ("
                + GradeTypeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, "
                + GradeTypeEntry.COLUMN_GRADETYPENAME + " TEXT);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_GRADETYPE_TABLE);

        // Create a String that contains the SQL statement to create the climb log table
        String SQL_CREATE_WORKOUTLOG_TABLE = "CREATE TABLE " + WorkoutLogEntry.TABLE_NAME + " ("
                + WorkoutLogEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, "
                + WorkoutLogEntry.COLUMN_DATE + " INTEGER, "
                + WorkoutLogEntry.COLUMN_WORKOUTTYPECODE + " INTEGER, "
                + WorkoutLogEntry.COLUMN_WORKOUTCODE + " INTEGER, "
                + WorkoutLogEntry.COLUMN_ISCLIMB + " INTEGER, "
                + WorkoutLogEntry.COLUMN_WEIGHT + " LONG, "
                + WorkoutLogEntry.COLUMN_SETCOUNT + " INTEGER, "
                + WorkoutLogEntry.COLUMN_REPCOUNTPERSET + " INTEGER, "
                + WorkoutLogEntry.COLUMN_REPDURATIONPERSET + " LONG, "
                + WorkoutLogEntry.COLUMN_RESTDURATIONPERSET + " LONG, "
                + WorkoutLogEntry.COLUMN_GRADETYPECODE + " INTEGER, "
                + WorkoutLogEntry.COLUMN_GRADECODE + " INTEGER);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_WORKOUTLOG_TABLE);

        // Create a String that contains the SQL statement to create the climb log table
        String SQL_CREATE_WORKOUTLIST_TABLE = "CREATE TABLE " + WorkoutListEntry.TABLE_NAME + " ("
                + WorkoutListEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, "
                + WorkoutListEntry.COLUMN_NAME + " TEXT, "
                + WorkoutListEntry.COLUMN_WORKOUTTYPECODE + " INTEGER, "
                + WorkoutListEntry.COLUMN_DESCRIPTION + " TEXT, "
                + WorkoutListEntry.COLUMN_ISCLIMB + " INTEGER, "
                + WorkoutListEntry.COLUMN_ISWEIGHT + " INTEGER, "
                + WorkoutListEntry.COLUMN_ISSETCOUNT + " INTEGER, "
                + WorkoutListEntry.COLUMN_ISREPCOUNTPERSET + " INTEGER, "
                + WorkoutListEntry.COLUMN_ISREPDURATIONPERSET + " INTEGER, "
                + WorkoutListEntry.COLUMN_ISRESTDURATIONPERSET + " INTEGER, "
                + WorkoutListEntry.COLUMN_ISGRADECODE + " INTEGER);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_WORKOUTLIST_TABLE);

        // Create a String that contains the SQL statement to create the climb log table
        String SQL_CREATE_WORKOUTTYPE_TABLE = "CREATE TABLE " + WorkoutTypeEntry.TABLE_NAME + " ("
                + WorkoutTypeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, "
                + WorkoutTypeEntry.COLUMN_WORKOUTTYPENAME + " TEXT, "
                + WorkoutTypeEntry.COLUMN_DESCRIPTION + " TEXT);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_WORKOUTTYPE_TABLE);

        Log.v(LOG_TAG, "Database Creation Method Run");

        insertInitialGradeListData(db);

        insertInitialGradeTypeData(db);

        insertInitialAscentTypeData(db);

        insertInitialWorkoutTypeData(db);

        insertInitialWorkoutListData(db);

        Log.v(LOG_TAG, "Database Initial Fill Method Run");

        insertDummyClimbs(db);

        Log.v(LOG_TAG, "Database Dummy Fill Method Run");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertInitialGradeListData(SQLiteDatabase db) {

        ContentValues values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 1);
        values.put(GradeListEntry.COLUMN_GRADENAME, "3");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 1);
        db.insert(GradeListEntry.TABLE_NAME, null, values);
        values.clear();

        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 1);
        values.put(GradeListEntry.COLUMN_GRADENAME, "3+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 2);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 1);
        values.put(GradeListEntry.COLUMN_GRADENAME, "4");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 3);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 1);
        values.put(GradeListEntry.COLUMN_GRADENAME, "4+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 4);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 1);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 1);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 6);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 1);
        values.put(GradeListEntry.COLUMN_GRADENAME, "6a");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 7);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 1);
        values.put(GradeListEntry.COLUMN_GRADENAME, "6a+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 8);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 1);
        values.put(GradeListEntry.COLUMN_GRADENAME, "6b");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 9);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 1);
        values.put(GradeListEntry.COLUMN_GRADENAME, "6b+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 10);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 1);
        values.put(GradeListEntry.COLUMN_GRADENAME, "6c");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 11);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 1);
        values.put(GradeListEntry.COLUMN_GRADENAME, "6c+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 12);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 1);
        values.put(GradeListEntry.COLUMN_GRADENAME, "7a");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 13);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 1);
        values.put(GradeListEntry.COLUMN_GRADENAME, "7a+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 14);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 1);
        values.put(GradeListEntry.COLUMN_GRADENAME, "7b");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 15);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 1);
        values.put(GradeListEntry.COLUMN_GRADENAME, "7b+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 16);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 1);
        values.put(GradeListEntry.COLUMN_GRADENAME, "7c");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 17);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 1);
        values.put(GradeListEntry.COLUMN_GRADENAME, "7c+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 18);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 1);
        values.put(GradeListEntry.COLUMN_GRADENAME, "8a");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 19);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 1);
        values.put(GradeListEntry.COLUMN_GRADENAME, "8a+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 20);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 1);
        values.put(GradeListEntry.COLUMN_GRADENAME, "8b");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 21);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 1);
        values.put(GradeListEntry.COLUMN_GRADENAME, "8b+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 22);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 1);
        values.put(GradeListEntry.COLUMN_GRADENAME, "8c");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 23);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 1);
        values.put(GradeListEntry.COLUMN_GRADENAME, "8c+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 24);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 1);
        values.put(GradeListEntry.COLUMN_GRADENAME, "9a");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 25);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 1);
        values.put(GradeListEntry.COLUMN_GRADENAME, "9a+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 26);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 1);
        values.put(GradeListEntry.COLUMN_GRADENAME, "9b");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 27);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 1);
        values.put(GradeListEntry.COLUMN_GRADENAME, "9b+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 28);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 1);
        values.put(GradeListEntry.COLUMN_GRADENAME, "9c");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 29);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 1);
        values.put(GradeListEntry.COLUMN_GRADENAME, "9c+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 29);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 2);
        values.put(GradeListEntry.COLUMN_GRADENAME, "4a");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 1);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 2);
        values.put(GradeListEntry.COLUMN_GRADENAME, "4b");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 2);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 2);
        values.put(GradeListEntry.COLUMN_GRADENAME, "4c");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 3);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 2);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5a");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 4);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 2);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5b");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 2);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5c");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 6);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 2);
        values.put(GradeListEntry.COLUMN_GRADENAME, "6a");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 8);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 2);
        values.put(GradeListEntry.COLUMN_GRADENAME, "6b");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 11);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 2);
        values.put(GradeListEntry.COLUMN_GRADENAME, "6c");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 16);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 2);
        values.put(GradeListEntry.COLUMN_GRADENAME, "7a");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 19);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 2);
        values.put(GradeListEntry.COLUMN_GRADENAME, "7b");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 22);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 2);
        values.put(GradeListEntry.COLUMN_GRADENAME, "7c");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 25);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 2);
        values.put(GradeListEntry.COLUMN_GRADENAME, "8a");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 28);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 2);
        values.put(GradeListEntry.COLUMN_GRADENAME, "8b");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 31);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 2);
        values.put(GradeListEntry.COLUMN_GRADENAME, "8c");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 34);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 3);
        values.put(GradeListEntry.COLUMN_GRADENAME, "VB");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 1);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 3);
        values.put(GradeListEntry.COLUMN_GRADENAME, "V0-");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 2);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 3);
        values.put(GradeListEntry.COLUMN_GRADENAME, "V0");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 3);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 3);
        values.put(GradeListEntry.COLUMN_GRADENAME, "V0+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 4);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 3);
        values.put(GradeListEntry.COLUMN_GRADENAME, "V1");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 3);
        values.put(GradeListEntry.COLUMN_GRADENAME, "V2");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 6);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 3);
        values.put(GradeListEntry.COLUMN_GRADENAME, "V3");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 7.5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 3);
        values.put(GradeListEntry.COLUMN_GRADENAME, "V4");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 9.5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 3);
        values.put(GradeListEntry.COLUMN_GRADENAME, "V5");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 11.5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 3);
        values.put(GradeListEntry.COLUMN_GRADENAME, "V6");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 13);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 3);
        values.put(GradeListEntry.COLUMN_GRADENAME, "V7");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 14);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 3);
        values.put(GradeListEntry.COLUMN_GRADENAME, "V8");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 15.5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 3);
        values.put(GradeListEntry.COLUMN_GRADENAME, "V9");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 17);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 3);
        values.put(GradeListEntry.COLUMN_GRADENAME, "V10");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 18);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 3);
        values.put(GradeListEntry.COLUMN_GRADENAME, "V11");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 19);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 3);
        values.put(GradeListEntry.COLUMN_GRADENAME, "V12");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 20);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 3);
        values.put(GradeListEntry.COLUMN_GRADENAME, "V13");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 21);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 3);
        values.put(GradeListEntry.COLUMN_GRADENAME, "V14");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 22);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 3);
        values.put(GradeListEntry.COLUMN_GRADENAME, "V15");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 23);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 3);
        values.put(GradeListEntry.COLUMN_GRADENAME, "V16");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 24);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 3);
        values.put(GradeListEntry.COLUMN_GRADENAME, "V17");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 25);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 3);
        values.put(GradeListEntry.COLUMN_GRADENAME, "V18");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 26);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 3);
        values.put(GradeListEntry.COLUMN_GRADENAME, "V19");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 27);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 3);
        values.put(GradeListEntry.COLUMN_GRADENAME, "V20");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 28);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 3);
        values.put(GradeListEntry.COLUMN_GRADENAME, "V21");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 29);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 4);
        values.put(GradeListEntry.COLUMN_GRADENAME, "1");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 1);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 4);
        values.put(GradeListEntry.COLUMN_GRADENAME, "2");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 2);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 4);
        values.put(GradeListEntry.COLUMN_GRADENAME, "2+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 3);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 4);
        values.put(GradeListEntry.COLUMN_GRADENAME, "3-");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 4);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 4);
        values.put(GradeListEntry.COLUMN_GRADENAME, "3");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 4);
        values.put(GradeListEntry.COLUMN_GRADENAME, "3+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 6);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 4);
        values.put(GradeListEntry.COLUMN_GRADENAME, "4a");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 7);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 4);
        values.put(GradeListEntry.COLUMN_GRADENAME, "4b");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 8);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 4);
        values.put(GradeListEntry.COLUMN_GRADENAME, "4c");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 9);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 4);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5a");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 10);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 4);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5b");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 11);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 4);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5c");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 12);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 4);
        values.put(GradeListEntry.COLUMN_GRADENAME, "6a");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 13);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 4);
        values.put(GradeListEntry.COLUMN_GRADENAME, "6a+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 14);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 4);
        values.put(GradeListEntry.COLUMN_GRADENAME, "6b");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 15);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 4);
        values.put(GradeListEntry.COLUMN_GRADENAME, "6b+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 16);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 4);
        values.put(GradeListEntry.COLUMN_GRADENAME, "6c");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 17);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 4);
        values.put(GradeListEntry.COLUMN_GRADENAME, "6c+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 18);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 4);
        values.put(GradeListEntry.COLUMN_GRADENAME, "7a");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 19);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 4);
        values.put(GradeListEntry.COLUMN_GRADENAME, "7a+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 20);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 4);
        values.put(GradeListEntry.COLUMN_GRADENAME, "7b");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 21);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 4);
        values.put(GradeListEntry.COLUMN_GRADENAME, "7b+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 22);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 4);
        values.put(GradeListEntry.COLUMN_GRADENAME, "7c");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 23);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 4);
        values.put(GradeListEntry.COLUMN_GRADENAME, "7c+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 24);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 4);
        values.put(GradeListEntry.COLUMN_GRADENAME, "8a");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 25);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 4);
        values.put(GradeListEntry.COLUMN_GRADENAME, "8a+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 26);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 4);
        values.put(GradeListEntry.COLUMN_GRADENAME, "8b");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 27);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 4);
        values.put(GradeListEntry.COLUMN_GRADENAME, "8b+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 28);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 4);
        values.put(GradeListEntry.COLUMN_GRADENAME, "8c");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 29);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 4);
        values.put(GradeListEntry.COLUMN_GRADENAME, "8c+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 30);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 4);
        values.put(GradeListEntry.COLUMN_GRADENAME, "9a");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 31);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 4);
        values.put(GradeListEntry.COLUMN_GRADENAME, "9a+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 32);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 4);
        values.put(GradeListEntry.COLUMN_GRADENAME, "9b");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 33);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 4);
        values.put(GradeListEntry.COLUMN_GRADENAME, "9b+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 34);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 4);
        values.put(GradeListEntry.COLUMN_GRADENAME, "9c");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 35);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 4);
        values.put(GradeListEntry.COLUMN_GRADENAME, "9c+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 36);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 5);
        values.put(GradeListEntry.COLUMN_GRADENAME, "Mod");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 1.5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 5);
        values.put(GradeListEntry.COLUMN_GRADENAME, "Diff");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 2.5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 5);
        values.put(GradeListEntry.COLUMN_GRADENAME, "Vdiff");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 3.5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 5);
        values.put(GradeListEntry.COLUMN_GRADENAME, "HVD");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 5);
        values.put(GradeListEntry.COLUMN_GRADENAME, "Sev");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 6.25);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 5);
        values.put(GradeListEntry.COLUMN_GRADENAME, "HS");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 7.5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 5);
        values.put(GradeListEntry.COLUMN_GRADENAME, "VS");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 8);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 5);
        values.put(GradeListEntry.COLUMN_GRADENAME, "HVS");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 10.5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 5);
        values.put(GradeListEntry.COLUMN_GRADENAME, "E1");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 12);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 5);
        values.put(GradeListEntry.COLUMN_GRADENAME, "E2");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 13.5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 5);
        values.put(GradeListEntry.COLUMN_GRADENAME, "E3");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 15);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 5);
        values.put(GradeListEntry.COLUMN_GRADENAME, "E4");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 17);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 5);
        values.put(GradeListEntry.COLUMN_GRADENAME, "E5");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 19);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 5);
        values.put(GradeListEntry.COLUMN_GRADENAME, "E6");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 21.5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 5);
        values.put(GradeListEntry.COLUMN_GRADENAME, "E7");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 23.5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 5);
        values.put(GradeListEntry.COLUMN_GRADENAME, "E8");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 25.5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 5);
        values.put(GradeListEntry.COLUMN_GRADENAME, "E9");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 27);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 5);
        values.put(GradeListEntry.COLUMN_GRADENAME, "E10");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 29);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 5);
        values.put(GradeListEntry.COLUMN_GRADENAME, "E11");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 32.5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 5);
        values.put(GradeListEntry.COLUMN_GRADENAME, "E12");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 34.5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 5);
        values.put(GradeListEntry.COLUMN_GRADENAME, "E13");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 36);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 6);
        values.put(GradeListEntry.COLUMN_GRADENAME, "I");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 1);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 6);
        values.put(GradeListEntry.COLUMN_GRADENAME, "II");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 2);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 6);
        values.put(GradeListEntry.COLUMN_GRADENAME, "III");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 3);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 6);
        values.put(GradeListEntry.COLUMN_GRADENAME, "III+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 4);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 6);
        values.put(GradeListEntry.COLUMN_GRADENAME, "IV-");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 6);
        values.put(GradeListEntry.COLUMN_GRADENAME, "IV-");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 6);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 6);
        values.put(GradeListEntry.COLUMN_GRADENAME, "IV+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 7);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 6);
        values.put(GradeListEntry.COLUMN_GRADENAME, "V-");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 7.8);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 6);
        values.put(GradeListEntry.COLUMN_GRADENAME, "V");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 8.75);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 6);
        values.put(GradeListEntry.COLUMN_GRADENAME, "V+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 9.75);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 6);
        values.put(GradeListEntry.COLUMN_GRADENAME, "VI-");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 10.5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 6);
        values.put(GradeListEntry.COLUMN_GRADENAME, "VI");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 11.5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 6);
        values.put(GradeListEntry.COLUMN_GRADENAME, "VI+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 13);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 6);
        values.put(GradeListEntry.COLUMN_GRADENAME, "VII-");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 14);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 6);
        values.put(GradeListEntry.COLUMN_GRADENAME, "VII");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 15.5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 6);
        values.put(GradeListEntry.COLUMN_GRADENAME, "VII+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 16.5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 6);
        values.put(GradeListEntry.COLUMN_GRADENAME, "VIII-");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 18);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 6);
        values.put(GradeListEntry.COLUMN_GRADENAME, "VIII");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 19);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 6);
        values.put(GradeListEntry.COLUMN_GRADENAME, "VIII+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 20.5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 6);
        values.put(GradeListEntry.COLUMN_GRADENAME, "IX-");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 21.5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 6);
        values.put(GradeListEntry.COLUMN_GRADENAME, "IX");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 23);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 6);
        values.put(GradeListEntry.COLUMN_GRADENAME, "IX+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 24);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 6);
        values.put(GradeListEntry.COLUMN_GRADENAME, "X-");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 25.5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 6);
        values.put(GradeListEntry.COLUMN_GRADENAME, "X");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 26.5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 6);
        values.put(GradeListEntry.COLUMN_GRADENAME, "X+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 28);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 6);
        values.put(GradeListEntry.COLUMN_GRADENAME, "XI-");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 29);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 6);
        values.put(GradeListEntry.COLUMN_GRADENAME, "XI");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 30.25);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 6);
        values.put(GradeListEntry.COLUMN_GRADENAME, "XI+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 31.5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 6);
        values.put(GradeListEntry.COLUMN_GRADENAME, "XII-");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 32.75);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 6);
        values.put(GradeListEntry.COLUMN_GRADENAME, "XII");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 34);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 6);
        values.put(GradeListEntry.COLUMN_GRADENAME, "XII+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 35.25);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 7);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5.1");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 1);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 7);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5.2");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 2);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 7);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5.3");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 3.5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 7);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5.4");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 4.5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 7);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5.5");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 6);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 7);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5.6");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 7);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 7);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5.7");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 8.5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 7);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5.8");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 9.5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 7);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5.9");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 10.5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 7);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5.10a");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 11.5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 7);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5.10b");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 13);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 7);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5.10c");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 14);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 7);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5.10d");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 15);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 7);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5.11a");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 16);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 7);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5.11b");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 17);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 7);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5.11c");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 18);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 7);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5.11d");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 19);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 7);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5.12a");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 20);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 7);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5.12b");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 21);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 7);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5.12c");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 22);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 7);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5.12d");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 23);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 7);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5.13a");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 24);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 7);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5.13b");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 25);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 7);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5.13c");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 26);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 7);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5.13d");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 27);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 7);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5.14a");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 28);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 7);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5.14b");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 29);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 7);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5.14c");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 30);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 7);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5.14d");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 31);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 7);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5.15a");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 32);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 7);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5.15b");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 33);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 7);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5.15c");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 34);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 7);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5.15d");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 35);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 7);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5.16a");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 36);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 7);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5.16b");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 37);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 7);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5.16c");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 38);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 7);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5.16d");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 39);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 8);
        values.put(GradeListEntry.COLUMN_GRADENAME, "3");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 4);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 8);
        values.put(GradeListEntry.COLUMN_GRADENAME, "4");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 5.25);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 8);
        values.put(GradeListEntry.COLUMN_GRADENAME, "4+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 6.5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 8);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5-");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 8);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 8);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 9);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 8);
        values.put(GradeListEntry.COLUMN_GRADENAME, "5+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 10.5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 8);
        values.put(GradeListEntry.COLUMN_GRADENAME, "6-");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 12);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 8);
        values.put(GradeListEntry.COLUMN_GRADENAME, "6");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 13);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 8);
        values.put(GradeListEntry.COLUMN_GRADENAME, "6+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 14.5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 8);
        values.put(GradeListEntry.COLUMN_GRADENAME, "7-");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 16);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 8);
        values.put(GradeListEntry.COLUMN_GRADENAME, "7");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 17.75);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 8);
        values.put(GradeListEntry.COLUMN_GRADENAME, "7+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 19.5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 8);
        values.put(GradeListEntry.COLUMN_GRADENAME, "8-");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 21);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 8);
        values.put(GradeListEntry.COLUMN_GRADENAME, "8");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 22.5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 8);
        values.put(GradeListEntry.COLUMN_GRADENAME, "8+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 24.5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 8);
        values.put(GradeListEntry.COLUMN_GRADENAME, "9-");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 26);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 8);
        values.put(GradeListEntry.COLUMN_GRADENAME, "9");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 28);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 8);
        values.put(GradeListEntry.COLUMN_GRADENAME, "9+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 29.5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 8);
        values.put(GradeListEntry.COLUMN_GRADENAME, "10-");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 31);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 8);
        values.put(GradeListEntry.COLUMN_GRADENAME, "10");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 32.5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 8);
        values.put(GradeListEntry.COLUMN_GRADENAME, "10+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 34);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 8);
        values.put(GradeListEntry.COLUMN_GRADENAME, "11-");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 35.5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 8);
        values.put(GradeListEntry.COLUMN_GRADENAME, "11");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 37);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 8);
        values.put(GradeListEntry.COLUMN_GRADENAME, "11+");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, 38.5);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        double maxSubjectiveDifficulty = 38.5;
        double minSubjectiveDifficulty = 1;
        double numberSubjectiveGrades = 10;

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 9);
        values.put(GradeListEntry.COLUMN_GRADENAME, "Very Easy");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, minSubjectiveDifficulty);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 9);
        values.put(GradeListEntry.COLUMN_GRADENAME, "Easy");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, (1 * ((maxSubjectiveDifficulty - minSubjectiveDifficulty) / 9)) + minSubjectiveDifficulty);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 9);
        values.put(GradeListEntry.COLUMN_GRADENAME, "Easy/Medium");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, (2 * ((maxSubjectiveDifficulty - minSubjectiveDifficulty) / 9)) + minSubjectiveDifficulty);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 9);
        values.put(GradeListEntry.COLUMN_GRADENAME, "Medium");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, (3 * ((maxSubjectiveDifficulty - minSubjectiveDifficulty) / 9)) + minSubjectiveDifficulty);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 9);
        values.put(GradeListEntry.COLUMN_GRADENAME, "Medium/Difficult");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, (4 * ((maxSubjectiveDifficulty - minSubjectiveDifficulty) / 9)) + minSubjectiveDifficulty);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 9);
        values.put(GradeListEntry.COLUMN_GRADENAME, "Difficult");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, (5 * ((maxSubjectiveDifficulty - minSubjectiveDifficulty) / 9)) + minSubjectiveDifficulty);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 9);
        values.put(GradeListEntry.COLUMN_GRADENAME, "Very Difficult");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, (6 * ((maxSubjectiveDifficulty - minSubjectiveDifficulty) / 9)) + minSubjectiveDifficulty);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 9);
        values.put(GradeListEntry.COLUMN_GRADENAME, "Extreme");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, (7 * ((maxSubjectiveDifficulty - minSubjectiveDifficulty) / 9)) + minSubjectiveDifficulty);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 9);
        values.put(GradeListEntry.COLUMN_GRADENAME, "Very Extreme");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, (8 * ((maxSubjectiveDifficulty - minSubjectiveDifficulty) / 9)) + minSubjectiveDifficulty);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(GradeListEntry.COLUMN_GRADETYPECODE, 9);
        values.put(GradeListEntry.COLUMN_GRADENAME, "Impossible unless Adam Ondra");
        values.put(GradeListEntry.COLUMN_RELATIVEDIFFICULTY, maxSubjectiveDifficulty);
        db.insert(GradeListEntry.TABLE_NAME, null, values);

        Log.v(LOG_TAG, "Database Initial Fill Method Run");
    }

    public void insertInitialGradeTypeData(SQLiteDatabase db) {

        //1
        ContentValues values = new ContentValues();
        values.put(GradeTypeEntry.COLUMN_GRADETYPENAME, "French (Boulder)");
        db.insert(GradeTypeEntry.TABLE_NAME, null, values);

        //2
        values.clear();
        values = new ContentValues();
        values.put(GradeTypeEntry.COLUMN_GRADETYPENAME, "British (Boulder)");
        db.insert(GradeTypeEntry.TABLE_NAME, null, values);

        //3
        values.clear();
        values = new ContentValues();
        values.put(GradeTypeEntry.COLUMN_GRADETYPENAME, "V Grade (Boulder)");
        db.insert(GradeTypeEntry.TABLE_NAME, null, values);

        //4
        values.clear();
        values = new ContentValues();
        values.put(GradeTypeEntry.COLUMN_GRADETYPENAME, "French (Lead)");
        db.insert(GradeTypeEntry.TABLE_NAME, null, values);

        //5
        values.clear();
        values = new ContentValues();
        values.put(GradeTypeEntry.COLUMN_GRADETYPENAME, "British Trad (Lead)");
        db.insert(GradeTypeEntry.TABLE_NAME, null, values);

        //6
        values.clear();
        values = new ContentValues();
        values.put(GradeTypeEntry.COLUMN_GRADETYPENAME, "UIAA (Lead)");
        db.insert(GradeTypeEntry.TABLE_NAME, null, values);

        //7
        values.clear();
        values = new ContentValues();
        values.put(GradeTypeEntry.COLUMN_GRADETYPENAME, "Yosemite Decimal (Lead)");
        db.insert(GradeTypeEntry.TABLE_NAME, null, values);

        //8
        values.clear();
        values = new ContentValues();
        values.put(GradeTypeEntry.COLUMN_GRADETYPENAME, "Norwegian");
        db.insert(GradeTypeEntry.TABLE_NAME, null, values);

        //9
        values.clear();
        values = new ContentValues();
        values.put(GradeTypeEntry.COLUMN_GRADETYPENAME, "Subjective Grade");
        db.insert(GradeTypeEntry.TABLE_NAME, null, values);

    }

    public void insertInitialAscentTypeData(SQLiteDatabase db) {

        ContentValues values = new ContentValues();
        values.put(AscentEntry.COLUMN_ASCENTTYPENAME, "Flash");
        values.put(AscentEntry.COLUMN_DESCRIPTION, "To successfully and cleanly complete a climbing route on the first attempt after having received beta of some form.");
        db.insert(AscentEntry.TABLE_NAME, null, values);
        values.clear();

        values = new ContentValues();
        values.put(AscentEntry.COLUMN_ASCENTTYPENAME, "Free Solo");
        values.put(AscentEntry.COLUMN_DESCRIPTION, "Climbing without aid or protection.");
        db.insert(AscentEntry.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(AscentEntry.COLUMN_ASCENTTYPENAME, "Headpoint");
        values.put(AscentEntry.COLUMN_DESCRIPTION, "The practice of top-roping a hard trad route before leading it cleanly.");
        db.insert(AscentEntry.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(AscentEntry.COLUMN_ASCENTTYPENAME, "Pinkpoint");
        values.put(AscentEntry.COLUMN_DESCRIPTION, "To complete a lead climb without falling or resting on the rope (hangdogging), but with pre-placed protection and carabiners.");
        db.insert(AscentEntry.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(AscentEntry.COLUMN_ASCENTTYPENAME, "Aid-Climb");
        values.put(AscentEntry.COLUMN_DESCRIPTION, "A style of climbing in which standing on or pulling oneself up via devices attached to fixed or placed protection is used to make upward progress.");
        db.insert(AscentEntry.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(AscentEntry.COLUMN_ASCENTTYPENAME, "Free Base");
        values.put(AscentEntry.COLUMN_DESCRIPTION, "Climbing with your only protection being a parachute that is deployed in the event of a fall. A combination of free soloing, and BASE jumping.");
        db.insert(AscentEntry.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(AscentEntry.COLUMN_ASCENTTYPENAME, "On-Sight");
        values.put(AscentEntry.COLUMN_DESCRIPTION, "A clean ascent, with no prior practice or beta.");
        db.insert(AscentEntry.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(AscentEntry.COLUMN_ASCENTTYPENAME, "Top Rope");
        values.put(AscentEntry.COLUMN_DESCRIPTION, "A style in climbing in which the climber is securely attached to a rope which then passes up, through an anchor system at the top of the climb, and down to a belayer at the foot of the climb.");
        db.insert(AscentEntry.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(AscentEntry.COLUMN_ASCENTTYPENAME, "Redpoint");
        values.put(AscentEntry.COLUMN_DESCRIPTION, "Redpointing a route means free-climbing it by leading, after having practiced the route beforehand.");
        db.insert(AscentEntry.TABLE_NAME, null, values);



    }

    public void insertDummyClimbs(SQLiteDatabase db) {


        long currentDate = Calendar.getInstance().getTimeInMillis();
        long dayTwoDate = currentDate + 86400000;
        long dayThreeDate = currentDate + 86400000 * 2;
        long dayFourDate = currentDate - 86400000;
        long dayFiveDate = currentDate - 86400000 * 2;

        ContentValues values = new ContentValues();
        values.put(ClimbLogEntry.COLUMN_DATE, currentDate);
        values.put(ClimbLogEntry.COLUMN_NAME, "Climb 1");
        values.put(ClimbLogEntry.COLUMN_GRADETYPECODE, 1);
        values.put(ClimbLogEntry.COLUMN_GRADECODE, 1);
        values.put(ClimbLogEntry.COLUMN_ASCENTTYPECODE, 1);
        values.put(ClimbLogEntry.COLUMN_LOCATION, "Location 1");
        values.put(ClimbLogEntry.COLUMN_FIRSTASCENTCODE, 1);
        values.put(ClimbLogEntry.COLUMN_LOGTAG, ClimbLogEntry.LOGTAG_CLIMB);
        db.insert(ClimbLogEntry.TABLE_NAME, null, values);
        values.clear();

        values = new ContentValues();
        values.put(ClimbLogEntry.COLUMN_DATE, currentDate);
        values.put(ClimbLogEntry.COLUMN_NAME, "Climb 2");
        values.put(ClimbLogEntry.COLUMN_GRADETYPECODE, 1);
        values.put(ClimbLogEntry.COLUMN_GRADECODE, 1);
        values.put(ClimbLogEntry.COLUMN_ASCENTTYPECODE, 1);
        values.put(ClimbLogEntry.COLUMN_LOCATION, "Location 2");
        values.put(ClimbLogEntry.COLUMN_FIRSTASCENTCODE, 1);
        values.put(ClimbLogEntry.COLUMN_LOGTAG, ClimbLogEntry.LOGTAG_CLIMB);
        db.insert(ClimbLogEntry.TABLE_NAME, null, values);
        values.clear();

        values = new ContentValues();
        values.put(ClimbLogEntry.COLUMN_DATE, currentDate);
        values.put(ClimbLogEntry.COLUMN_NAME, "Climb 3");
        values.put(ClimbLogEntry.COLUMN_GRADETYPECODE, 1);
        values.put(ClimbLogEntry.COLUMN_GRADECODE, 1);
        values.put(ClimbLogEntry.COLUMN_ASCENTTYPECODE, 1);
        values.put(ClimbLogEntry.COLUMN_LOCATION, "Location 3");
        values.put(ClimbLogEntry.COLUMN_FIRSTASCENTCODE, 1);
        values.put(ClimbLogEntry.COLUMN_LOGTAG, ClimbLogEntry.LOGTAG_CLIMB);
        db.insert(ClimbLogEntry.TABLE_NAME, null, values);
        values.clear();

        values = new ContentValues();
        values.put(ClimbLogEntry.COLUMN_DATE, currentDate);
        values.put(ClimbLogEntry.COLUMN_NAME, "Climb 4");
        values.put(ClimbLogEntry.COLUMN_GRADETYPECODE, 1);
        values.put(ClimbLogEntry.COLUMN_GRADECODE, 1);
        values.put(ClimbLogEntry.COLUMN_ASCENTTYPECODE, 1);
        values.put(ClimbLogEntry.COLUMN_LOCATION, "Location 4");
        values.put(ClimbLogEntry.COLUMN_FIRSTASCENTCODE, 1);
        values.put(ClimbLogEntry.COLUMN_LOGTAG, ClimbLogEntry.LOGTAG_CLIMB);
        db.insert(ClimbLogEntry.TABLE_NAME, null, values);
        values.clear();

        //day+1
        values = new ContentValues();
        values.put(ClimbLogEntry.COLUMN_DATE, dayTwoDate);
        values.put(ClimbLogEntry.COLUMN_NAME, "Climb 1+1");
        values.put(ClimbLogEntry.COLUMN_GRADETYPECODE, 1);
        values.put(ClimbLogEntry.COLUMN_GRADECODE, 1);
        values.put(ClimbLogEntry.COLUMN_ASCENTTYPECODE, 1);
        values.put(ClimbLogEntry.COLUMN_LOCATION, "Location 1+1");
        values.put(ClimbLogEntry.COLUMN_FIRSTASCENTCODE, 1);
        values.put(ClimbLogEntry.COLUMN_LOGTAG, ClimbLogEntry.LOGTAG_CLIMB);
        db.insert(ClimbLogEntry.TABLE_NAME, null, values);
        values.clear();

        values = new ContentValues();
        values.put(ClimbLogEntry.COLUMN_DATE, dayTwoDate);
        values.put(ClimbLogEntry.COLUMN_NAME, "Climb 2+1");
        values.put(ClimbLogEntry.COLUMN_GRADETYPECODE, 1);
        values.put(ClimbLogEntry.COLUMN_GRADECODE, 1);
        values.put(ClimbLogEntry.COLUMN_ASCENTTYPECODE, 1);
        values.put(ClimbLogEntry.COLUMN_LOCATION, "Location 2+1");
        values.put(ClimbLogEntry.COLUMN_FIRSTASCENTCODE, 1);
        values.put(ClimbLogEntry.COLUMN_LOGTAG, ClimbLogEntry.LOGTAG_CLIMB);
        db.insert(ClimbLogEntry.TABLE_NAME, null, values);
        values.clear();

        values = new ContentValues();
        values.put(ClimbLogEntry.COLUMN_DATE, dayTwoDate);
        values.put(ClimbLogEntry.COLUMN_NAME, "Climb 3+1");
        values.put(ClimbLogEntry.COLUMN_GRADETYPECODE, 1);
        values.put(ClimbLogEntry.COLUMN_GRADECODE, 1);
        values.put(ClimbLogEntry.COLUMN_ASCENTTYPECODE, 1);
        values.put(ClimbLogEntry.COLUMN_LOCATION, "Location 3+1");
        values.put(ClimbLogEntry.COLUMN_FIRSTASCENTCODE, 1);
        values.put(ClimbLogEntry.COLUMN_LOGTAG, ClimbLogEntry.LOGTAG_CLIMB);
        db.insert(ClimbLogEntry.TABLE_NAME, null, values);
        values.clear();

        values = new ContentValues();
        values.put(ClimbLogEntry.COLUMN_DATE, dayTwoDate);
        values.put(ClimbLogEntry.COLUMN_NAME, "Climb 4+1");
        values.put(ClimbLogEntry.COLUMN_GRADETYPECODE, 1);
        values.put(ClimbLogEntry.COLUMN_GRADECODE, 1);
        values.put(ClimbLogEntry.COLUMN_ASCENTTYPECODE, 1);
        values.put(ClimbLogEntry.COLUMN_LOCATION, "Location 4+1");
        values.put(ClimbLogEntry.COLUMN_FIRSTASCENTCODE, 1);
        values.put(ClimbLogEntry.COLUMN_LOGTAG, ClimbLogEntry.LOGTAG_CLIMB);
        db.insert(ClimbLogEntry.TABLE_NAME, null, values);
        values.clear();

        //day+2
        values = new ContentValues();
        values.put(ClimbLogEntry.COLUMN_DATE, dayThreeDate);
        values.put(ClimbLogEntry.COLUMN_NAME, "Climb 1+2");
        values.put(ClimbLogEntry.COLUMN_GRADETYPECODE, 1);
        values.put(ClimbLogEntry.COLUMN_GRADECODE, 1);
        values.put(ClimbLogEntry.COLUMN_ASCENTTYPECODE, 1);
        values.put(ClimbLogEntry.COLUMN_LOCATION, "Location 1+2");
        values.put(ClimbLogEntry.COLUMN_FIRSTASCENTCODE, 1);
        values.put(ClimbLogEntry.COLUMN_LOGTAG, ClimbLogEntry.LOGTAG_CLIMB);
        db.insert(ClimbLogEntry.TABLE_NAME, null, values);
        values.clear();

        values = new ContentValues();
        values.put(ClimbLogEntry.COLUMN_DATE, dayThreeDate);
        values.put(ClimbLogEntry.COLUMN_NAME, "Climb 2+2");
        values.put(ClimbLogEntry.COLUMN_GRADETYPECODE, 1);
        values.put(ClimbLogEntry.COLUMN_GRADECODE, 1);
        values.put(ClimbLogEntry.COLUMN_ASCENTTYPECODE, 1);
        values.put(ClimbLogEntry.COLUMN_LOCATION, "Location 2+2");
        values.put(ClimbLogEntry.COLUMN_FIRSTASCENTCODE, 1);
        values.put(ClimbLogEntry.COLUMN_LOGTAG, ClimbLogEntry.LOGTAG_CLIMB);
        db.insert(ClimbLogEntry.TABLE_NAME, null, values);
        values.clear();

        values = new ContentValues();
        values.put(ClimbLogEntry.COLUMN_DATE, dayThreeDate);
        values.put(ClimbLogEntry.COLUMN_NAME, "Climb 3+2");
        values.put(ClimbLogEntry.COLUMN_GRADETYPECODE, 1);
        values.put(ClimbLogEntry.COLUMN_GRADECODE, 1);
        values.put(ClimbLogEntry.COLUMN_ASCENTTYPECODE, 1);
        values.put(ClimbLogEntry.COLUMN_LOCATION, "Location 3+2");
        values.put(ClimbLogEntry.COLUMN_FIRSTASCENTCODE, 1);
        values.put(ClimbLogEntry.COLUMN_LOGTAG, ClimbLogEntry.LOGTAG_CLIMB);
        db.insert(ClimbLogEntry.TABLE_NAME, null, values);
        values.clear();

        values = new ContentValues();
        values.put(ClimbLogEntry.COLUMN_DATE, dayThreeDate);
        values.put(ClimbLogEntry.COLUMN_NAME, "Climb 4+2");
        values.put(ClimbLogEntry.COLUMN_GRADETYPECODE, 1);
        values.put(ClimbLogEntry.COLUMN_GRADECODE, 1);
        values.put(ClimbLogEntry.COLUMN_ASCENTTYPECODE, 1);
        values.put(ClimbLogEntry.COLUMN_LOCATION, "Location 4+2");
        values.put(ClimbLogEntry.COLUMN_FIRSTASCENTCODE, 1);
        values.put(ClimbLogEntry.COLUMN_LOGTAG, ClimbLogEntry.LOGTAG_CLIMB);
        db.insert(ClimbLogEntry.TABLE_NAME, null, values);
        values.clear();

        //day-1
        values = new ContentValues();
        values.put(ClimbLogEntry.COLUMN_DATE, dayFourDate);
        values.put(ClimbLogEntry.COLUMN_NAME, "Climb 1-1");
        values.put(ClimbLogEntry.COLUMN_GRADETYPECODE, 1);
        values.put(ClimbLogEntry.COLUMN_GRADECODE, 1);
        values.put(ClimbLogEntry.COLUMN_ASCENTTYPECODE, 1);
        values.put(ClimbLogEntry.COLUMN_LOCATION, "Location 1-1");
        values.put(ClimbLogEntry.COLUMN_FIRSTASCENTCODE, 1);
        values.put(ClimbLogEntry.COLUMN_LOGTAG, ClimbLogEntry.LOGTAG_CLIMB);
        db.insert(ClimbLogEntry.TABLE_NAME, null, values);
        values.clear();

        values = new ContentValues();
        values.put(ClimbLogEntry.COLUMN_DATE, dayFourDate);
        values.put(ClimbLogEntry.COLUMN_NAME, "Climb 2-1");
        values.put(ClimbLogEntry.COLUMN_GRADETYPECODE, 1);
        values.put(ClimbLogEntry.COLUMN_GRADECODE, 1);
        values.put(ClimbLogEntry.COLUMN_ASCENTTYPECODE, 1);
        values.put(ClimbLogEntry.COLUMN_LOCATION, "Location 2-1");
        values.put(ClimbLogEntry.COLUMN_FIRSTASCENTCODE, 1);
        values.put(ClimbLogEntry.COLUMN_LOGTAG, ClimbLogEntry.LOGTAG_CLIMB);
        db.insert(ClimbLogEntry.TABLE_NAME, null, values);
        values.clear();

        values = new ContentValues();
        values.put(ClimbLogEntry.COLUMN_DATE, dayFourDate);
        values.put(ClimbLogEntry.COLUMN_NAME, "Climb 3-1");
        values.put(ClimbLogEntry.COLUMN_GRADETYPECODE, 1);
        values.put(ClimbLogEntry.COLUMN_GRADECODE, 1);
        values.put(ClimbLogEntry.COLUMN_ASCENTTYPECODE, 1);
        values.put(ClimbLogEntry.COLUMN_LOCATION, "Location 3-1");
        values.put(ClimbLogEntry.COLUMN_FIRSTASCENTCODE, 1);
        values.put(ClimbLogEntry.COLUMN_LOGTAG, ClimbLogEntry.LOGTAG_CLIMB);
        db.insert(ClimbLogEntry.TABLE_NAME, null, values);
        values.clear();

        values = new ContentValues();
        values.put(ClimbLogEntry.COLUMN_DATE, dayFourDate);
        values.put(ClimbLogEntry.COLUMN_NAME, "Climb 4-1");
        values.put(ClimbLogEntry.COLUMN_GRADETYPECODE, 1);
        values.put(ClimbLogEntry.COLUMN_GRADECODE, 1);
        values.put(ClimbLogEntry.COLUMN_ASCENTTYPECODE, 1);
        values.put(ClimbLogEntry.COLUMN_LOCATION, "Location 4-1");
        values.put(ClimbLogEntry.COLUMN_FIRSTASCENTCODE, 1);
        values.put(ClimbLogEntry.COLUMN_LOGTAG, ClimbLogEntry.LOGTAG_CLIMB);
        db.insert(ClimbLogEntry.TABLE_NAME, null, values);
        values.clear();

        //day-2
        values = new ContentValues();
        values.put(ClimbLogEntry.COLUMN_DATE, dayFiveDate);
        values.put(ClimbLogEntry.COLUMN_NAME, "Climb 1-2");
        values.put(ClimbLogEntry.COLUMN_GRADETYPECODE, 1);
        values.put(ClimbLogEntry.COLUMN_GRADECODE, 1);
        values.put(ClimbLogEntry.COLUMN_ASCENTTYPECODE, 1);
        values.put(ClimbLogEntry.COLUMN_LOCATION, "Location 1-2");
        values.put(ClimbLogEntry.COLUMN_FIRSTASCENTCODE, 1);
        values.put(ClimbLogEntry.COLUMN_LOGTAG, ClimbLogEntry.LOGTAG_CLIMB);
        db.insert(ClimbLogEntry.TABLE_NAME, null, values);
        values.clear();

        values = new ContentValues();
        values.put(ClimbLogEntry.COLUMN_DATE, dayFiveDate);
        values.put(ClimbLogEntry.COLUMN_NAME, "Climb 2-2");
        values.put(ClimbLogEntry.COLUMN_GRADETYPECODE, 1);
        values.put(ClimbLogEntry.COLUMN_GRADECODE, 1);
        values.put(ClimbLogEntry.COLUMN_ASCENTTYPECODE, 1);
        values.put(ClimbLogEntry.COLUMN_LOCATION, "Location 2-2");
        values.put(ClimbLogEntry.COLUMN_FIRSTASCENTCODE, 1);
        values.put(ClimbLogEntry.COLUMN_LOGTAG, ClimbLogEntry.LOGTAG_CLIMB);
        db.insert(ClimbLogEntry.TABLE_NAME, null, values);
        values.clear();

        values = new ContentValues();
        values.put(ClimbLogEntry.COLUMN_DATE, dayFiveDate);
        values.put(ClimbLogEntry.COLUMN_NAME, "Climb 3-2");
        values.put(ClimbLogEntry.COLUMN_GRADETYPECODE, 1);
        values.put(ClimbLogEntry.COLUMN_GRADECODE, 1);
        values.put(ClimbLogEntry.COLUMN_ASCENTTYPECODE, 1);
        values.put(ClimbLogEntry.COLUMN_LOCATION, "Location 3-2");
        values.put(ClimbLogEntry.COLUMN_FIRSTASCENTCODE, 1);
        values.put(ClimbLogEntry.COLUMN_LOGTAG, ClimbLogEntry.LOGTAG_CLIMB);
        db.insert(ClimbLogEntry.TABLE_NAME, null, values);
        values.clear();

        values = new ContentValues();
        values.put(ClimbLogEntry.COLUMN_DATE, dayFiveDate);
        values.put(ClimbLogEntry.COLUMN_NAME, "Climb 4-2");
        values.put(ClimbLogEntry.COLUMN_GRADETYPECODE, 1);
        values.put(ClimbLogEntry.COLUMN_GRADECODE, 1);
        values.put(ClimbLogEntry.COLUMN_ASCENTTYPECODE, 1);
        values.put(ClimbLogEntry.COLUMN_LOCATION, "Location 4-2");
        values.put(ClimbLogEntry.COLUMN_FIRSTASCENTCODE, 1);
        values.put(ClimbLogEntry.COLUMN_LOGTAG, ClimbLogEntry.LOGTAG_CLIMB);
        db.insert(ClimbLogEntry.TABLE_NAME, null, values);
        values.clear();


    }

    public void insertInitialWorkoutTypeData(SQLiteDatabase db) {

        ContentValues values = new ContentValues();
        values.put(WorkoutTypeEntry.COLUMN_WORKOUTTYPENAME, "Strength");
        values.put(WorkoutTypeEntry.COLUMN_DESCRIPTION, "Description");
        db.insert(WorkoutTypeEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(WorkoutTypeEntry.COLUMN_WORKOUTTYPENAME, "Endurance");
        values.put(WorkoutTypeEntry.COLUMN_DESCRIPTION, "Description");
        db.insert(WorkoutTypeEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(WorkoutTypeEntry.COLUMN_WORKOUTTYPENAME, "Core");
        values.put(WorkoutTypeEntry.COLUMN_DESCRIPTION, "Description");
        db.insert(WorkoutTypeEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(WorkoutTypeEntry.COLUMN_WORKOUTTYPENAME, "Hangboard");
        values.put(WorkoutTypeEntry.COLUMN_DESCRIPTION, "Description");
        db.insert(WorkoutTypeEntry.TABLE_NAME, null, values);

        values.clear();
        values = new ContentValues();
        values.put(WorkoutTypeEntry.COLUMN_WORKOUTTYPENAME, "Custom");
        values.put(WorkoutTypeEntry.COLUMN_DESCRIPTION, "Description");
        db.insert(WorkoutTypeEntry.TABLE_NAME, null, values);

    }

    public void insertInitialWorkoutListData(SQLiteDatabase db) {

        ContentValues values = new ContentValues();
        values.put(WorkoutListEntry.COLUMN_NAME, "Pull-Ups"); // Text
        values.put(WorkoutListEntry.COLUMN_WORKOUTTYPECODE, 1); // Integer
        values.put(WorkoutListEntry.COLUMN_DESCRIPTION, "Description"); // Text
        values.put(WorkoutListEntry.COLUMN_ISCLIMB, WorkoutListEntry.IS_FALSE); // Text
        values.put(WorkoutListEntry.COLUMN_ISWEIGHT, WorkoutListEntry.IS_TRUE); // Text
        values.put(WorkoutListEntry.COLUMN_ISSETCOUNT, WorkoutListEntry.IS_TRUE); // Text
        values.put(WorkoutListEntry.COLUMN_ISREPCOUNTPERSET, WorkoutListEntry.IS_TRUE); // Text
        values.put(WorkoutListEntry.COLUMN_ISREPDURATIONPERSET, WorkoutListEntry.IS_FALSE); // Text
        values.put(WorkoutListEntry.COLUMN_ISRESTDURATIONPERSET, WorkoutListEntry.IS_TRUE); // Text
        values.put(WorkoutListEntry.COLUMN_ISGRADECODE, WorkoutListEntry.IS_FALSE); // Text
        db.insert(WorkoutListEntry.TABLE_NAME, null, values);
        values.clear();

        values = new ContentValues();
        values.put(WorkoutListEntry.COLUMN_NAME, "Squats"); // Text
        values.put(WorkoutListEntry.COLUMN_WORKOUTTYPECODE, 1); // Integer
        values.put(WorkoutListEntry.COLUMN_DESCRIPTION, "Description"); // Text
        values.put(WorkoutListEntry.COLUMN_ISCLIMB, WorkoutListEntry.IS_FALSE); // Text
        values.put(WorkoutListEntry.COLUMN_ISWEIGHT, WorkoutListEntry.IS_TRUE); // Text
        values.put(WorkoutListEntry.COLUMN_ISSETCOUNT, WorkoutListEntry.IS_TRUE); // Text
        values.put(WorkoutListEntry.COLUMN_ISREPCOUNTPERSET, WorkoutListEntry.IS_TRUE); // Text
        values.put(WorkoutListEntry.COLUMN_ISREPDURATIONPERSET, WorkoutListEntry.IS_FALSE); // Text
        values.put(WorkoutListEntry.COLUMN_ISRESTDURATIONPERSET, WorkoutListEntry.IS_TRUE); // Text
        values.put(WorkoutListEntry.COLUMN_ISGRADECODE, WorkoutListEntry.IS_FALSE); // Text
        db.insert(WorkoutListEntry.TABLE_NAME, null, values);
        values.clear();

        values = new ContentValues();
        values.put(WorkoutListEntry.COLUMN_NAME, "Push-Ups"); // Text
        values.put(WorkoutListEntry.COLUMN_WORKOUTTYPECODE, 1); // Integer
        values.put(WorkoutListEntry.COLUMN_DESCRIPTION, "Description"); // Text
        values.put(WorkoutListEntry.COLUMN_ISCLIMB, WorkoutListEntry.IS_FALSE); // Text
        values.put(WorkoutListEntry.COLUMN_ISWEIGHT, WorkoutListEntry.IS_TRUE); // Text
        values.put(WorkoutListEntry.COLUMN_ISSETCOUNT, WorkoutListEntry.IS_TRUE); // Text
        values.put(WorkoutListEntry.COLUMN_ISREPCOUNTPERSET, WorkoutListEntry.IS_TRUE); // Text
        values.put(WorkoutListEntry.COLUMN_ISREPDURATIONPERSET, WorkoutListEntry.IS_FALSE); // Text
        values.put(WorkoutListEntry.COLUMN_ISRESTDURATIONPERSET, WorkoutListEntry.IS_TRUE); // Text
        values.put(WorkoutListEntry.COLUMN_ISGRADECODE, WorkoutListEntry.IS_FALSE); // Text
        db.insert(WorkoutListEntry.TABLE_NAME, null, values);
        values.clear();

        values = new ContentValues();
        values.put(WorkoutListEntry.COLUMN_NAME, "Shoulder Press"); // Text
        values.put(WorkoutListEntry.COLUMN_WORKOUTTYPECODE, 1); // Integer
        values.put(WorkoutListEntry.COLUMN_DESCRIPTION, "Description"); // Text
        values.put(WorkoutListEntry.COLUMN_ISCLIMB, WorkoutListEntry.IS_FALSE); // Text
        values.put(WorkoutListEntry.COLUMN_ISWEIGHT, WorkoutListEntry.IS_TRUE); // Text
        values.put(WorkoutListEntry.COLUMN_ISSETCOUNT, WorkoutListEntry.IS_TRUE); // Text
        values.put(WorkoutListEntry.COLUMN_ISREPCOUNTPERSET, WorkoutListEntry.IS_TRUE); // Text
        values.put(WorkoutListEntry.COLUMN_ISREPDURATIONPERSET, WorkoutListEntry.IS_FALSE); // Text
        values.put(WorkoutListEntry.COLUMN_ISRESTDURATIONPERSET, WorkoutListEntry.IS_TRUE); // Text
        values.put(WorkoutListEntry.COLUMN_ISGRADECODE, WorkoutListEntry.IS_FALSE); // Text
        db.insert(WorkoutListEntry.TABLE_NAME, null, values);
        values.clear();

        values = new ContentValues();
        values.put(WorkoutListEntry.COLUMN_NAME, "Random Endurance Workout"); // Text
        values.put(WorkoutListEntry.COLUMN_WORKOUTTYPECODE, 2); // Integer
        values.put(WorkoutListEntry.COLUMN_DESCRIPTION, "Description"); // Text
        values.put(WorkoutListEntry.COLUMN_ISCLIMB, WorkoutListEntry.IS_FALSE); // Text
        values.put(WorkoutListEntry.COLUMN_ISWEIGHT, WorkoutListEntry.IS_TRUE); // Text
        values.put(WorkoutListEntry.COLUMN_ISSETCOUNT, WorkoutListEntry.IS_TRUE); // Text
        values.put(WorkoutListEntry.COLUMN_ISREPCOUNTPERSET, WorkoutListEntry.IS_TRUE); // Text
        values.put(WorkoutListEntry.COLUMN_ISREPDURATIONPERSET, WorkoutListEntry.IS_FALSE); // Text
        values.put(WorkoutListEntry.COLUMN_ISRESTDURATIONPERSET, WorkoutListEntry.IS_TRUE); // Text
        values.put(WorkoutListEntry.COLUMN_ISGRADECODE, WorkoutListEntry.IS_FALSE); // Text
        db.insert(WorkoutListEntry.TABLE_NAME, null, values);
        values.clear();

        values = new ContentValues();
        values.put(WorkoutListEntry.COLUMN_NAME, "Plank"); // Text
        values.put(WorkoutListEntry.COLUMN_WORKOUTTYPECODE, 3); // Integer
        values.put(WorkoutListEntry.COLUMN_DESCRIPTION, "Description"); // Text
        values.put(WorkoutListEntry.COLUMN_ISCLIMB, WorkoutListEntry.IS_FALSE); // Text
        values.put(WorkoutListEntry.COLUMN_ISWEIGHT, WorkoutListEntry.IS_FALSE); // Text
        values.put(WorkoutListEntry.COLUMN_ISSETCOUNT, WorkoutListEntry.IS_TRUE); // Text
        values.put(WorkoutListEntry.COLUMN_ISREPCOUNTPERSET, WorkoutListEntry.IS_FALSE); // Text
        values.put(WorkoutListEntry.COLUMN_ISREPDURATIONPERSET, WorkoutListEntry.IS_TRUE); // Text
        values.put(WorkoutListEntry.COLUMN_ISRESTDURATIONPERSET, WorkoutListEntry.IS_TRUE); // Text
        values.put(WorkoutListEntry.COLUMN_ISGRADECODE, WorkoutListEntry.IS_FALSE); // Text
        db.insert(WorkoutListEntry.TABLE_NAME, null, values);
        values.clear();

        values = new ContentValues();
        values.put(WorkoutListEntry.COLUMN_NAME, "Front Lever"); // Text
        values.put(WorkoutListEntry.COLUMN_WORKOUTTYPECODE, 3); // Integer
        values.put(WorkoutListEntry.COLUMN_DESCRIPTION, "Description"); // Text
        values.put(WorkoutListEntry.COLUMN_ISCLIMB, WorkoutListEntry.IS_FALSE); // Text
        values.put(WorkoutListEntry.COLUMN_ISWEIGHT, WorkoutListEntry.IS_FALSE); // Text
        values.put(WorkoutListEntry.COLUMN_ISSETCOUNT, WorkoutListEntry.IS_TRUE); // Text
        values.put(WorkoutListEntry.COLUMN_ISREPCOUNTPERSET, WorkoutListEntry.IS_TRUE); // Text
        values.put(WorkoutListEntry.COLUMN_ISREPDURATIONPERSET, WorkoutListEntry.IS_TRUE); // Text
        values.put(WorkoutListEntry.COLUMN_ISRESTDURATIONPERSET, WorkoutListEntry.IS_TRUE); // Text
        values.put(WorkoutListEntry.COLUMN_ISGRADECODE, WorkoutListEntry.IS_FALSE); // Text
        db.insert(WorkoutListEntry.TABLE_NAME, null, values);
        values.clear();

        values = new ContentValues();
        values.put(WorkoutListEntry.COLUMN_NAME, "Sit-Up"); // Text
        values.put(WorkoutListEntry.COLUMN_WORKOUTTYPECODE, 3); // Integer
        values.put(WorkoutListEntry.COLUMN_DESCRIPTION, "Description"); // Text
        values.put(WorkoutListEntry.COLUMN_ISCLIMB, WorkoutListEntry.IS_FALSE); // Text
        values.put(WorkoutListEntry.COLUMN_ISWEIGHT, WorkoutListEntry.IS_FALSE); // Text
        values.put(WorkoutListEntry.COLUMN_ISSETCOUNT, WorkoutListEntry.IS_TRUE); // Text
        values.put(WorkoutListEntry.COLUMN_ISREPCOUNTPERSET, WorkoutListEntry.IS_TRUE); // Text
        values.put(WorkoutListEntry.COLUMN_ISREPDURATIONPERSET, WorkoutListEntry.IS_FALSE); // Text
        values.put(WorkoutListEntry.COLUMN_ISRESTDURATIONPERSET, WorkoutListEntry.IS_TRUE); // Text
        values.put(WorkoutListEntry.COLUMN_ISGRADECODE, WorkoutListEntry.IS_FALSE); // Text
        db.insert(WorkoutListEntry.TABLE_NAME, null, values);
        values.clear();

        values = new ContentValues();
        values.put(WorkoutListEntry.COLUMN_NAME, "90deg Bent-Arm Hang"); // Text
        values.put(WorkoutListEntry.COLUMN_WORKOUTTYPECODE, 4); // Integer
        values.put(WorkoutListEntry.COLUMN_DESCRIPTION, "Description"); // Text
        values.put(WorkoutListEntry.COLUMN_ISCLIMB, WorkoutListEntry.IS_FALSE); // Text
        values.put(WorkoutListEntry.COLUMN_ISWEIGHT, WorkoutListEntry.IS_TRUE); // Text
        values.put(WorkoutListEntry.COLUMN_ISSETCOUNT, WorkoutListEntry.IS_TRUE); // Text
        values.put(WorkoutListEntry.COLUMN_ISREPCOUNTPERSET, WorkoutListEntry.IS_TRUE); // Text
        values.put(WorkoutListEntry.COLUMN_ISREPDURATIONPERSET, WorkoutListEntry.IS_TRUE); // Text
        values.put(WorkoutListEntry.COLUMN_ISRESTDURATIONPERSET, WorkoutListEntry.IS_TRUE); // Text
        values.put(WorkoutListEntry.COLUMN_ISGRADECODE, WorkoutListEntry.IS_FALSE); // Text
        db.insert(WorkoutListEntry.TABLE_NAME, null, values);
        values.clear();

        values = new ContentValues();
        values.put(WorkoutListEntry.COLUMN_NAME, "Custom Something"); // Text
        values.put(WorkoutListEntry.COLUMN_WORKOUTTYPECODE, 5); // Integer
        values.put(WorkoutListEntry.COLUMN_DESCRIPTION, "Description"); // Text
        values.put(WorkoutListEntry.COLUMN_ISCLIMB, WorkoutListEntry.IS_FALSE); // Text
        values.put(WorkoutListEntry.COLUMN_ISWEIGHT, WorkoutListEntry.IS_TRUE); // Text
        values.put(WorkoutListEntry.COLUMN_ISSETCOUNT, WorkoutListEntry.IS_TRUE); // Text
        values.put(WorkoutListEntry.COLUMN_ISREPCOUNTPERSET, WorkoutListEntry.IS_TRUE); // Text
        values.put(WorkoutListEntry.COLUMN_ISREPDURATIONPERSET, WorkoutListEntry.IS_TRUE); // Text
        values.put(WorkoutListEntry.COLUMN_ISRESTDURATIONPERSET, WorkoutListEntry.IS_TRUE); // Text
        values.put(WorkoutListEntry.COLUMN_ISGRADECODE, WorkoutListEntry.IS_FALSE); // Text
        db.insert(WorkoutListEntry.TABLE_NAME, null, values);
        values.clear();

    }

}
