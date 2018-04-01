package com.example.android.anothertest.calendarmodule;

/**
 * Created by Bobek on 27/03/2018.
 */

public class MonthDays {

    private int mDate;
    private int mFlagClimbing;
    private int mFlagWorkout;
    private int mFlagWorkoutClimb;
    private int mFlagCurrentDay;
    private int mFlagCurrentMonth;

    public MonthDays(int date, int flagClimbing, int flagWorkout, int flagWorkoutClimb, int flagCurrentDay, int flagCurrentMonth) {
        mDate = date;
        mFlagClimbing = flagClimbing;
        mFlagWorkout = flagWorkout;
        mFlagWorkoutClimb = flagWorkoutClimb;
        mFlagCurrentDay = flagCurrentDay;
        mFlagCurrentMonth = flagCurrentMonth;
    }

    public int getDate() {
        return mDate;
    }

    public int getFlagClimbing() {
        return mFlagClimbing;
    }

    public int getFlagWorkout() {
        return mFlagWorkout;
    }

    public int getFlagWorkoutClimb() {
        return mFlagWorkoutClimb;
    }

    public int getFlagCurrentDay() {
        return mFlagCurrentDay;
    }

    public int getFlagCurrentMonth() {
        return mFlagCurrentMonth;
    }

}
