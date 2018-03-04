package com.example.android.anothertest.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import static com.example.android.anothertest.util.TimeUtils.convertDate;

/**
 * Created by Bobek on 27/02/2018.
 */

public class DatabaseReadWrite {

    /**
     * Get the position in the ViewPager for a given day
     *
     * @param inputRowID the row that needs to be read from the database
     * @param mContext   the context
     * @return A bundle containing:
     * String outputRouteName
     * String outputLocationName
     * Long outputDate
     * String outputDateString
     * Int outputFirstAscent
     * Int outputGradeNumber
     * Int outputGradeName
     * Int outputAscent
     */
    public static Bundle EditClimbLoadEntry(int inputRowID, Context mContext) {

        Bundle outputBundle = new Bundle();

        DatabaseHelper handler = new DatabaseHelper(mContext);
        SQLiteDatabase database = handler.getWritableDatabase();

        String[] projection = {
                DatabaseContract.ClimbLogEntry._ID,
                DatabaseContract.ClimbLogEntry.COLUMN_DATE,
                DatabaseContract.ClimbLogEntry.COLUMN_NAME,
                DatabaseContract.ClimbLogEntry.COLUMN_GRADETYPECODE,
                DatabaseContract.ClimbLogEntry.COLUMN_GRADECODE,
                DatabaseContract.ClimbLogEntry.COLUMN_ASCENTTYPECODE,
                DatabaseContract.ClimbLogEntry.COLUMN_LOCATION,
                DatabaseContract.ClimbLogEntry.COLUMN_FIRSTASCENTCODE,
                DatabaseContract.ClimbLogEntry.COLUMN_LOGTAG};
        String whereClause = DatabaseContract.ClimbLogEntry._ID + "=?";
        String[] whereValue = {String.valueOf(inputRowID)};

        Cursor cursor = database.query(DatabaseContract.ClimbLogEntry.TABLE_NAME,
                projection,
                whereClause,
                whereValue,
                null,
                null,
                null);

        try {
            cursor.moveToFirst();

            // Get and set route name
            int idColumnOutput = cursor.getColumnIndex(DatabaseContract.ClimbLogEntry.COLUMN_NAME);
            String outputRouteName = cursor.getString(idColumnOutput);

            // Get and set  location name
            idColumnOutput = cursor.getColumnIndex(DatabaseContract.ClimbLogEntry.COLUMN_NAME);
            String outputLocationName = cursor.getString(idColumnOutput);

            // Get date
            idColumnOutput = cursor.getColumnIndex(DatabaseContract.ClimbLogEntry.COLUMN_DATE);
            Long outputDate = (long) cursor.getLong(idColumnOutput);
            String outputDateString = convertDate(outputDate, "dd/MM/yyyy");

            // Get whether first ascent or not
            idColumnOutput = cursor.getColumnIndex(DatabaseContract.ClimbLogEntry.COLUMN_FIRSTASCENTCODE);
            int outputFirstAscent = cursor.getInt(idColumnOutput);

            // Get grade
            idColumnOutput = cursor.getColumnIndex(DatabaseContract.ClimbLogEntry.COLUMN_GRADECODE);
            int outputGradeNumber = cursor.getInt(idColumnOutput);
            idColumnOutput = cursor.getColumnIndex(DatabaseContract.ClimbLogEntry.COLUMN_GRADETYPECODE);
            int outputGradeName = cursor.getInt(idColumnOutput);

            // Get ascent type
            idColumnOutput = cursor.getColumnIndex(DatabaseContract.ClimbLogEntry.COLUMN_ASCENTTYPECODE);
            int outputAscent = cursor.getInt(idColumnOutput);

            outputBundle.putString("outputRouteName", outputRouteName);
            outputBundle.putString("outputLocationName", outputLocationName);
            outputBundle.putLong("outputDate", outputDate);
            outputBundle.putString("outputDateString", outputDateString);
            outputBundle.putInt("outputFirstAscent", outputFirstAscent);
            outputBundle.putInt("outputGradeNumber", outputGradeNumber);
            outputBundle.putInt("outputGradeName", outputGradeName);
            outputBundle.putInt("outputAscent", outputAscent);

            return outputBundle;

        } finally {
            cursor.close();
            database.close();
        }

    }

