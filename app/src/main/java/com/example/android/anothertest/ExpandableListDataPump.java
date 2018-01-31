package com.example.android.anothertest;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.android.anothertest.data.DatabaseContract.GradeListEntry;
import com.example.android.anothertest.data.DatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Bobek on 19/11/2017.
 */

public class ExpandableListDataPump {

    private static final String TAG = "ExLiDataPump_Tag";

    private static DatabaseHelper mDbHelper;

    // Create and return a hashmap of the data of interest

    public static HashMap<String, List<String>> getData(String inputType) {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        // TODO: modify to pull data from specific source with custom amount of parents and children

        Log.i(TAG, "input type = " + inputType);

        if (inputType.equals("grade_type")) {

            Log.i(TAG, "if-loop recognised grade_type input");

/*            List<String> yds = new ArrayList<String>();
            yds.add("5.2");
            yds.add("5.3");
            yds.add("5.4");
            yds.add("5.5");
            yds.add("5.6");
            yds.add("5.7");
            yds.add("5.8");
            yds.add("5.9");
            yds.add("5.10a");
            yds.add("5.10b");
            yds.add("5.10c");
            yds.add("5.10d");
            yds.add("5.11a");
            yds.add("5.11b");
            yds.add("5.11c");
            yds.add("5.11d");
            yds.add("5.12a");
            yds.add("5.12b");
            yds.add("5.12c");
            yds.add("5.12d");
            yds.add("5.13a");
            yds.add("5.13b");
            yds.add("5.13c");
            yds.add("5.13d");
            yds.add("5.14a");
            yds.add("5.14b");
            yds.add("5.14c");
            yds.add("5.14d");
            yds.add("5.15a");
            yds.add("5.15b");
            yds.add("5.15c");
            yds.add("5.15d");

            List<String> uiaa = new ArrayList<String>();
            uiaa.add("II");
            uiaa.add("III");
            uiaa.add("IV-");
            uiaa.add("IV");
            uiaa.add("IV+");
            uiaa.add("V-");
            uiaa.add("V");
            uiaa.add("V+");
            uiaa.add("VI-");
            uiaa.add("VI");
            uiaa.add("VI+");
            uiaa.add("VII-");
            uiaa.add("VII");
            uiaa.add("VII+");
            uiaa.add("VIII-");
            uiaa.add("VIII");
            uiaa.add("VIII+");
            uiaa.add("IX-");
            uiaa.add("IX");
            uiaa.add("IX+");
            uiaa.add("X-");
            uiaa.add("X");
            uiaa.add("X+");
            uiaa.add("XI-");
            uiaa.add("XI");
            uiaa.add("XI+");

            List<String> fr_lead = new ArrayList<String>();
            fr_lead.add("1");
            fr_lead.add("2");
            fr_lead.add("3");
            fr_lead.add("4");
            fr_lead.add("5a");
            fr_lead.add("5b");
            fr_lead.add("5c");
            fr_lead.add("6a");
            fr_lead.add("6a+");
            fr_lead.add("6b");
            fr_lead.add("6b+");
            fr_lead.add("6c");
            fr_lead.add("6c+");
            fr_lead.add("7a");
            fr_lead.add("7a+");
            fr_lead.add("7b");
            fr_lead.add("7b+");
            fr_lead.add("7c");
            fr_lead.add("7c+");
            fr_lead.add("8a");
            fr_lead.add("8a+");
            fr_lead.add("8b");
            fr_lead.add("8b+");
            fr_lead.add("8c");
            fr_lead.add("8c+");
            fr_lead.add("9a");
            fr_lead.add("9a+");
            fr_lead.add("9b");
            fr_lead.add("9b+");
            fr_lead.add("9c");
            fr_lead.add("9c+");

            expandableListDetail.put("YOSEMITE DECIMAL SYSTEM", yds);
            expandableListDetail.put("UIAA", uiaa);
            expandableListDetail.put("FRENCH (LEAD)", fr_lead);*/

            SQLiteDatabase db = mDbHelper.getReadableDatabase();

            // Define a projection that specifies which columns from the database
            // you will actually use after this query.
            String[] projection = {
                    GradeListEntry._ID,
                    GradeListEntry.COLUMN_GRADENAME,
                    GradeListEntry.COLUMN_GRADETYPECODE,
                    GradeListEntry.COLUMN_RELATIVEDIFFICULTY};

            // Perform a query on the GradeList table
            Cursor cursor = db.query(
                    GradeListEntry.TABLE_NAME,   // The table to query
                    projection,            // The columns to return
                    null,                  // The columns for the WHERE clause
                    null,                  // The values for the WHERE clause
                    null,                  // Don't group the rows
                    null,                  // Don't filter by row groups
                    null);                   // The sort order

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(GradeListEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(GradeListEntry.COLUMN_GRADENAME);
            int typeColumnIndex = cursor.getColumnIndex(GradeListEntry.COLUMN_GRADETYPECODE);
            int difficultyColumnIndex = cursor.getColumnIndex(GradeListEntry.COLUMN_RELATIVEDIFFICULTY);

            int previousType = 0;

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                int currentType = cursor.getInt(typeColumnIndex);
                Short currentDifficulty = cursor.getShort(difficultyColumnIndex);

                if (currentType != previousType) {
                    List<String> listHeader = new ArrayList<String>();
                }


            }


        } else if (inputType.equals("training_type")) {
            List<String> finger_board = new ArrayList<String>();
            finger_board.add("Dead Hang");
            finger_board.add("Frenchies");
            finger_board.add("Danglers");

            List<String> strength = new ArrayList<String>();
            strength.add("Campus");
            strength.add("One-Boulder Max");
            strength.add("One-Arm Pull-Ups");

            List<String> climbing = new ArrayList<String>();
            climbing.add("Ladder");
            climbing.add("Pyramid");
            climbing.add("Single Boulder");

            expandableListDetail.put("Finger Board", finger_board);
            expandableListDetail.put("Strength", strength);
            expandableListDetail.put("Climbing", climbing);

        } else if (inputType.equals("ascent_type")) {
            List<String> sport_climbing = new ArrayList<String>();
            sport_climbing.add("Top Rope");
            sport_climbing.add("Red point");
            sport_climbing.add("First ascent");

            List<String> bouldering = new ArrayList<String>();
            bouldering.add("Flash");
            bouldering.add("First Ascent");
            bouldering.add("Ground-up");

            expandableListDetail.put("Sport Climbing", sport_climbing);
            expandableListDetail.put("Bouldering", bouldering);
        }

        return expandableListDetail;
    }
}
