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

public class EditTaskActivity extends AppCompatActivity {

    EditText task_editname, task_editdescription;
    Button btn_edithome, btn_save_edittask, btn_editback;
    Spinner task_editstatus;
    String selectedStatus;
    TextView editempName;
    //String EditTaskId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);


        btn_save_edittask = findViewById(R.id.btnSaveEditTask);
        btn_edithome = findViewById(R.id.btnBackHomeAddNewTask);
        btn_editback = findViewById(R.id.btnEditBackAddTask);
        editempName = findViewById(R.id.txtEditEmpName);
        task_editstatus = findViewById(R.id.spinnerEditStatusGroup);
        task_editname = findViewById(R.id.inputEditTaskName);
        task_editdescription = findViewById(R.id.inputEditTaskDescription);

        DatabaseHelper databaseHelper = new DatabaseHelper(EditTaskActivity.this);
        SharedPreferences loggedInInfo = PreferenceManager.getDefaultSharedPreferences(this);
        editempName.setText(loggedInInfo.getString("EmpName", null));

        getIncomingIntent();

        btn_save_edittask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task tsk = new Task();
                selectedStatus = task_editstatus.getSelectedItem().toString();

                try {
                    tsk.setEmployeeID(loggedInInfo.getInt("id", 0));
                    tsk.setTaskName(task_editname.getText().toString());
                    tsk.setDescription(task_editdescription.getText().toString());
                    tsk.setStatus(selectedStatus);
                    
                } catch (Exception e) {
                    Toast.makeText(EditTaskActivity.this, "Error editing the task", Toast.LENGTH_SHORT).show();
                    tsk = new Task("error, ", "error", "error");
                }

                Boolean success = databaseHelper.editTask(tsk);
                Toast.makeText(EditTaskActivity.this, tsk.toString(), Toast.LENGTH_SHORT).show();
                if (success == true) {
                    startActivity(new Intent(EditTaskActivity.this, ViewTaskActivity.class));
                } else {
                    Toast.makeText(EditTaskActivity.this, "Error, please try again.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btn_edithome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditTaskActivity.this, HomeActivity.class));
            }
        });
        btn_editback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditTaskActivity.this, ViewTaskActivity.class));
            }
        });

    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("task_name")
                && getIntent().hasExtra("task_description") && getIntent().hasExtra("task_status")) {

            SetEditTaskData(getIntent().getStringExtra("task_name"), getIntent().getStringExtra("task_description"), getIntent().getStringExtra("task_status"));
            // EditTaskId = getIntent().getStringExtra("task_id");
        } else {
            Toast.makeText(EditTaskActivity.this, "Error, while Loading Data.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(EditTaskActivity.this, ViewTaskActivity.class));
        }
    }

    private void SetEditTaskData(String taskname, String taskdescription, String taskstatus) {
        task_editname.setText(taskname);
        task_editdescription.setText(taskdescription);
        task_editstatus.setSelected(true);
    }
}