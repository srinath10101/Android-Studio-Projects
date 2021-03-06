package com.srinathv.unit_tests;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Srinath on 04-10-2017.
 */
// idk why on using abstract, no error comes
public class TimeTableHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "UnitTest.db";
    SQLiteDatabase d;

    public TimeTableHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        d = db;
        String SQL_CREATE_ENTRIES = "CREATE TABLE " + TimeTableContract.TestEntry.TABLE_NAME +" ( "
                + TimeTableContract.TestEntry.COLUMN_NAME_SUBJECTNAME + " VARCHAR(40) NOT NULL,"
                + TimeTableContract.TestEntry.COLUMN_NAME_DATEOFTEST + " VARCHAR(20) NOT NULL,"
                + TimeTableContract.TestEntry.COLUMN_NAME_PORTIONS + " VARCHAR(50) NOT NULL"
                +" );";
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        String del = "DROP TABLE IF EXISTS "+ TimeTableContract.TestEntry.TABLE_NAME;
        db.execSQL(del);
        onCreate(db);
    }

    public Cursor getAllSubjects(){
        SQLiteDatabase db = this.getReadableDatabase();
        if (db==null)
            Log.v("TAG_1","db is null");
        else
            Log.v("TAG_1","db is not null");
        Cursor cursor = db.rawQuery("SELECT * FROM "
                +TimeTableContract.TestEntry.TABLE_NAME, null);
        return cursor;
    }

    public Cursor deleteSubject(String s){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("DELETE FROM "
                +TimeTableContract.TestEntry.TABLE_NAME+" WHERE "+TimeTableContract.TestEntry.COLUMN_NAME_SUBJECTNAME
                +" = '"+s+"';", null);
        return cursor;
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }



}
