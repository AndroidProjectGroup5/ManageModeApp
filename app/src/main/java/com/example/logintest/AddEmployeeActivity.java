package com.example.logintest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddEmployeeActivity extends AppCompatActivity {

    //############ Declaring attributes ############//
    EditText create_username, create_password;
    Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addemployee);

        create_username = findViewById(R.id.create_username);
        create_password = findViewById(R.id.create_password);
        btnCreate = findViewById(R.id.btnCreate);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameValue = create_username.getText().toString();
                String passwordValue = create_password.getText().toString();
                Toast.makeText(AddEmployeeActivity.this, "Employee was successfully created!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}