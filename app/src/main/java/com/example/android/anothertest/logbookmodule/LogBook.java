package com.example.android.anothertest.logbookmodule;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.anothertest.R;
import com.example.android.anothertest.data.DatabaseContract;
import com.example.android.anothertest.data.DatabaseHelper;

import java.util.Calendar;

public class LogBook extends AppCompatActivity {

    final int ADD_CLIMB_NEW = 0;
    final int ADD_CLIMB_EDIT = 1;
    DatabaseHelper handler;
    SQLiteDatabase database;
    LogBookListAdapter adapter;
    long dayPeriod = 86400000;
    long currentDateStart;
    long currentDateEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_book);

        long currentDate = Calendar.getInstance().getTimeInMillis();
        currentDateStart = currentDate - millisToStartOfDay();
        currentDateEnd = currentDateStart + dayPeriod;

        //Create handler to connect to SQLite DB
        handler = new DatabaseHelper(this);
        database = handler.getWritableDatabase();
        //Cursor cursor = database.rawQuery("SELECT  * FROM " + DatabaseContract.ClimbLogEntry.TABLE_NAME, null);
        Cursor cursor = getCursorBetweenDates(currentDateStart, currentDateEnd, database);

        adapter = new LogBookListAdapter(this, cursor);
        ListView listView = (ListView) findViewById(R.id.log_book_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Create a new intent to open the {@link FamilyActivity}
                Intent editClimbIntent = new Intent(LogBook.this, AddClimb.class);
                editClimbIntent.putExtra("EditOrNewFlag", ADD_CLIMB_EDIT);
                editClimbIntent.putExtra("RowID", (int) id);
                // Start the new activity
                Log.i("TAG ME UP", "OnItemClick " + (int) id + " " + String.valueOf(ADD_CLIMB_EDIT));
                startActivity(editClimbIntent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View v, int position, long id) {

                AlertDialog.Builder deleteAlert = deleteDialog(id);

                deleteAlert.show();

                return true;
            }
        });

        //TODO: way for scrolling between days

        View button_previous_day = findViewById(R.id.button_previous_day);
        View button_next_day = findViewById(R.id.button_next_day);
        View button_add_workout = findViewById(R.id.button_add_workout);
        View button_log_climb = findViewById(R.id.button_log_climb);

        button_previous_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new intent to open the {@link FamilyActivity}
                // ...Intent TrainingLogIntent = new Intent(LogBook.this, LogBook.class);
                // Start the new activity
                // ...startActivity(TrainingLogIntent);
            }
        });

        button_next_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new intent to open the {@link FamilyActivity}
                // ...Intent TrainingLogIntent = new Intent(LogBook.this, LogBook.class);
                // Start the new activity
                // ...startActivity(TrainingLogIntent);
            }
        });

        button_add_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new intent to open the {@link FamilyActivity}
                Intent AddWorkoutIntent = new Intent(LogBook.this, AddWorkout.class);
                // Start the new activity
                startActivity(AddWorkoutIntent);
            }
        });

        button_log_climb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new intent to open the {@link FamilyActivity}
                Intent AddClimbIntent = new Intent(LogBook.this, AddClimb.class);
                AddClimbIntent.putExtra("EditOrNewFlag", ADD_CLIMB_NEW);
                AddClimbIntent.putExtra("RowID", -1);
                // Start the new activity
                startActivity(AddClimbIntent);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        refreshCursor();
    }

    public void refreshCursor() {
        //Cursor cursorNew = database.rawQuery("SELECT  * FROM " + DatabaseContract.ClimbLogEntry.TABLE_NAME, null);
        Cursor cursorNew = getCursorBetweenDates(currentDateStart, currentDateEnd, database);
        adapter.changeCursor(cursorNew);
    }

    public AlertDialog.Builder deleteDialog(final long id) {

        AlertDialog.Builder alert = new AlertDialog.Builder(LogBook.this);
        //alert.setTitle("Alert!!");
        alert.setMessage("Are you sure you wish to delete this ");
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String table = DatabaseContract.ClimbLogEntry.TABLE_NAME;
                String whereClause = "_id=?";
                String[] whereArgs = new String[]{String.valueOf(id)};
                database.delete(table, whereClause, whereArgs);

                Toast.makeText(LogBook.this, "Clicked Yes, deleting " + id, Toast.LENGTH_LONG).show();

                refreshCursor();

                dialog.dismiss();
            }
        });
        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(LogBook.this, "Clicked No", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });

        return (alert);

    }

    public Cursor getCursorBetweenDates(long dateStart, long dateEnd, SQLiteDatabase db) {

        Log.d("getCursorBetweenDates", "dateStart = " + dateStart + " , dateEnd = " + dateEnd);
/*        String[] projection = {
                DatabaseContract.ClimbLogEntry._ID,
                DatabaseContract.ClimbLogEntry.COLUMN_DATE,
                DatabaseContract.ClimbLogEntry.COLUMN_NAME,
                DatabaseContract.ClimbLogEntry.COLUMN_GRADETYPECODE,
                DatabaseContract.ClimbLogEntry.COLUMN_GRADECODE,
                DatabaseContract.ClimbLogEntry.COLUMN_ASCENTTYPECODE,
                DatabaseContract.ClimbLogEntry.COLUMN_LOCATION,
                DatabaseContract.ClimbLogEntry.COLUMN_FIRSTASCENTCODE,
                DatabaseContract.ClimbLogEntry.COLUMN_LOGTAG};
        String whereClause = DatabaseContract.ClimbLogEntry.COLUMN_DATE + " BETWEEN ? AND ?";
        String[] whereValue = {String.valueOf(dateStart),
                String.valueOf(dateEnd)};

        Cursor cursor = db.query(DatabaseContract.ClimbLogEntry.TABLE_NAME,
                projection,
                whereClause,
                whereValue,
                null,
                null,
                null);*/

        Cursor cursor = db.rawQuery("select * from " + DatabaseContract.ClimbLogEntry.TABLE_NAME + " where " + DatabaseContract.ClimbLogEntry.COLUMN_DATE + " BETWEEN '" + dateStart + "' AND '" + dateEnd + "' ORDER BY Date ASC", null);

        return cursor;
    }

    public long millisToStartOfDay() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        long millis = (System.currentTimeMillis() - c.getTimeInMillis());
        return millis;
    }

}
