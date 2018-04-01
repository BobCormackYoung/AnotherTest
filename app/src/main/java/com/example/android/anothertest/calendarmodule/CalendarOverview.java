package com.example.android.anothertest.calendarmodule;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.anothertest.R;
import com.example.android.anothertest.util.TimeUtils;

import java.util.Calendar;

public class CalendarOverview extends AppCompatActivity {

    private static Context mContext;
    private static int startingPosition;
    private static int monthOffset;
    private CachingFragmentStatePagerAdapter adapterViewPager;
    private int currentDayOfMonth;
    private int currentMonthOfYear;
    private int currentYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_overview);

        mContext = this;

        //get a calendar instance
        Calendar mCalendar = Calendar.getInstance();
        //get the current day and month
        currentDayOfMonth = mCalendar.get(Calendar.DAY_OF_MONTH);
        currentMonthOfYear = mCalendar.get(Calendar.MONTH);
        currentYear = mCalendar.get(Calendar.YEAR);

        // Initialise viewPager
        final ViewPager vpPager = (ViewPager) findViewById(R.id.calendar_viewpager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);

        // Set pager to current date
        vpPager.setCurrentItem(TimeUtils.getPositionForMonth(Calendar.getInstance()));
        startingPosition = TimeUtils.getPositionForMonth(Calendar.getInstance());

        // Set PageChangeListener
        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    // Pager Adapter
    public static class MyPagerAdapter extends CachingFragmentStatePagerAdapter {

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return TimeUtils.MONTHS_OF_TIME;
        }

        @Override
        public Fragment getItem(int position) {
            if (position < startingPosition) {
                monthOffset = position - startingPosition;
                //monthOffset = monthOffset--;
            } else if (position > startingPosition) {
                monthOffset = position - startingPosition;
                //monthOffset = monthOffset++;
            } else if (position == startingPosition) {
                monthOffset = position - startingPosition;
                //monthOffset = 0;
            }
            Log.i("ViewPager", "SP: " + startingPosition + ", CP: " + position + ", MO: " + monthOffset);
            return CalendarFragmentContent.newInstance(monthOffset);
        }
    }
}
