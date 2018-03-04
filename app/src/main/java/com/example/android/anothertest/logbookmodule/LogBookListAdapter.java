package com.example.android.anothertest.logbookmodule;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.anothertest.R;
import com.example.android.anothertest.data.DatabaseContract;
import com.example.android.anothertest.data.DatabaseReadWrite;

/**
 * Created by Bobek on 12/11/2017.
 */

public class LogBookListAdapter extends CursorAdapter {

    Context mContext;
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

        //check if climb or training
        int logTag = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.ClimbLogEntry.COLUMN_LOGTAG));
        if (logTag == DatabaseContract.ClimbLogEntry.LOGTAG_CLIMB) {
            //Item is a climb, not a training session
            titleText = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.ClimbLogEntry.COLUMN_NAME));
            int gradeCode = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.ClimbLogEntry.COLUMN_GRADECODE));
            gradeText = DatabaseReadWrite.getGradeTextClimb(gradeCode, context);
            info1Text = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.ClimbLogEntry.COLUMN_LOCATION));
            int info2Code = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.ClimbLogEntry.COLUMN_ASCENTTYPECODE));
            info2Text = DatabaseReadWrite.getAscentNameTextClimb(info2Code, context);
            int firstAscentCode = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.ClimbLogEntry.COLUMN_FIRSTASCENTCODE));
            if (firstAscentCode == DatabaseContract.ClimbLogEntry.FIRSTASCENT_TRUE) {
                info3Text = "First Ascent";
            } else {
                info3Text = "Repeat Ascent";
            }
        }

        TextView titleTextView = (TextView) view.findViewById(R.id.log_book_list_item_title);
        titleTextView.setText(titleText);

        TextView gradeTextView = (TextView) view.findViewById(R.id.log_book_list_item_icon);
        gradeTextView.setText(gradeText);

        TextView info1TextView = (TextView) view.findViewById(R.id.log_book_list_item_info1);
        info1TextView.setText(info1Text);

        TextView info2TextView = (TextView) view.findViewById(R.id.log_book_list_item_info2);
        info2TextView.setText(info2Text);

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


}


