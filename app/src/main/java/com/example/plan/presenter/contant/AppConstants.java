package com.example.plan.presenter.contant;

public class AppConstants {

    public class DataType{
        public static final int LIST_PLAN = 0;
        public static final int REMIND = 1;
        public static final int TASK = 2;
        public static final int REPEAT_ME = 3;
    }

    public class RepeatType {

        // repeat type
/*
        public static final int DAY_LY = 0;
        public static final int WEEK_DAYS = 1;
        public static final int WEEK_LY = 2;
        public static final int MONTH_LY = 3;
        public static final int YEAR_LY = 4;
        public static final int CUSTOM = 5;

        // repeat type with custom
*/

        public static final int REPEAT_MONDAY = 0;
        public static final int REPEAT_TUEDAY = 1;
        public static final int REPEAT_WEDNESDAY = 2;
        public static final int REPEAT_THURDAY = 3;
        public static final int REPEAT_FRIDAY = 4;
        public static final int REPEAT_SATURDAY = 5;
        public static final int REPEAT_SUNDAY = 6;
    }

    public class DefaultListPlanID{
        public static final int PLAN_TODAY = -1;
        public static final int PLAN_HISTORY = -2;
        public static final int PLAN_IMPORTANT = -3;
    }
}
