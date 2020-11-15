package com.example.logintest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewTaskActivity extends AppCompatActivity {

    TextView setAssignee ;
    Button btn_home, btn_back, btn_addtask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);

        btn_addtask = findViewById(R.id.btn_AddTask);
        btn_home = findViewById(R.id.btnBackHomeViewTask);

        // create TaskListAdapter and create recycler_view_tasks resource layout
        // insert code to access recycler view below

        btn_addtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewTaskActivity.this, AddTaskActivity.class));
            }
        });

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewTaskActivity.this, HomeActivity.class));
            }
        });
    }
}