package com.example.logintest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    //############ Declaring attributes ############//
    Button btn_Attendance,
            btn_vEmp, btn_vTask, btn_Logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        //----- Setting attributes to their objects -----//
        btn_Attendance = findViewById(R.id.btn_Attendance);
        btn_vEmp = findViewById(R.id.btn_ViewEmployees);
        btn_vTask = findViewById(R.id.btn_ViewTask);
        btn_Logout = findViewById(R.id.btn_LogOut);

        //----- Setting all the listeners -----//
        btn_Attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, MarkAttendanceActivity.class));
            }
        });

        btn_vEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ViewEmployeeActivity.class));
            }
        });
//
        btn_vTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ViewTaskActivity.class);
                startActivity(intent);
            }
        });

        btn_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            }
        });
    }
}