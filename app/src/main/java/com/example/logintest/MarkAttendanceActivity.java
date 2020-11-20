package com.example.logintest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
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
    private TextView attendance, clockIn, clockOut;

    DatabaseHelper databaseHelper = new DatabaseHelper(MarkAttendanceActivity.this);

    Calendar c = Calendar.getInstance(); // gets the date
    DateFormat fmtDate = DateFormat.getDateInstance(); // format the date displayed in the app

    /*
    // USE BUNDLE TO GET SELECTED ITEM ON SPINNER DROP DOWN
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        TextView  textView=(TextView) findViewById(R.id.txt_bundle);
        Bundle bundle=getIntent().getExtras();
        String data=bundle.get("data").toString();
        textView.setText(data);
    }*/

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            c.set(Calendar.YEAR, i);
            c.set(Calendar.MONTH, i1);
            c.set(Calendar.DAY_OF_MONTH, i2);
            attendance.setText("Date: " + fmtDate.format(c.getTime()));
        }
    }; // event listener after user select a date from calendar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_attendance);

        attendance = findViewById(R.id.txtAttendDateResult);
        clockIn = findViewById(R.id.txtClockInResult);
        clockOut = findViewById(R.id.txtClockOutResult);

        Button buttonDate = findViewById(R.id.btnSetDate);
        Button buttoncIn = findViewById(R.id.btnSetClockIn);
        Button buttoncOut = findViewById(R.id.btnSetClockOut);


        // ˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜Loading spinner
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        Spinner assignee_group = findViewById(R.id.spinnerAssingees);

        List<String> categories = new ArrayList<>();
        categories.add("Item 1");
        categories.add("Item 2");
        categories.add("Item 3");
        categories.add("Item 4");
        categories.add("Item 5");
        categories.add("Item 6");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, categories);
        //List<String> labels = db.getTaskAssigneeLabels();
        //ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, labels);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        assignee_group.setAdapter(dataAdapter);

        assignee_group.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // On selecting a spinner item
                String item = parent.getItemAtPosition(position).toString();

                // Showing selected spinner item
                Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // ˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜End of loading spinner


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

                //Spinner assigneeGroup = findViewById(R.id.spinnerAssignees);

                //String selectedEmployee;
                //selectedEmployee = assigneeGroup.getOnItemSelectedListener().toString();

                att.setaAssignee(attendance.getText().toString());
                att.setAttDate(attendance.getText().toString());
                att.setaClockIn(clockIn.getText().toString());
                att.setaClockOut(clockOut.getText().toString());

                try{
                    Toast.makeText(MarkAttendanceActivity.this, att.toString(), Toast.LENGTH_SHORT).show();

                }catch (Exception e) {
                    Toast.makeText(MarkAttendanceActivity.this, "Error creating the attendance object", Toast.LENGTH_SHORT).show();
                    att = new Attendance("error", "error", "error");
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
