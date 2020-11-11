package com.example.logintest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddEmployeeActivity extends AppCompatActivity {

    //############ Declaring attributes ############//
    EditText create_name, create_username, create_password;
    Button btn_home, btn_back, btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addemployee);

        create_name = findViewById(R.id.create_name);
        create_username = findViewById(R.id.create_username);
        create_password = findViewById(R.id.create_password);
        btnCreate = findViewById(R.id.btnCreate);
        btn_home = findViewById(R.id.btnBackHomeAddEmp);
        btn_back = findViewById(R.id.btnBackAddEmployee);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Employee emp = new Employee();

                emp.seteName(create_name.getText().toString());
                emp.seteUsername(create_username.getText().toString());
                emp.setePassword(create_password.getText().toString());

                try{
                    Toast.makeText(AddEmployeeActivity.this, emp.toString(), Toast.LENGTH_SHORT).show();

                }catch (Exception e) {
                    Toast.makeText(AddEmployeeActivity.this, "Error creating the employee", Toast.LENGTH_SHORT).show();
                    emp = new Employee("error, ", "error", "error");
                }

                DatabaseHelper databaseHelper = new DatabaseHelper(AddEmployeeActivity.this);
                Boolean sucess = databaseHelper.addUser(emp);
                Toast.makeText(AddEmployeeActivity.this, "Success = " + sucess, Toast.LENGTH_SHORT).show();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddEmployeeActivity.this, ViewEmployeeActivity.class));
            }
        });

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddEmployeeActivity.this, HomeActivity.class));
            }
        });
    }
}