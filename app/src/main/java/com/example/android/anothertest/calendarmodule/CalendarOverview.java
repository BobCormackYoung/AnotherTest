package com.example.android.anothertest.calendarmodule;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.android.anothertest.R;

public class CalendarOverview extends AppCompatActivity {

    public static final int CALENDAR_SIZE = 2401; //total calendar size (100years either side)
    public static final int CALENDAR_MID_PAGE = CALENDAR_SIZE / 2; //find location of the mid-page
    private final String LOG_TAG = getClass().getSimpleName();
    public TextView tvPosition;
    public TextView tvMonth;
    public TextView tvOther;
    private CalendarProperties mCalendarProperties;
    private CalendarPagerAdapter mCalendarPagerAdapter;
    private int mCurrentPage = CALENDAR_MID_PAGE; //set the initial current page to the mid page
    private ViewPager mViewPager;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_overview);

        mContext = this;

        tvPosition = (TextView) findViewById(R.id.tv_position);
        tvMonth = (TextView) findViewById(R.id.tv_month);
        tvOther = (TextView) findViewById(R.id.tv_other);

        mCalendarProperties = new CalendarProperties();
        mCalendarProperties.setCalendarSize(CALENDAR_SIZE);
        mCalendarProperties.setInitialPosition(CALENDAR_MID_PAGE);
        mCalendarProperties.setDisplayedPosition(mCurrentPage);
        mCalendarProperties.setItemLayoutResource(R.layout.calendar_day_layout);

        mViewPager = findViewById(R.id.vp_calendar); //find the viewpager
        mCalendarPagerAdapter = new CalendarPagerAdapter(mContext, mCalendarProperties); //create the new pageradapter
        mViewPager.setAdapter(mCalendarPagerAdapter); //set the pageradapter for our new view pager

        mViewPager.setCurrentItem(CALENDAR_MID_PAGE);

    }
}
