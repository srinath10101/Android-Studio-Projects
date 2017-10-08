package com.srinathv.unit_tests;

import android.provider.BaseColumns;

/**
 * Created by Srinath on 04-10-2017.
 */

public final class TimeTableContract {
    public TimeTableContract() {}
    public static abstract class TestEntry implements BaseColumns {
        public static final String TABLE_NAME = "UnitTest";
        public static final String COLUMN_NAME_SUBJECTNAME = "name";
        public static final String COLUMN_NAME_DATEOFTEST="datetest";
        public static final String COLUMN_NAME_PORTIONS="port";
    }
}
