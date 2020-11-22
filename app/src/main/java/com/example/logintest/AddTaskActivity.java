package com.example.logintest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddTaskActivity extends AppCompatActivity {

    EditText task_name, task_description;
    Button btn_home, btn_save_task, btn_back;
    Spinner task_status;
    String selectedStatus;
    TextView empName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        btn_back = findViewById(R.id.btnBackAddTask);
        btn_save_task = findViewById(R.id.btnSaveTask);
        btn_home = findViewById(R.id.btnBackHomeAddNewTask);
        empName = findViewById(R.id.txtEmpName2);
        task_status = findViewById(R.id.spinnerStatusGroup);
        task_name = findViewById(R.id.inputTaskName);
        task_description = findViewById(R.id.inputTaskDescription);


        DatabaseHelper databaseHelper = new DatabaseHelper(AddTaskActivity.this);
        SharedPreferences loggedInInfo = PreferenceManager.getDefaultSharedPreferences(this);
        empName.setText(loggedInInfo.getString("EmpName", null));



        btn_save_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task tsk = new Task();
                selectedStatus = task_status.getSelectedItem().toString();

                tsk.setEmployeeID(loggedInInfo.getInt("id", 0));
                tsk.setTaskName(task_name.getText().toString());
                tsk.setDescription(task_description.getText().toString());
                tsk.setStatus(selectedStatus);

                try{
                    Toast.makeText(AddTaskActivity.this, tsk.toString(), Toast.LENGTH_SHORT).show();

                }catch (Exception e) {
                    Toast.makeText(AddTaskActivity.this, "Error creating the task", Toast.LENGTH_SHORT).show();
                    tsk = new Task("error, ", "error", "error");
                }

                Boolean success = databaseHelper.addTask(tsk);
                Toast.makeText(AddTaskActivity.this, "Success = " + success, Toast.LENGTH_SHORT).show();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddTaskActivity.this, ViewTaskActivity.class));
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