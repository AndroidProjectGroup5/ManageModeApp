package com.example.logintest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    //############ Declaring attributes ############//
    Button btn_register, btn_Attendance,
            btn_cProject, btn_vProject,
            btn_vEmp, btn_addtask, btn_vTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        //----- Setting attributes to their objects -----//
    //    btn_register = findViewById(R.id.btn_Register);
    //    btn_addtask = findViewById(R.id.btn_AddTask);
        btn_Attendance = findViewById(R.id.btn_Attendance);
        btn_cProject = findViewById(R.id.btn_cProject);
        btn_vProject = findViewById(R.id.btn_vProject);
        btn_vEmp = findViewById(R.id.btn_ViewEmployees);
        btn_vTask = findViewById(R.id.btn_ViewTask);



        //----- Setting all the listeners -----//
        btn_Attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, MarkAttendanceActivity.class));
            }
        });

        btn_cProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, CreateProjectActivity.class));
            }
        });

        btn_vProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ViewProjectActivity.class));
            }
        });

    /*   btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AddEmployeeActivity.class);
                startActivity(intent);
            }
        });

     */

        btn_vEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ViewEmployeeActivity.class));
            }
        });
//        btn_addtask.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(HomeActivity.this, AddTaskActivity.class);
//                startActivity(intent);
//            }
//        });

        btn_vTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ViewTaskActivity.class);
                startActivity(intent);
            }
        });
    }
}