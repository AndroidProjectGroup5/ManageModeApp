package com.example.logintest;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    static String name = "database";
    static int version = 1;

    //################# SQL codes #####################//
    String createTable = "CREATE TABLE if not exists 'user' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'username' TEXT NOT NULL, 'password' TEXT NOT NULL)";
    String sql = "INSERT INTO user (username, password) SELECT 'admin', 'admin' WHERE NOT EXISTS (SELECT 1 FROM user WHERE username ='admin' AND password ='admin')";



    public DatabaseHelper(@Nullable Context context) {
        super(context, name, null, version);
    // creates the table if it doesn't exists
        getWritableDatabase().execSQL(createTable);
    // creates an standard admin account if it doesn't exists.
        getWritableDatabase().execSQL(sql);
    }

    public void insertUsers(ContentValues contentValues){
        getWritableDatabase().insert("user","", contentValues);
    }


    // checks to see if the arguments matches any record in the table
    public boolean isLoginValid(String username, String password){
        String sql = "Select count(*) from user where username ='" + username + "' and password ='" + password + "'";
        SQLiteStatement statement = getReadableDatabase().compileStatement(sql);

        long l = statement.simpleQueryForLong();
        statement.close();

        if(l == 1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
