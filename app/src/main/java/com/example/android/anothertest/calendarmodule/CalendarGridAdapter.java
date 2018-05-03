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
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarGridAdapter extends ArrayAdapter<Date> {

    private final String LOG_TAG = getClass().getSimpleName();
    private final int LOG_SWITCH = 1; //1=log, 0=no log

    private LayoutInflater mLayoutInflater;
    private int mMonth;
    private CalendarProperties mCalendarProperties;

    CalendarGridAdapter(Context context, CalendarProperties calendarProperties, ArrayList<Date> dates, int month) {
        super(context, calendarProperties.getItemLayoutResource(), dates);

        //Log.i(LOG_TAG,"in constructor");


        mCalendarProperties = calendarProperties;
        mMonth = month < 0 ? 11 : month;
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

        TextView dayLabel = (TextView) view.findViewById(R.id.tv_daynumber);

        Calendar day = new GregorianCalendar();
        day.setTime(getItem(position));

        dayLabel.setText(String.valueOf(day.get(Calendar.DAY_OF_MONTH)));

        if (isCurrentMonthDay(day) == true) {
            dayLabel.setTypeface(Typeface.DEFAULT, 0);
        } else {
            dayLabel.setTypeface(Typeface.DEFAULT, 2);
        }

        return view;
    }

    private boolean isCurrentMonthDay(Calendar day) {
        return day.get(Calendar.MONTH) == mMonth;
    }

}
