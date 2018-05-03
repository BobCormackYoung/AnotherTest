package com.example.android.anothertest.calendarmodule;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.android.anothertest.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalendarPagerAdapter extends PagerAdapter {

    private final String LOG_TAG = getClass().getSimpleName();
    ;
    private final int LOG_SWITCH = 1; //1=log, 0=no log
    private final int CALENDAR_SIZE = CalendarOverview.CALENDAR_SIZE;
    private Context mContext;
    private CalendarProperties mCalendarProperties;
    private GridView mGridView;

    public CalendarPagerAdapter(Context context, CalendarProperties calendarProperties) {
        mContext = context;
        mCalendarProperties = calendarProperties;
    }

    private static String convertDate(Calendar calendar, String dateFormat) {
        long dateInMilliseconds = calendar.getTimeInMillis();
        return DateFormat.format(dateFormat, dateInMilliseconds).toString();
    }

    @Override
    public int getCount() {
        return CALENDAR_SIZE;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        //return false;
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mGridView = (GridView) inflater.inflate(R.layout.calendar_gridview, null);

        //mGridView.setOnItemClickListener(new DayRowClickListener(this, mContext, mCalendarProperties));

        loadMonth(position);

        container.addView(mGridView);
        return mGridView;
    }

    public void loadMonth(int position) {
        ArrayList<Date> days = new ArrayList<>();

        // Get Calendar object instance
        Calendar calendar = (Calendar) mCalendarProperties.getCurrentDate().clone();

        // Add months to Calendar (a number of months depends on ViewPager position)
        int initialPosition = mCalendarProperties.getInitialPosition();
        calendar.add(Calendar.MONTH, (position - initialPosition));

        // Set day of month as 1
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        // Get a number of the first day of the week
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        // Count when month is beginning
        int monthBeginningCell = dayOfWeek + (dayOfWeek == 1 ? 5 : -2);

        // Subtract a number of beginning days, it will let to load a part of a previous month
        calendar.add(Calendar.DAY_OF_MONTH, -monthBeginningCell);

        /*
        Get all days of one page (42 is a number of all possible cells in one page
        (a part of previous month, current month and a part of next month))
         */
        while (days.size() < 42) {
            days.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        if (LOG_SWITCH == 1) {
            Calendar debugCurrentDate = (Calendar) mCalendarProperties.getCurrentDate().clone();
            Calendar debugCurrentDate2 = (Calendar) mCalendarProperties.getCurrentDate().clone();
            debugCurrentDate2.add(Calendar.MONTH, position - initialPosition);
            Log.i(LOG_TAG, "Position: " + position + " of " + CALENDAR_SIZE);
            Log.i(LOG_TAG, "debug current date: " + convertDate(debugCurrentDate, "dd-MM-yyyy"));
            Log.i(LOG_TAG, "adapter current date: " + convertDate(debugCurrentDate2, "dd-MM-yyyy"));
        }

        CalendarGridAdapter calendarGridAdapter = new CalendarGridAdapter(mContext, mCalendarProperties, days, calendar.get(Calendar.MONTH) - 1);

        mGridView.setAdapter(calendarGridAdapter);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
