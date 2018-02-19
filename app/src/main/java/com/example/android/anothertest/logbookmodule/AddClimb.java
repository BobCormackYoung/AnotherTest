package com.example.android.anothertest.logbookmodule;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.anothertest.R;
import com.example.android.anothertest.data.DatabaseContract;
import com.example.android.anothertest.data.DatabaseHelper;
import com.example.android.anothertest.logbookmodule.ascentpicker.AscentHolder;
import com.example.android.anothertest.logbookmodule.gradepicker.ParentGradeHolder;

import java.util.Calendar;

/**
 * Created by Bobek on 11/02/2018.
 */

public class AddClimb extends AppCompatActivity {

    final String LOG_TAG = "LogClimbTag";

    final int PICK_GRADE_REQUEST = 1;
    final int PICK_ASCENT_TYPE_REQUEST = 2;
    final int ADD_CLIMB_NEW = 0;
    final int ADD_CLIMB_EDIT = 1;
    int outputGradeNumber = -1;
    int outputGradeName = -1;
    int outputAscent = -1;
    String outputLocationName = null;
    String outputRouteName = null;
    int outputFirstAscent = -1;
    long outputDate = -1;
    int inputIntentCode = -1;
    int inputRowID = -1;

    public static String convertDate(long dateInMilliseconds, String dateFormat) {
        return DateFormat.format(dateFormat, dateInMilliseconds).toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_climb);

        Intent inputIntent = getIntent();
        inputIntentCode = inputIntent.getIntExtra("EditOrNewFlag", 0);
        inputRowID = inputIntent.getIntExtra("RowID", 0);

