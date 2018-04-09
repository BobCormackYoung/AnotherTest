package com.example.android.anothertest.calendarmodule;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.example.android.anothertest.R;
import com.example.android.anothertest.data.DatabaseReadWrite;

import java.util.ArrayList;
import java.util.Calendar;

public class CalendarFragmentContent extends Fragment {

    private static final String KEY_MONTH_OFFSET = "month_offset";
    int fragmentMonthOffset;
    int flagToday;
    int flagThisMonth;
    int flagClimb;
    int flagWorkout;
    int flagWorkoutClimb;

    public static CalendarFragmentContent newInstance(int month_offset) {
        CalendarFragmentContent fragmentFirst = new CalendarFragmentContent();
        Bundle args = new Bundle();
        args.putInt(KEY_MONTH_OFFSET, month_offset);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final int month_offset = getArguments().getInt(KEY_MONTH_OFFSET);
        final Context context = getActivity();
        if (context != null) {
            fragmentMonthOffset = month_offset;
            return;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calendar_pager_fragment, container, false);

        int runAsAsyncTask = 0; //1 = run Async, 0 = run on GUI thread

        if (runAsAsyncTask == 1) {
            AsyncTaskRunner runner = new AsyncTaskRunner();
            runner.execute(view);
        } else if (runAsAsyncTask == 0) {
            GUIThreadRunner(view);
        }

        return view;

    }

    private void GUIThreadRunner(View view) {

        final Context context = getActivity();

        //get a calendar instance
        Calendar mCalendar = Calendar.getInstance();
        //mCalendar.add(Calendar.MONTH,1);
        //get the current day and month
        int currentDayOfMonth = mCalendar.get(Calendar.DAY_OF_MONTH);
        int currentMonthOfYear = mCalendar.get(Calendar.MONTH);
        int currentYear = mCalendar.get(Calendar.YEAR);

        //set calendar to first day of the month
        mCalendar.set(Calendar.DAY_OF_MONTH, 1);
        //offset month
        mCalendar.add(Calendar.MONTH, fragmentMonthOffset);
        int monthOfYear = mCalendar.get(Calendar.MONTH);
        int weekOfMonth = mCalendar.get(Calendar.WEEK_OF_MONTH);
        int dayOfWeek = mCalendar.get(Calendar.DAY_OF_WEEK);
        int daysToStartCalendar = (dayOfWeek - 1) + 7;

        Log.i("monthOfYear", "" + monthOfYear);
        Log.i("weekOfMonth", "" + weekOfMonth);
        Log.i("daysToStartCalendar", "" + daysToStartCalendar);

        // Create a list of words
        final ArrayList<MonthDays> monthDays = new ArrayList<MonthDays>();
        mCalendar.add(Calendar.DAY_OF_MONTH, -daysToStartCalendar);

        int nextMonth;
        if (monthOfYear == 11) {
            nextMonth = 0;
        } else {
            nextMonth = monthOfYear + 1;
        }

        for (int counter = 0; counter < 7 * 9; counter++) {
            if (mCalendar.get(Calendar.MONTH) == nextMonth
                    && mCalendar.get(Calendar.WEEK_OF_MONTH) == 2
                    && mCalendar.get(Calendar.DAY_OF_WEEK) == 1) {
                break;
            } else {
                if (mCalendar.get(Calendar.DAY_OF_MONTH) == currentDayOfMonth
                        && mCalendar.get(Calendar.MONTH) == currentMonthOfYear
                        && mCalendar.get(Calendar.YEAR) == currentYear) {
                    flagToday = 1;
                } else {
                    flagToday = 0;
                }

                if (mCalendar.get(Calendar.MONTH) == monthOfYear) {
                    flagThisMonth = 1;
                } else {
                    flagThisMonth = 0;
                }

                //int climbCount = 0; //DatabaseReadWrite.getClimbCount(mCalendar, context);
                //int workoutCount = 0; //DatabaseReadWrite.getWorkoutCount(mCalendar, context);
                //int workoutClimbCount = 0; //DatabaseReadWrite.getWorkoutClimbCount(mCalendar, context);
                int climbCount = DatabaseReadWrite.getClimbCount(mCalendar, context);
                int workoutCount = DatabaseReadWrite.getWorkoutCount(mCalendar, context);
                int workoutClimbCount = DatabaseReadWrite.getWorkoutClimbCount(mCalendar, context);

                if (climbCount != 0) {
                    flagClimb = 1;
                } else {
                    flagClimb = 0;
                }

                if (workoutCount != 0) {
                    flagWorkout = 1;
                } else {
                    flagWorkout = 0;
                }

                if (workoutClimbCount != 0) {
                    flagWorkoutClimb = 1;
                } else {
                    flagWorkoutClimb = 0;
                }


                monthDays.add(new MonthDays(mCalendar.get(Calendar.DAY_OF_MONTH), flagClimb, flagWorkout, flagWorkoutClimb, flagToday, flagThisMonth));
                //step to next day
                mCalendar.add(Calendar.DAY_OF_MONTH, 1);
            }
        }

        GridView gridView = (GridView) view.findViewById(R.id.gridview);
        final CalendarAdapter calendarAdapter = new CalendarAdapter(context, monthDays);
        gridView.setAdapter(calendarAdapter);

        TextView tv1 = view.findViewById(R.id.fragment_tv1);
        TextView tv2 = view.findViewById(R.id.fragment_tv2);
        TextView tv3 = view.findViewById(R.id.fragment_tv3);

        tv1.setText("Start :: Month: " + currentMonthOfYear + ", Year: " + currentYear);
        tv2.setText("Displayed :: Month: " + monthOfYear + ", Year: " + mCalendar.get(Calendar.YEAR));
        tv3.setText("Month Offset: " + fragmentMonthOffset);

    }

