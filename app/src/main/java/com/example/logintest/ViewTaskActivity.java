package com.example.logintest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewTaskActivity extends AppCompatActivity {

    TextView setAssignee ;
    Button btn_addtask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);

        setAssignee = (TextView)findViewById(R.id.txtSetAssignee);
        btn_addtask = findViewById(R.id.btn_AddTask);

        setAssignee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewTaskActivity.this, ChangeTaskActivity.class);
                startActivity(intent);
            }
        });


        btn_addtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewTaskActivity.this, AddTaskActivity.class));
            //    startActivity(intent);
            }
        });
    }
}