package com.example.logintest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ViewTaskActivity extends AppCompatActivity {

    Button btn_home, btn_addtask;
    DatabaseHelper mDB;
    TaskListAdapter adapter;
    int emp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);

        btn_addtask = findViewById(R.id.btn_AddTask);
        btn_home = findViewById(R.id.btnBackHomeViewTask);
        SharedPreferences loggedInInfo = PreferenceManager.getDefaultSharedPreferences(this);

        // create TaskListAdapter and create recycler_view_tasks resource layout
        // insert code to access recycler view below

        mDB = new DatabaseHelper(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewTasks);
        adapter = new TaskListAdapter(mDB, new TaskListAdapter.TaskDiff(), loggedInInfo.getInt("id", 0));

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btn_addtask = findViewById(R.id.btn_AddTask);
        btn_home = findViewById(R.id.btnBackHomeViewTask);

        // Toast.makeText(this, "EmployeeID = " + emp, Toast.LENGTH_SHORT).show();
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