package com.example.logintest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ViewEmployeeActivity extends AppCompatActivity {

    Button btn_home, btn_back, btn_addEmp;
    EmployeeListAdapter adapter;
    DatabaseHelper mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_employee);

        mDB = new DatabaseHelper(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewEmps);
        adapter = new EmployeeListAdapter(mDB, new EmployeeListAdapter.EmployeeDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btn_addEmp = findViewById(R.id.btnAddEmp);
        btn_back = findViewById(R.id.btnBackHomeMarkAttend);
        btn_home = findViewById(R.id.btnBackHomeViewEmp);

        // create EmployeeListAdapter and create recycler_view_employees resource layout
        // insert code to access recycler view below

        btn_addEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewEmployeeActivity.this, AddEmployeeActivity.class));
            }
        });

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewEmployeeActivity.this, HomeActivity.class));
            }
        });
    }

}