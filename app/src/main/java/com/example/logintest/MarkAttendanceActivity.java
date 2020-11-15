package com.example.logintest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class MarkAttendanceActivity extends AppCompatActivity {

    Button btn_attend, btn_back;
    private TextView attendance, clockInOut;

    Calendar c = Calendar.getInstance(); // gets the date
    DateFormat fmtDate = DateFormat.getDateInstance(); // format the date displayed in the app
    DateFormat fmtTime = DateFormat.getTimeInstance(); // format the date displayed in the app

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            c.set(Calendar.YEAR, i);
            c.set(Calendar.MONTH, i1);
            c.set(Calendar.DAY_OF_MONTH, i2);
            attendance.setText("Date: " + fmtDate.format(c.getTime()));
        }
    }; // event listener after user select a date from calendar

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int h, int h1) {
            c.set(Calendar.HOUR_OF_DAY, h);
            c.set(Calendar.MINUTE, h1);
            clockInOut.setText("ClockIn: " + fmtTime.format(c.getTime()));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_attendance);

        attendance = (TextView) findViewById(R.id.txtAttend);
        Button button = (Button) findViewById(R.id.btnSetDate);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MarkAttendanceActivity.this,
                        d,
                        c.get(Calendar.YEAR),
                        c.get(Calendar.MONTH),
                        c.get(Calendar.DAY_OF_MONTH)).show();
            }
            //public void onClick(View v) {
            //    new TimePickerDialog(MarkAttendanceActivity.this,
            //            t,
            //            c.get(Calendar.HOUR_OF_DAY),
            //            c.get(Calendar.MINUTE)).show();
            //}
        });

        btn_back = findViewById(R.id.btnBackHomeMarkAttend);
        btn_attend = findViewById(R.id.btnSaveAttendance);

        btn_attend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MarkAttendanceActivity.this, "Attendance Saved!", Toast.LENGTH_SHORT).show();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MarkAttendanceActivity.this, HomeActivity.class));
            }
        });
    }
}