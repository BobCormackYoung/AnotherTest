package com.example.android.anothertest.logbookmodule;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
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

        // check if climb or training
        int logTag = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.CalendarTrackerEntry.COLUMN_ISCLIMB));
        // get the foreign key row ID
        int rowID = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.CalendarTrackerEntry.COLUMN_ROWID));

        if (logTag == DatabaseContract.ClimbLogEntry.IS_CLIMB) {
            Bundle bundle = DatabaseReadWrite.EditClimbLoadEntry(rowID, context);

            titleText = bundle.getString("outputRouteName");
            info1Text = bundle.getString("outputLocationName");
            int firstAscentCode = bundle.getInt("outputFirstAscent");
            if (firstAscentCode == DatabaseContract.ClimbLogEntry.FIRSTASCENT_TRUE) {
                info3Text = "First Ascent";
            } else {
                info3Text = "Repeat Ascent";
            }
            int gradeCode = bundle.getInt("outputGradeName");
            gradeText = DatabaseReadWrite.getGradeTextClimb(gradeCode, context);
            int outputAscent = bundle.getInt("outputAscent");
            info2Text = DatabaseReadWrite.getAscentNameTextClimb(outputAscent, context);

        } else if (logTag == DatabaseContract.WorkoutLogEntry.IS_WORKOUT) {
            Bundle bundle = DatabaseReadWrite.EditWorkoutLoadEntry(rowID, context);
            //Item is a training session, not a climb
            int workoutCode = bundle.getInt("outputWorkoutCode");
            titleText = DatabaseReadWrite.getWorkoutTextClimb(workoutCode, context);
            info1Text = "Sets x Reps: " + bundle.getInt("outputSetCount") + "x" + bundle.getInt("outputRepCount");
            info2Text = "Additional Weight: " + bundle.getInt("outputWeight") + " Kg";
            info3Text = "...";
            gradeText = "WK";
            //TODO: add logic so that correct information is displayed for this item
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
        if (logTag == DatabaseContract.ClimbLogEntry.IS_CLIMB) {
            // Find the color that the resource ID maps to
            int color = ContextCompat.getColor(context, R.color.colorTrainingItems);
            // Set the background color of the text container View
            //textContainer.setBackgroundColor(color);
            textContainer.setTextColor(color);
        } else if (logTag == DatabaseContract.WorkoutLogEntry.IS_WORKOUT) {
            // Find the color that the resource ID maps to
            int color = ContextCompat.getColor(context, R.color.colorClimbingItems);
            // Set the background color of the text container View
            //textContainer.setBackgroundColor(color);
            textContainer.setTextColor(color);
        }
    }


}


