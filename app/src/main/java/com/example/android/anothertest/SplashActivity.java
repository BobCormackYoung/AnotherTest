package com.example.android.anothertest;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.android.anothertest.calendarmodule.CalendarOverview;
import com.example.android.anothertest.data.DatabaseHelper;
import com.example.android.anothertest.logbookmodule.LogBook;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends AppCompatActivity {

    public static final String LOG_TAG = "SplashActivity";

    /** Database helper that will provide us access to the database */
    private DatabaseHelper mDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_splash);

        View button1 = findViewById(R.id.button1);
        View button2 = findViewById(R.id.button2);
        View button3 = findViewById(R.id.button3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new intent to open the {@link FamilyActivity}
                Intent DailyViewIntent = new Intent(SplashActivity.this, LogBook.class);
                // Start the new activity
                startActivity(DailyViewIntent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new intent to open the {@link FamilyActivity}
                Intent CalendarViewIntent = new Intent(SplashActivity.this, CalendarOverview.class);
                // Start the new activity
                startActivity(CalendarViewIntent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new intent to open the {@link FamilyActivity}
                Intent DailyViewIntent = new Intent(SplashActivity.this, LogBook.class);
                // Start the new activity
                startActivity(DailyViewIntent);
            }
        });

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        db.close();

    }

}
