package com.example.logintest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ViewAllProjectsActivity extends AppCompatActivity {

    Button btn_back, btn_cProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_projects);

        btn_cProject = findViewById(R.id.btn_cProject2);
        btn_back = findViewById(R.id.btnBackViewAllProj);

        btn_cProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewAllProjectsActivity.this, CreateProjectActivity.class));
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewAllProjectsActivity.this, HomeActivity.class));
            }
        });
    }
}