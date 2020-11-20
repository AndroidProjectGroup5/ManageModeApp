package com.example.logintest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class AddTaskActivity extends AppCompatActivity {

    EditText task_name, task_description;

    Button btn_home, btn_save_task, btn_back;

    Spinner assignee_group = findViewById(R.id.spinnerAssingees);
    Spinner status_group = findViewById(R.id.spinnerStatusGroup);

    String task_assignee, task_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        btn_back = findViewById(R.id.btnBackHomeMarkAttend);
        btn_save_task = findViewById(R.id.btnSaveTask);
        btn_home = findViewById(R.id.btnBackHomeAddNewTask);

        task_name = findViewById(R.id.inputTaskName);
        task_description = findViewById(R.id.inputTaskDescription);
        //task_assignee = assignee_group.getSelectedItem().toString();
        task_status = status_group.getSelectedItem().toString();

        assignee_group.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String label = parent.getItemAtPosition(position).toString();

                Toast.makeText(parent.getContext(), "You selected: " + label, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // 2 - Loading spinner data from database
        loadSpinnerData();

        btn_save_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task tsk = new Task();

                tsk.settName(task_name.getText().toString());
                tsk.settDescription(task_description.getText().toString());
                tsk.settAssignee(task_assignee);
                tsk.settStatus(task_status);

                try{
                    Toast.makeText(AddTaskActivity.this, tsk.toString(), Toast.LENGTH_SHORT).show();

                }catch (Exception e) {
                    Toast.makeText(AddTaskActivity.this, "Error creating the task", Toast.LENGTH_SHORT).show();
                    tsk = new Task("error, ", "error", "error");
                }

                DatabaseHelper databaseHelper = new DatabaseHelper(AddTaskActivity.this);
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

    private void loadSpinnerData() {
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        List<String> labels = db.getTaskAssigneeLabels();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, labels);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        assignee_group.setAdapter(dataAdapter);

    }
}