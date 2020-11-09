package com.example.logintest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChangeTaskActivity extends AppCompatActivity {

    Button btn_home, btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_task);

        btn_home = findViewById(R.id.btnBackHomeChaTsk);
        btn_back = findViewById(R.id.btnBackChgStsTask);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChangeTaskActivity.this, ViewTaskActivity.class));
            }
        });

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChangeTaskActivity.this, HomeActivity.class));
            }
        });
    }

}