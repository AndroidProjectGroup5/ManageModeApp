package com.example.logintest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AddTaskActivity extends AppCompatActivity {

    Button btn_home, btn_save_task, btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        btn_back = findViewById(R.id.btnBackHomeMarkAttend);
        btn_save_task = findViewById(R.id.btnSaveTask);
        btn_home = findViewById(R.id.btnBackHomeAddNewTask);

        btn_save_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddTaskActivity.this, "Task Added!", Toast.LENGTH_SHORT).show();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddTaskActivity.this, HomeActivity.class));
            }
        });

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddTaskActivity.this, HomeActivity.class));
            }
        });
    }
}