        if (inputIntentCode == ADD_CLIMB_NEW) {
            // Add a new climb, don't import any data to the form
            outputDate = Calendar.getInstance().getTimeInMillis();
            String outputDateString = convertDate(outputDate, "dd/MM/yyyy");
            EditText dateView = (EditText) findViewById(R.id.editText5);
            dateView.setText(outputDateString);

        } else if (inputIntentCode == ADD_CLIMB_EDIT) {
            // Edit existing record, import data into the form
            DatabaseHelper handler = new DatabaseHelper(this);
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

            cursor.moveToFirst();

            // Get and set route name
            int idColumnOutput = cursor.getColumnIndex(DatabaseContract.ClimbLogEntry.COLUMN_NAME);
            outputRouteName = cursor.getString(idColumnOutput);
            EditText routeNameView = (EditText) findViewById(R.id.editText);
            routeNameView.setText(outputRouteName);

            // Get and set  location name
            idColumnOutput = cursor.getColumnIndex(DatabaseContract.ClimbLogEntry.COLUMN_NAME);
            outputLocationName = cursor.getString(idColumnOutput);
            EditText locationNameView = (EditText) findViewById(R.id.editText2);
            locationNameView.setText(outputLocationName);

            // Get date
            idColumnOutput = cursor.getColumnIndex(DatabaseContract.ClimbLogEntry.COLUMN_DATE);
            outputDate = (long) cursor.getLong(idColumnOutput);
            String outputDateString = convertDate(outputDate, "dd/MM/yyyy");
            EditText dateView = (EditText) findViewById(R.id.editText5);
            dateView.setText(String.valueOf(outputDateString));

            // Get whether first ascent or not
            idColumnOutput = cursor.getColumnIndex(DatabaseContract.ClimbLogEntry.COLUMN_FIRSTASCENTCODE);
            outputFirstAscent = cursor.getInt(idColumnOutput);
            CheckBox firstAscentCheckBox = (CheckBox) findViewById(R.id.checkbox_firstascent);
            if (outputFirstAscent == DatabaseContract.ClimbLogEntry.FIRSTASCENT_TRUE) {
                firstAscentCheckBox.setChecked(true);
            } else if (outputFirstAscent == DatabaseContract.ClimbLogEntry.FIRSTASCENT_FALSE) {
                firstAscentCheckBox.setChecked(false);
            }

            // Get grade
            idColumnOutput = cursor.getColumnIndex(DatabaseContract.ClimbLogEntry.COLUMN_GRADECODE);
            outputGradeNumber = cursor.getInt(idColumnOutput);
            idColumnOutput = cursor.getColumnIndex(DatabaseContract.ClimbLogEntry.COLUMN_GRADETYPECODE);
            outputGradeName = cursor.getInt(idColumnOutput);

            // Get ascent type
            idColumnOutput = cursor.getColumnIndex(DatabaseContract.ClimbLogEntry.COLUMN_ASCENTTYPECODE);
            outputAscent = cursor.getInt(idColumnOutput);

            cursor.close();

            // Set grade view
            // Get grade name
            String[] projectionTwo = {
                    DatabaseContract.GradeListEntry._ID,
                    DatabaseContract.GradeListEntry.COLUMN_GRADENAME};
            String whereClauseTwo = DatabaseContract.GradeListEntry._ID + "=?";
            String[] whereValueTwo = {String.valueOf(outputGradeNumber)};

            Cursor cursorTwo = database.query(DatabaseContract.GradeListEntry.TABLE_NAME,
                    projectionTwo,
                    whereClauseTwo,
                    whereValueTwo,
                    null,
                    null,
                    null);

            cursorTwo.moveToFirst();

            idColumnOutput = cursorTwo.getColumnIndex(DatabaseContract.GradeListEntry.COLUMN_GRADENAME);
            String outputStringTwo = cursorTwo.getString(idColumnOutput);

            cursorTwo.close();

            String[] projectionThree = {
                    DatabaseContract.GradeTypeEntry._ID,
                    DatabaseContract.GradeTypeEntry.COLUMN_GRADETYPENAME};
            String whereClauseThree = DatabaseContract.GradeTypeEntry._ID + "=?";
            String[] whereValueThree = {String.valueOf(outputGradeName)};

            Cursor cursorThree = database.query(DatabaseContract.GradeTypeEntry.TABLE_NAME,
                    projectionThree,
                    whereClauseThree,
                    whereValueThree,
                    null,
                    null,
                    null);

            cursorThree.moveToFirst();

            idColumnOutput = cursorThree.getColumnIndex(DatabaseContract.GradeTypeEntry.COLUMN_GRADETYPENAME);
            String outputStringThree = cursorThree.getString(idColumnOutput);

            cursorThree.close();

            EditText gradeView = (EditText) findViewById(R.id.editText4);
            gradeView.setText(outputStringThree + " | " + outputStringTwo);

            // Set ascent type
            String[] projectionFour = {
                    DatabaseContract.AscentEntry._ID,
                    DatabaseContract.AscentEntry.COLUMN_ASCENTTYPENAME,
                    DatabaseContract.AscentEntry.COLUMN_DESCRIPTION};
            String whereClauseFour = DatabaseContract.AscentEntry._ID + "=?";
            String[] whereValueFour = {String.valueOf(outputAscent)};

            Cursor cursorFour = database.query(DatabaseContract.AscentEntry.TABLE_NAME,
                    projectionFour,
                    whereClauseFour,
                    whereValueFour,
                    null,
                    null,
                    null);

            idColumnOutput = cursorFour.getColumnIndex(DatabaseContract.AscentEntry.COLUMN_ASCENTTYPENAME);

            cursorFour.moveToFirst();
            String outputStringFour = cursorFour.getString(idColumnOutput);

            EditText ascentTypeView = (EditText) findViewById(R.id.editText3);
            ascentTypeView.setText(outputStringFour);

            cursorFour.close();

        }


