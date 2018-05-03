package com.example.android.anothertest.calendarmodule;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.anothertest.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarGridAdapter extends ArrayAdapter<MonthDays> {

    private final String LOG_TAG = getClass().getSimpleName();
    private final int LOG_SWITCH = 1; //1=log, 0=no log

    private LayoutInflater mLayoutInflater;
    private int mMonth;
    private CalendarProperties mCalendarProperties;

    CalendarGridAdapter(Context context, CalendarProperties calendarProperties, ArrayList<MonthDays> dates, int month) {
        super(context, calendarProperties.getItemLayoutResource(), dates);

        mCalendarProperties = calendarProperties;
        if (month < 0) {
            mMonth = 11; //December = is month -1
        } else {
            mMonth = month; //all other months are correct
        }
        //mMonth = month < 0 ? 11 : month;
        mLayoutInflater = LayoutInflater.from(context);

        if (LOG_SWITCH == 1) {
            Log.i(LOG_TAG, "Month: " + month + ", mMonth: " + mMonth);
        }
    }

    @NonNull
    @Override
    public View getView(int position, View view, @NonNull ViewGroup parent) {

        //Log.i(LOG_TAG,"in getView");

        if (view == null) {
            view = mLayoutInflater.inflate(mCalendarProperties.getItemLayoutResource(), parent, false);
        }

        MonthDays monthDay = getItem(position);

        TextView dayLabel = (TextView) view.findViewById(R.id.tv_daynumber);
        TextView climbTextView = (TextView) view.findViewById(R.id.calendar_climb_symbol);
        TextView workoutTextView = (TextView) view.findViewById(R.id.calendar_workout_symbol);
        TextView workoutClimbTextView = (TextView) view.findViewById(R.id.calendar_workoutclimb_symbol);

        Calendar day = new GregorianCalendar();
        day.setTime(monthDay.getDate());

        dayLabel.setText(String.valueOf(day.get(Calendar.DAY_OF_MONTH)));

        if (isCurrentMonthDay(day) == true) {
            dayLabel.setTypeface(Typeface.DEFAULT, 0);
        } else {
            dayLabel.setTypeface(Typeface.DEFAULT, 2);
        }

        if (monthDay.getFlagClimbing() == 0) {
            climbTextView.setVisibility(View.GONE);
        } else {
            climbTextView.setVisibility(View.VISIBLE);
        }

        if (monthDay.getFlagWorkout() == 0) {
            workoutTextView.setVisibility(View.GONE);
        } else {
            workoutTextView.setVisibility(View.VISIBLE);
        }

        if (monthDay.getFlagWorkoutClimb() == 0) {
            workoutClimbTextView.setVisibility(View.GONE);
        } else {
            workoutClimbTextView.setVisibility(View.VISIBLE);
        }

        return view;
    }

    private boolean isCurrentMonthDay(Calendar day) {
        return day.get(Calendar.MONTH) == mMonth;
    }

}
