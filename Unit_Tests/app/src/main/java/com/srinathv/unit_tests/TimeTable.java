package com.srinathv.unit_tests;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class TimeTable extends AppCompatActivity {
    public static TimeTableHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),AddSubjects.class);
                startActivity(i);
            }
        });

        TextView t = (TextView) findViewById(R.id.txt);

        try{
            dbHelper = new TimeTableHelper(this);
            Cursor cursor = dbHelper.getAllSubjects();
            String subject = "";
            String entire = "";
            String portions = "";
            String con = cursor.moveToNext() + "";
            Toast.makeText(this,con, Toast.LENGTH_SHORT).show();
            while(cursor.moveToNext()){
                subject = cursor.getString(cursor.getColumnIndex(TimeTableContract.TestEntry.COLUMN_NAME_SUBJECTNAME));
                entire = cursor.getString(cursor.getColumnIndex(TimeTableContract.TestEntry.COLUMN_NAME_DATEOFTEST));
                portions = cursor.getString(cursor.getColumnIndex(TimeTableContract.TestEntry.COLUMN_NAME_PORTIONS));
                t.setText(subject+"");
            }
        }
        catch(Exception e){
            Toast.makeText(this, e.getMessage()+"", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_time_table, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