        // Listener for the grade selection
        EditText gradeView = (EditText) findViewById(R.id.editText4);
        gradeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickGrade();
            }
        });

        // Listener for the ascent-type selection
        EditText ascentTypeView = (EditText) findViewById(R.id.editText3);
        ascentTypeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickAscentType();
            }
        });

        // Listener for the save button
        Button saveButton = (Button) findViewById(R.id.log_climb_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText routeNameView = (EditText) findViewById(R.id.editText);
                EditText locationNameView = (EditText) findViewById(R.id.editText2);
                CheckBox firstAscentCheckBox = (CheckBox) findViewById(R.id.checkbox_firstascent);

                outputRouteName = routeNameView.getText().toString();
                outputLocationName = locationNameView.getText().toString();
                if (firstAscentCheckBox.isChecked()) {
                    outputFirstAscent = DatabaseContract.ClimbLogEntry.FIRSTASCENT_TRUE;
                } else {
                    outputFirstAscent = DatabaseContract.ClimbLogEntry.FIRSTASCENT_FALSE;
                }


                if (outputRouteName.trim().equals("") || outputLocationName.trim().equals("")
                        || outputAscent == -1 || outputGradeName == -1 || outputGradeNumber == -1
                        || outputDate == -1 || outputFirstAscent == -1) {
                    Toast.makeText(getApplicationContext(), "Insufficient information - please ensure all fields are filled", Toast.LENGTH_SHORT).show();
                } else {
                    if (inputIntentCode == ADD_CLIMB_EDIT) {
                        long updateResult = updateData(outputRouteName, outputLocationName, outputAscent, outputGradeName, outputGradeNumber, outputDate, outputFirstAscent, inputRowID);
                        Toast.makeText(getApplicationContext(), "Existing Row ID: " + String.valueOf(updateResult), Toast.LENGTH_SHORT).show();
                        finish();
                    } else if (inputIntentCode == ADD_CLIMB_NEW) {
                        long writeResult = writeData(outputRouteName, outputLocationName, outputAscent, outputGradeName, outputGradeNumber, outputDate, outputFirstAscent);
                        Toast.makeText(getApplicationContext(), "New Row ID: " + String.valueOf(writeResult), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }

            }
        });

        // Listener for the cancel button
        Button cancelButton = (Button) findViewById(R.id.log_climb_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == PICK_GRADE_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                putGrade(data);
            } else {
                Log.i(LOG_TAG, "result not okay");
            }
            // TODO: Error handler for nothing passed back
        } else if (requestCode == PICK_ASCENT_TYPE_REQUEST) {
            if (resultCode == RESULT_OK) {
                putAscent(data);
            } else {
                Log.i(LOG_TAG, "result not okay");
            }
            // TODO: Error handler for nothing passed back
        }
    }

    // method for launching the activity for picking the grade
    private void pickGrade() {
        // Create new intent
        Intent selectorIntent = new Intent(this, ParentGradeHolder.class);
        // Start activity for getting result
        startActivityForResult(selectorIntent, PICK_GRADE_REQUEST);
    }

    // method for launching the activity for picking the ascent type
    private void pickAscentType() {
        // Create new intent
        Intent selectorIntent = new Intent(this, AscentHolder.class);
        // Start activity for getting result
        startActivityForResult(selectorIntent, PICK_ASCENT_TYPE_REQUEST);
    }

    // method for inserting the grade data into the method variables & insert into textviews
    private void putGrade(Intent data) {
        // The user picked a grade, get the grade number
        outputGradeNumber = data.getIntExtra("OutputGradeNumber", 0);

        Log.i(LOG_TAG, String.valueOf(outputGradeNumber));

        //Create handler to connect to SQLite DB
        DatabaseHelper handler = new DatabaseHelper(this);
        SQLiteDatabase database = handler.getWritableDatabase();

        String[] projection = {
                DatabaseContract.GradeListEntry._ID,
                DatabaseContract.GradeListEntry.COLUMN_GRADENAME};
        String whereClause = DatabaseContract.GradeListEntry._ID + "=?";
        String[] whereValue = {String.valueOf(outputGradeNumber)};

        Cursor cursor = database.query(DatabaseContract.GradeListEntry.TABLE_NAME,
                projection,
                whereClause,
                whereValue,
                null,
                null,
                null);

        int idColumnOutput = cursor.getColumnIndex(DatabaseContract.GradeListEntry.COLUMN_GRADENAME);

        Log.i(LOG_TAG, "column index = " + idColumnOutput);

        while (cursor.moveToNext()) {
            String outputString = cursor.getString(idColumnOutput);
            Log.i(LOG_TAG, outputString);

            EditText gradeView = (EditText) findViewById(R.id.editText4);
            gradeView.setText(outputString);
        }

        cursor.close();

        // The user picked a grade, get the grade number
        outputGradeName = data.getIntExtra("OutputGradeName", 0);

        Log.i(LOG_TAG, String.valueOf(outputGradeName));

        //Create handler to connect to SQLite DB
        DatabaseHelper handler1 = new DatabaseHelper(this);
        SQLiteDatabase database1 = handler.getWritableDatabase();

        String[] projection1 = {
                DatabaseContract.GradeTypeEntry._ID,
                DatabaseContract.GradeTypeEntry.COLUMN_GRADETYPENAME};
        String whereClause1 = DatabaseContract.GradeTypeEntry._ID + "=?";
        String[] whereValue1 = {String.valueOf(outputGradeName)};

        Cursor cursor1 = database.query(DatabaseContract.GradeTypeEntry.TABLE_NAME,
                projection1,
                whereClause1,
                whereValue1,
                null,
                null,
                null);

        int idColumnOutput1 = cursor1.getColumnIndex(DatabaseContract.GradeTypeEntry.COLUMN_GRADETYPENAME);

        Log.i(LOG_TAG, "column index = " + idColumnOutput1);

        while (cursor1.moveToNext()) {
            String outputString1 = cursor1.getString(idColumnOutput1);
            Log.i(LOG_TAG, outputString1);

            EditText gradeView = (EditText) findViewById(R.id.editText4);
            String existingText = String.valueOf(gradeView.getText());
            gradeView.setText(outputString1 + " | " + existingText);
        }

        cursor1.close();
    }

    // method for inserting the ascent data into the method variables & insert into textviews
    private void putAscent(Intent data) {
        outputAscent = data.getIntExtra("OutputData", 0);

        Log.i(LOG_TAG, String.valueOf(outputAscent));

        //Create handler to connect to SQLite DB
        DatabaseHelper handler = new DatabaseHelper(this);
        SQLiteDatabase database = handler.getWritableDatabase();

        String[] projection = {
                DatabaseContract.AscentEntry._ID,
                DatabaseContract.AscentEntry.COLUMN_ASCENTTYPENAME,
                DatabaseContract.AscentEntry.COLUMN_DESCRIPTION};
        String whereClause = DatabaseContract.AscentEntry._ID + "=?";
        String[] whereValue = {String.valueOf(outputAscent)};

        Cursor cursor = database.query(DatabaseContract.AscentEntry.TABLE_NAME,
                projection,
                whereClause,
                whereValue,
                null,
                null,
                null);

        int idColumnOutput = cursor.getColumnIndex(DatabaseContract.AscentEntry.COLUMN_ASCENTTYPENAME);

        Log.i(LOG_TAG, "column index = " + idColumnOutput);

        while (cursor.moveToNext()) {
            String outputString = cursor.getString(idColumnOutput);
            Log.i(LOG_TAG, outputString);

            EditText gradeView = (EditText) findViewById(R.id.editText3);
            gradeView.setText(outputString);
        }

        cursor.close();
    }

    private void findGrade() {

    }

    private void findAscent() {

    }

    //write result information to database
    private long writeData(String routeName, String locationName, int ascentType, int gradeType, int gradeNumber, long date, int firstAscent) {
        // Gets the database in write mode
        //Create handler to connect to SQLite DB
        DatabaseHelper handler = new DatabaseHelper(this);
        SQLiteDatabase database = handler.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.ClimbLogEntry.COLUMN_DATE, date);
        values.put(DatabaseContract.ClimbLogEntry.COLUMN_NAME, routeName);
        values.put(DatabaseContract.ClimbLogEntry.COLUMN_GRADETYPECODE, gradeType);
        values.put(DatabaseContract.ClimbLogEntry.COLUMN_GRADECODE, gradeNumber);
        values.put(DatabaseContract.ClimbLogEntry.COLUMN_ASCENTTYPECODE, ascentType);
        values.put(DatabaseContract.ClimbLogEntry.COLUMN_LOCATION, locationName);
        values.put(DatabaseContract.ClimbLogEntry.COLUMN_FIRSTASCENTCODE, firstAscent);
        values.put(DatabaseContract.ClimbLogEntry.COLUMN_LOGTAG, DatabaseContract.ClimbLogEntry.LOGTAG_CLIMB);

        long newRowId = database.insert(DatabaseContract.ClimbLogEntry.TABLE_NAME, null, values);
        return newRowId;
    }

    //update result information to database
    private long updateData(String routeName, String locationName, int ascentType, int gradeType, int gradeNumber, long date, int firstAscent, int rowID) {
        // Gets the database in write mode
        //Create handler to connect to SQLite DB
        DatabaseHelper handler = new DatabaseHelper(this);
        SQLiteDatabase database = handler.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.ClimbLogEntry.COLUMN_DATE, date);
        values.put(DatabaseContract.ClimbLogEntry.COLUMN_NAME, routeName);
        values.put(DatabaseContract.ClimbLogEntry.COLUMN_GRADETYPECODE, gradeType);
        values.put(DatabaseContract.ClimbLogEntry.COLUMN_GRADECODE, gradeNumber);
        values.put(DatabaseContract.ClimbLogEntry.COLUMN_ASCENTTYPECODE, ascentType);
        values.put(DatabaseContract.ClimbLogEntry.COLUMN_LOCATION, locationName);
        values.put(DatabaseContract.ClimbLogEntry.COLUMN_FIRSTASCENTCODE, firstAscent);
        values.put(DatabaseContract.ClimbLogEntry.COLUMN_LOGTAG, DatabaseContract.ClimbLogEntry.LOGTAG_CLIMB);

        String whereClauseFive = DatabaseContract.ClimbLogEntry._ID + "=?";
        String[] whereValueFive = {String.valueOf(rowID)};

        long newRowId = database.update(DatabaseContract.ClimbLogEntry.TABLE_NAME, values, whereClauseFive, whereValueFive);
        return newRowId;
    }
}

