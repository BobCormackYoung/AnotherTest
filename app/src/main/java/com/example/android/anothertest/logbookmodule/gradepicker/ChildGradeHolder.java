package com.example.android.anothertest.logbookmodule.gradepicker;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.anothertest.R;
import com.example.android.anothertest.data.DatabaseContract;
import com.example.android.anothertest.data.DatabaseHelper;

/**
 * Created by Bobek on 11/02/2018.
 */

public class ChildGradeHolder extends AppCompatActivity {

    private static final String TAG = "ChildGradeHolder";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_list);

        Intent selectorIntent = getIntent();
        int selectorID = selectorIntent.getIntExtra("selector", 0);

        //Create handler to connect to SQLite DB
        DatabaseHelper handler = new DatabaseHelper(this);
        SQLiteDatabase database = handler.getWritableDatabase();

        Log.i(TAG, "SELECT * FROM " + DatabaseContract.GradeListEntry.TABLE_NAME + " where " + DatabaseContract.GradeListEntry.COLUMN_GRADETYPECODE + "=" + selectorID);
        Cursor cursor = database.rawQuery("SELECT * FROM " + DatabaseContract.GradeListEntry.TABLE_NAME + " where " + DatabaseContract.GradeListEntry.COLUMN_GRADETYPECODE + "=" + selectorID, null);
        Log.i(TAG, "Count: " + cursor.getCount());

        ChildGradeAdapter childAdapter = new ChildGradeAdapter(this, cursor);

        ListView childListView = (ListView) findViewById(R.id.child_list);

        childListView.setAdapter(childAdapter);

        childListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Log.i(TAG, Long.toString(id));

                int output = (int) id;

                Intent outputIntent = new Intent();
                outputIntent.putExtra("OutputData", output);
                setResult(RESULT_OK, outputIntent);
                finish();

            }
        });

    }
}