    private class AsyncTaskRunner extends AsyncTask<View, Void, Object[]> {

        @Override
        protected Object[] doInBackground(View... views) {
            View view = views[0];

            final Context context = getActivity();

            //get a calendar instance
            Calendar mCalendar = Calendar.getInstance();
            //mCalendar.add(Calendar.MONTH,1);
            //get the current day and month
            int currentDayOfMonth = mCalendar.get(Calendar.DAY_OF_MONTH);
            int currentMonthOfYear = mCalendar.get(Calendar.MONTH);
            int currentYear = mCalendar.get(Calendar.YEAR);

            //set calendar to first day of the month
            mCalendar.set(Calendar.DAY_OF_MONTH, 1);
            //offset month
            mCalendar.add(Calendar.MONTH, fragmentMonthOffset);
            int monthOfYear = mCalendar.get(Calendar.MONTH);
            int weekOfMonth = mCalendar.get(Calendar.WEEK_OF_MONTH);
            int dayOfWeek = mCalendar.get(Calendar.DAY_OF_WEEK);
            int daysToStartCalendar = (dayOfWeek - 1) + 7;

            Log.i("monthOfYear", "" + monthOfYear);
            Log.i("weekOfMonth", "" + weekOfMonth);
            Log.i("daysToStartCalendar", "" + daysToStartCalendar);

            // Create a list of words
            final ArrayList<MonthDays> monthDays = new ArrayList<MonthDays>();
            mCalendar.add(Calendar.DAY_OF_MONTH, -daysToStartCalendar);

            int nextMonth;
            if (monthOfYear == 11) {
                nextMonth = 0;
            } else {
                nextMonth = monthOfYear + 1;
            }

            for (int counter = 0; counter < 100; counter++) {
                if (mCalendar.get(Calendar.MONTH) == nextMonth
                        && mCalendar.get(Calendar.WEEK_OF_MONTH) == 2
                        && mCalendar.get(Calendar.DAY_OF_WEEK) == 1) {
                    break;
                } else {
                    if (mCalendar.get(Calendar.DAY_OF_MONTH) == currentDayOfMonth
                            && mCalendar.get(Calendar.MONTH) == currentMonthOfYear
                            && mCalendar.get(Calendar.YEAR) == currentYear) {
                        flagToday = 1;
                    } else {
                        flagToday = 0;
                    }

                    if (mCalendar.get(Calendar.MONTH) == monthOfYear) {
                        flagThisMonth = 1;
                    } else {
                        flagThisMonth = 0;
                    }

                    int climbCount = DatabaseReadWrite.getClimbCount(mCalendar, context); //0; //
                    int workoutCount = DatabaseReadWrite.getWorkoutCount(mCalendar, context); //0; //
                    int workoutClimbCount = DatabaseReadWrite.getWorkoutClimbCount(mCalendar, context); //0; //

                    if (climbCount != 0) {
                        flagClimb = 1;
                    } else {
                        flagClimb = 0;
                    }

                    if (workoutCount != 0) {
                        flagWorkout = 1;
                    } else {
                        flagWorkout = 0;
                    }

                    if (workoutClimbCount != 0) {
                        flagWorkoutClimb = 1;
                    } else {
                        flagWorkoutClimb = 0;
                    }


                    monthDays.add(new MonthDays(mCalendar.get(Calendar.DAY_OF_MONTH), flagClimb, flagWorkout, flagWorkoutClimb, flagToday, flagThisMonth));
                    //step to next day
                    mCalendar.add(Calendar.DAY_OF_MONTH, 1);
                }
            }

            Object[] output = new Object[7];
            output[0] = view;
            output[1] = context;
            output[2] = monthDays;
            output[3] = currentMonthOfYear;
            output[4] = currentYear;
            output[5] = monthOfYear;
            output[6] = mCalendar;

            return output;
        }

        @Override
        protected void onPostExecute(Object[] output) {
            View view = (View) output[0];
            Context context = (Context) output[1];
            ArrayList<MonthDays> monthDays = (ArrayList<MonthDays>) output[2];
            int currentMonthOfYear = (int) output[3];
            int currentYear = (int) output[4];
            int monthOfYear = (int) output[5];
            Calendar mCalendar = (Calendar) output[6];

            GridView gridView = (GridView) view.findViewById(R.id.gridview);
            final CalendarAdapter calendarAdapter = new CalendarAdapter(context, monthDays);
            gridView.setAdapter(calendarAdapter);

            TextView tv1 = view.findViewById(R.id.fragment_tv1);
            TextView tv2 = view.findViewById(R.id.fragment_tv2);
            TextView tv3 = view.findViewById(R.id.fragment_tv3);

            tv1.setText("Start :: Month: " + currentMonthOfYear + ", Year: " + currentYear);
            tv2.setText("Displayed :: Month: " + monthOfYear + ", Year: " + mCalendar.get(Calendar.YEAR));
            tv3.setText("Month Offset: " + fragmentMonthOffset);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