    /**
     * Get the Ascent type name for a given ID
     *
     * @param infoCode the ascent type ID
     * @param mContext the context
     * @return A string containing the Ascent Type Name
     */
    public static String getAscentNameTextClimb(int infoCode, Context mContext) {

        DatabaseHelper handler = new DatabaseHelper(mContext);
        SQLiteDatabase database = handler.getWritableDatabase();

        //ascent type
        String[] projection = {
                DatabaseContract.AscentEntry._ID,
                DatabaseContract.AscentEntry.COLUMN_ASCENTTYPENAME};
        String whereClause = DatabaseContract.AscentEntry._ID + "=?";
        String[] whereValue = {String.valueOf(infoCode)};

        Cursor cursor = database.query(DatabaseContract.AscentEntry.TABLE_NAME,
                projection,
                whereClause,
                whereValue,
                null,
                null,
                null);

        int idColumnOutput = cursor.getColumnIndex(DatabaseContract.AscentEntry.COLUMN_ASCENTTYPENAME);

        try {
            cursor.moveToFirst();
            String outputString = cursor.getString(idColumnOutput);
            return outputString;
        } finally {
            cursor.close();
            database.close();
        }


    }

    /**
     * Get the Grade name for a given ID
     *
     * @param gradeCode the grade ID
     * @param mContext  the context
     * @return A string containing the Grade Name
     */
    public static String getGradeTextClimb(int gradeCode, Context mContext) {

        DatabaseHelper handler = new DatabaseHelper(mContext);
        SQLiteDatabase database = handler.getWritableDatabase();

        //grade type
        String[] projection = {
                DatabaseContract.GradeListEntry._ID,
                DatabaseContract.GradeListEntry.COLUMN_GRADENAME};
        String whereClause = DatabaseContract.GradeListEntry._ID + "=?";
        String[] whereValue = {String.valueOf(gradeCode)};

        Cursor cursor = database.query(DatabaseContract.GradeListEntry.TABLE_NAME,
                projection,
                whereClause,
                whereValue,
                null,
                null,
                null);

        int idColumnOutput = cursor.getColumnIndex(DatabaseContract.GradeListEntry.COLUMN_GRADENAME);

        try {
            cursor.moveToFirst();
            String outputString = cursor.getString(idColumnOutput);
            return outputString;
        } finally {
            cursor.close();
            database.close();
        }

    }

    /**
     * Get the Grade name for a given ID
     *
     * @param gradeTypeCode the grade type ID
     * @param mContext      the context
     * @return A string containing the Grade Type Name
     */
    public static String getGradeTypeClimb(int gradeTypeCode, Context mContext) {

        DatabaseHelper handler = new DatabaseHelper(mContext);
        SQLiteDatabase database = handler.getWritableDatabase();

        //grade type
        String[] projection = {
                DatabaseContract.GradeTypeEntry._ID,
                DatabaseContract.GradeTypeEntry.COLUMN_GRADETYPENAME};
        String whereClause = DatabaseContract.GradeTypeEntry._ID + "=?";
        String[] whereValue = {String.valueOf(gradeTypeCode)};

        Cursor cursor = database.query(DatabaseContract.GradeTypeEntry.TABLE_NAME,
                projection,
                whereClause,
                whereValue,
                null,
                null,
                null);

        int idColumnOutput = cursor.getColumnIndex(DatabaseContract.GradeTypeEntry.COLUMN_GRADETYPENAME);

        try {
            cursor.moveToFirst();
            String outputString = cursor.getString(idColumnOutput);
            return outputString;
        } finally {
            cursor.close();
            database.close();
        }

    }

}
