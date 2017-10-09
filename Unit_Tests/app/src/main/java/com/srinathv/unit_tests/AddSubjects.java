package com.srinathv.unit_tests;

import android.content.Intent;
import android.icu.util.GregorianCalendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import java.util.Calendar;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;

public class AddSubjects extends AppCompatActivity {
    public String yyyy = "",mm="",dd="";
    public int dayweek = 0;
    public String entireday = "";
    public String subject = "";
    public String portions = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subjects);

        Button b2 = (Button) findViewById(R.id.butt2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText e = (EditText) findViewById(R.id.edt1);
                EditText f = (EditText) findViewById(R.id.edt2);

                if(entireday.length()!=0){
                    try {
                        TimeTableHelper tth = new TimeTableHelper(getApplicationContext());
                        SQLiteDatabase db = tth.getWritableDatabase();

                        ContentValues values = new ContentValues();
                        values.put(TimeTableContract.TestEntry.COLUMN_NAME_SUBJECTNAME, e.getText().toString());
                        values.put(TimeTableContract.TestEntry.COLUMN_NAME_DATEOFTEST, entireday);
                        values.put(TimeTableContract.TestEntry.COLUMN_NAME_SUBJECTNAME, f.getText().toString());

                        long newID = db.insert(TimeTableContract.TestEntry.TABLE_NAME, null, values);
                    }
                    catch(Exception E){
                        Toast.makeText(AddSubjects.this, E.getMessage(), Toast.LENGTH_LONG).show();
                    }
                    Toast.makeText(AddSubjects.this, "I'll make sure to notify you :)", Toast.LENGTH_SHORT).show();

                }
                else Toast.makeText(AddSubjects.this, "Enter all details!", Toast.LENGTH_SHORT).show();

            }
        });

        Button b1 = (Button) findViewById(R.id.butt1);
        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(getApplicationContext(),TimeTable.class);
                i.putExtra("hello","hello");
                startActivity(i);
            }
        });

        CalendarView v = (CalendarView) findViewById(R.id.calendarView);
        v.setOnDateChangeListener( new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Calendar c = Calendar.getInstance();
                c.set(year, month, dayOfMonth);
                dayweek = c.get(Calendar.DAY_OF_WEEK);
                dd = dayOfMonth+"";
                mm = (month+1)+"";
                yyyy = year+"";
                if (dayweek==2)
                    entireday= "Monday, "+dd+"-"+mm+"-"+yyyy;
                else if(dayweek==3)
                    entireday= "Tuesday, "+dd+"-"+mm+"-"+yyyy;
                else if(dayweek==4)
                    entireday= "Wednesday, "+dd+"-"+mm+"-"+yyyy;
                else if(dayweek==5)
                    entireday= "Thursday, "+dd+"-"+mm+"-"+yyyy;
                else if(dayweek==6)
                    entireday= "Friday, "+dd+"-"+mm+"-"+yyyy;
                else if(dayweek==7)
                    entireday= "Saturday, "+dd+"-"+mm+"-"+yyyy;
            }
        });



    }

}
