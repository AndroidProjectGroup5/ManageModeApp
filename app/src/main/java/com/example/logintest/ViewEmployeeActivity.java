package com.example.logintest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ViewEmployeeActivity extends AppCompatActivity {

    Button btn_back, btn_addEmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_employee);

        btn_addEmp = findViewById(R.id.btnAddEmp);
        btn_back = findViewById(R.id.btnBack);

        btn_addEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewEmployeeActivity.this, AddEmployeeActivity.class));
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewEmployeeActivity.this, HomeActivity.class));
            }
        });
    }
}