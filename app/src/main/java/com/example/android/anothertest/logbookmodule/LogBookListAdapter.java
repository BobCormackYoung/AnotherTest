package com.example.android.anothertest.logbookmodule;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.anothertest.R;
import com.example.android.anothertest.data.DatabaseContract;
import com.example.android.anothertest.data.DatabaseHelper;

/**
 * Created by Bobek on 12/11/2017.
 */

public class LogBookListAdapter extends CursorAdapter {

    /** Resource ID for the background color for this list of words */
    private int mTrainingColorResourceId;
    private int mClimbingColorResourceId;

    public LogBookListAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.log_book_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        String titleText = null;
        String gradeText = null;
        String info1Text = null;
        String info2Text = null;
        String info3Text = null;

        DatabaseHelper handler = new DatabaseHelper(context);
        SQLiteDatabase database = handler.getWritableDatabase();

        //check if climb or training
        int logTag = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.ClimbLogEntry.COLUMN_LOGTAG));
        if (logTag == DatabaseContract.ClimbLogEntry.LOGTAG_CLIMB) {
            //Item is a climb, not a training session
            titleText = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.ClimbLogEntry.COLUMN_NAME));
            int gradeCode = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.ClimbLogEntry.COLUMN_GRADECODE));
            gradeText = getGradeTextClimb(gradeCode, database);
            info1Text = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.ClimbLogEntry.COLUMN_LOCATION));
            int info2Code = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.ClimbLogEntry.COLUMN_ASCENTTYPECODE));
            info2Text = getInfo2TextClimb(info2Code, database);
            int firstAscentCode = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.ClimbLogEntry.COLUMN_FIRSTASCENTCODE));
            if (firstAscentCode == DatabaseContract.ClimbLogEntry.FIRSTASCENT_TRUE) {
                info3Text = "First Ascent";
            } else {
                info3Text = "Repeat Ascent";
            }
        }

        // Find the TextView in the list_item.xml layout with the ID miwok_text_view.
        TextView titleTextView = (TextView) view.findViewById(R.id.log_book_list_item_title);
        titleTextView.setText(titleText);

        // Find the TextView in the list_item.xml layout with the ID default_text_view.
        TextView gradeTextView = (TextView) view.findViewById(R.id.log_book_list_item_icon);
        gradeTextView.setText(gradeText);

        // Find the TextView in the list_item.xml layout with the ID default_text_view.
        TextView info1TextView = (TextView) view.findViewById(R.id.log_book_list_item_info1);
        info1TextView.setText(info1Text);

        // Find the TextView in the list_item.xml layout with the ID default_text_view.
        TextView info2TextView = (TextView) view.findViewById(R.id.log_book_list_item_info2);
        info2TextView.setText(info2Text);

        // Find the TextView in the list_item.xml layout with the ID default_text_view.
        TextView info3TextView = (TextView) view.findViewById(R.id.log_book_list_item_info3);
        info3TextView.setText(info3Text);

        //View textContainer = listItemView.findViewById(R.id.log_book_list_item_wrapper);
        TextView textContainer = (TextView) view.findViewById(R.id.log_book_list_item_icon);
        if (logTag == DatabaseContract.ClimbLogEntry.LOGTAG_CLIMB) {
            // Find the color that the resource ID maps to
            int color = ContextCompat.getColor(context, R.color.colorTrainingItems);
            // Set the background color of the text container View
            //textContainer.setBackgroundColor(color);
            textContainer.setTextColor(color);
        } else {
            // Find the color that the resource ID maps to
            int color = ContextCompat.getColor(context, R.color.colorClimbingItems);
            // Set the background color of the text container View
            //textContainer.setBackgroundColor(color);
            textContainer.setTextColor(color);
        }
    }

    private String getInfo2TextClimb(int info2Code, SQLiteDatabase database) {
        //ascent type
        String[] projection = {
                DatabaseContract.AscentEntry._ID,
                DatabaseContract.AscentEntry.COLUMN_ASCENTTYPENAME};
        String whereClause = DatabaseContract.AscentEntry._ID + "=?";
        String[] whereValue = {String.valueOf(info2Code)};

        Cursor cursor = database.query(DatabaseContract.AscentEntry.TABLE_NAME,
                projection,
                whereClause,
                whereValue,
                null,
                null,
                null);

        int idColumnOutput = cursor.getColumnIndex(DatabaseContract.AscentEntry.COLUMN_ASCENTTYPENAME);

        cursor.moveToFirst();
        String outputString = cursor.getString(idColumnOutput);
        cursor.close();
        return outputString;
    }

    private String getGradeTextClimb(int gradeCode, SQLiteDatabase database) {
        //ascent type
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

        cursor.moveToFirst();
        String outputString = cursor.getString(idColumnOutput);
        cursor.close();
        return outputString;
    }
}


