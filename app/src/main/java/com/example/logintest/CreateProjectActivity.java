package com.example.logintest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CreateProjectActivity extends AppCompatActivity {

    Button btn_home, btn_create, btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);

        btn_back = findViewById(R.id.btnBackCreateProject);
        btn_home = findViewById(R.id.btnBackHomeCreateProject);
        btn_create = findViewById(R.id.btnCreateProj);

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CreateProjectActivity.this, "Project created!", Toast.LENGTH_SHORT).show();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateProjectActivity.this, ViewAllProjectsActivity.class));
            }
        });

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateProjectActivity.this, HomeActivity.class));
            }
        });
    }
}