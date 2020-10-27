package com.example.logintest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    //############ Declaring attributes ############//
    Button btn_register;
    Button btn_addtask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        btn_register = findViewById(R.id.btn_Register);
        btn_addtask = findViewById(R.id.btn_AddTask);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AddEmployeeActivity.class);
                startActivity(intent);
            }
        });

        btn_addtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AddTaskActivity.class);
                startActivity(intent);
            }
        });
    }
}