package com.example.logintest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ViewProjectActivity extends AppCompatActivity {

    Button btn_back, btn_viewTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_project);

        btn_back = findViewById(R.id.btnBackViewProj);
        btn_viewTasks = findViewById(R.id.btn_vTasks);

        btn_viewTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ViewProjectActivity.this, "Where should I go?", Toast.LENGTH_SHORT).show();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewProjectActivity.this, HomeActivity.class));
            }
        });
    }
}