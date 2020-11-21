package com.example.logintest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.M)
public class MarkAttendanceActivity extends AppCompatActivity {

    Button btn_attend, btn_back;
    private TextView date, clockIn, clockOut;
    TextView txtName, txtID;

    DatabaseHelper databaseHelper = new DatabaseHelper(MarkAttendanceActivity.this);

    Calendar c = Calendar.getInstance(); // gets the date
    DateFormat fmtDate = DateFormat.getDateInstance(); // format the date displayed in the app


    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            c.set(Calendar.YEAR, i);
            c.set(Calendar.MONTH, i1);
            c.set(Calendar.DAY_OF_MONTH, i2);
            date.setText("Date: " + fmtDate.format(c.getTime()));
        }
    }; // event listener after user select a date from calendar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_attendance);

        date = findViewById(R.id.txtAttendDateResult);
        clockIn = findViewById(R.id.txtClockInResult);
        clockOut = findViewById(R.id.txtClockOutResult);
        txtName = findViewById(R.id.txtEmpName);

        SharedPreferences loggedInInfo = PreferenceManager.getDefaultSharedPreferences(this);
        txtName.setText(loggedInInfo.getString("EmpName", null));
        //txtName.setText(loggedInInfo.getInt("id", 0));
        //(loggedInInfo.getInt("id", 0));


        Button buttonDate = findViewById(R.id.btnSetDate);
        Button buttoncIn = findViewById(R.id.btnSetClockIn);
        Button buttoncOut = findViewById(R.id.btnSetClockOut);


        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MarkAttendanceActivity.this,
                        d,
                        c.get(Calendar.YEAR),
                        c.get(Calendar.MONTH),
                        c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        buttoncIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hours = c.get(Calendar.HOUR_OF_DAY);
                int minutes = c.get(Calendar.MINUTE);
                new TimePickerDialog(MarkAttendanceActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int hour, int minute) {
                                clockIn.setText(hour + ":" + minute);
                            }
                        }, hours, minutes, true).show();
            }
        });

        buttoncOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hours = c.get(Calendar.HOUR_OF_DAY);
                int minutes = c.get(Calendar.MINUTE);
                new TimePickerDialog(MarkAttendanceActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int hour, int minute) {
                                clockOut.setText(hour + ":" + minute);
                            }
                        }, hours, minutes, true).show();
            }
        });

        btn_back = findViewById(R.id.btnBackHomeMarkAttend);
        btn_attend = findViewById(R.id.btnSaveAttendance);

        btn_attend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Attendance att = new Attendance();

                att.setEmployeeID(1);
                att.setAttDate(date.getText().toString());
                att.setaClockIn(clockIn.getText().toString());
                att.setaClockOut(clockOut.getText().toString());

                try{
                    Toast.makeText(MarkAttendanceActivity.this, att.toString(), Toast.LENGTH_SHORT).show();

                }catch (Exception e) {
                    Toast.makeText(MarkAttendanceActivity.this, "Error creating the attendance object", Toast.LENGTH_SHORT).show();
                    att = new Attendance(0 , "error", "error", "error");
                }

                Boolean success = databaseHelper.saveAttendance(att);
                Toast.makeText(MarkAttendanceActivity.this, "Success = " + success, Toast.LENGTH_SHORT).show();

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
