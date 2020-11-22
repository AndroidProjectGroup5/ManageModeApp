package com.example.logintest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {


    //############ Declaring attributes ############//
    EditText et_username, et_password;
    Button btn_login;
    DatabaseHelper databaseHelper;
    Employee loggedEmp = new Employee();
    public static SharedPreferences loggedInInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
        loggedInInfo = PreferenceManager.getDefaultSharedPreferences(this);

        databaseHelper = new DatabaseHelper(LoginActivity.this);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameValue = et_username.getText().toString();
                String passwordValue = et_password.getText().toString();

                if(databaseHelper.isLoginValid(usernameValue, passwordValue)){

                    loggedEmp = databaseHelper.lookForEmp(et_username.getText().toString());

                    SharedPreferences.Editor editor = loggedInInfo.edit();
                    editor.putInt("id", loggedEmp.getId());
                    editor.putString("EmpName", loggedEmp.geteName());
                    editor.commit();

                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    //Toast.makeText(LoginActivity.this, loggedInInfo.getString("id", ""), Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(LoginActivity.this, "Invalid username or password.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}