package com.example.android.anothertest.data;

import android.provider.BaseColumns;

/**
 * Created by Bobek on 16/01/2018.
 */

public final class DatabaseContract {

    private DatabaseContract() {}

    public static final class AscentEntry implements BaseColumns{


        public final static String TABLE_NAME = "AscentType";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_ASCENTTYPENAME = "AscentTypeName";
        public final static String COLUMN_DESCRIPTION = "Description";

    }

    public final static class CalendarTrackerEntry implements BaseColumns{

        public final static String TABLE_NAME = "CalendarTracker";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_DATE = "Date";
        public final static String COLUMN_WORKOUTCOUNT = "WorkoutCount";
        public final static String COLUMN_CLIMBCOUNT = "ClimbCount";

    }

    public final static class ClimbLogEntry implements BaseColumns{

        public final static String TABLE_NAME = "ClimbLog";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_DATE = "Date";
        public final static String COLUMN_NAME = "Name";
        public final static String COLUMN_GRADETYPECODE = "GradeTypeCode";
        public final static String COLUMN_GRADECODE = "GradeCode";
        public final static String COLUMN_ASCENTTYPECODE = "AscentTypeCode";
        public final static String COLUMN_LOCATION = "Location";
        public final static String COLUMN_FIRSTASCENTCODE = "FirstAscentCode";
        public final static String COLUMN_ISCLIMB = "IsClimb";

        public final static int IS_TRUE = 1;
        public final static int FIRSTASCENT_TRUE = 1;
        public final static int FIRSTASCENT_FALSE = 0;
    }

    public final static class GradeListEntry implements BaseColumns{

        public final static String TABLE_NAME = "GradeList";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_GRADETYPECODE = "GradeTypeCode";
        public final static String COLUMN_GRADENAME = "GradeName";
        public final static String COLUMN_RELATIVEDIFFICULTY = "RelativeDifficulty";

    }

    public final static class GradeTypeEntry implements BaseColumns{

        public final static String TABLE_NAME = "GradeType";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_GRADETYPENAME = "GradeTypeName";

    }

    public final static class WorkoutLogEntry implements BaseColumns {

        public final static String TABLE_NAME = "WorkoutLog";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_DATE = "Date";
        public final static String COLUMN_WORKOUTTYPECODE = "WorkoutTypeCode";
        public final static String COLUMN_WORKOUTCODE = "WorkoutCode";
        public final static String COLUMN_ISCLIMB = "IsClimbCode";
        public final static String COLUMN_WEIGHT = "Weight";
        public final static String COLUMN_SETCOUNT = "SetCount";
        public final static String COLUMN_REPCOUNTPERSET = "RepCountPerSet";
        public final static String COLUMN_REPDURATIONPERSET = "RepDurationPerSet";
        public final static String COLUMN_RESTDURATIONPERSET = "RestDurationPerSet";
        public final static String COLUMN_GRADETYPECODE = "GradeTypeCode";
        public final static String COLUMN_GRADECODE = "GradeCode";
        public final static String COLUMN_ISCOMPLETE = "IsComplete";

        public final static int IS_TRUE = 1;
        public final static int IS_FALSE = 0;

    }

    public final static class WorkoutListEntry implements BaseColumns {

        public final static String TABLE_NAME = "WorkoutList";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME = "Name";
        public final static String COLUMN_WORKOUTTYPECODE = "WorkoutTypeCode";
        public final static String COLUMN_DESCRIPTION = "Description";
        public final static String COLUMN_ISCLIMB = "IsClimbCode";
        public final static String COLUMN_ISWEIGHT = "IsWeight";
        public final static String COLUMN_ISSETCOUNT = "IsSetCount";
        public final static String COLUMN_ISREPCOUNTPERSET = "IsRepCountPerSet";
        public final static String COLUMN_ISREPDURATIONPERSET = "IsRepDurationPerSet";
        public final static String COLUMN_ISRESTDURATIONPERSET = "IsRestDurationPerSet";
        public final static String COLUMN_ISGRADECODE = "IsGradeCode";

        public final static int IS_TRUE = 1;
        public final static int IS_FALSE = 0;

    }

    public final static class WorkoutTypeEntry implements BaseColumns {

        public final static String TABLE_NAME = "WorkoutType";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_WORKOUTTYPENAME = "Name";
        public final static String COLUMN_DESCRIPTION = "Description";

    }

}
