package com.example.logintest;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    static String name = "managemod";
    static int version = 1;

    private static final String TAG = DatabaseHelper.class.getSimpleName();
    public DatabaseHelper(@Nullable Context context) {
        super(context, name, null, version);
    }

    //################# SQL codes #####################//
    //String createTable = "CREATE TABLE if not exists 'user' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'username' VARCHAR NOT NULL, 'password' VARCHAR NOT NULL, 'isAdmin' BOOL)";
    String user = "CREATE TABLE if not exists 'user' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "'EmployeeName' VARCHAR NOT NULL, " +
            "'username' VARCHAR NOT NULL," +
            " 'password' VARCHAR NOT NULL)";

    /*
    * Put a new foreign key pointing to employee id
    * query for that id to find username
    * */
    String task = "CREATE TABLE if not exists 'task' ('task_id' INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "'TaskName' VARCHAR NOT NULL, " +
            "'TaskDescription' VARCHAR NOT NULL," +
            "'TaskAssignee' VARCHAR NOT NULL," +
            " 'TaskStatus' VARCHAR NOT NULL)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(user);
        db.execSQL(task); // right way would be to check if table exists first, this just simplifies
        fillEmployees(db);
        fillTasks(db);
    }

    public void fillEmployees(SQLiteDatabase db) {
        String insertUsers = "INSERT INTO user (EmployeeName, username, password) VALUES (" +
                "'admin', 'admin', 'admin'), " +
                "('Danilo Andrade', 'danilo', 'dan12')," +
                "('Andrea Cruz', 'andrea', 'andrea34')," +
                "('Jaimish Patel', 'jaimish', 'jaimish56')," +
                "('Suzuka Natsuhara', 'suzuka', 'suzuka78')," +
                "('John Mathew', 'johnmathew', 'john000')";
        db.execSQL(insertUsers);
    }


    private void fillTasks(SQLiteDatabase db) {
        String insertTasks = "INSERT INTO task (TaskName, TaskDescription, TaskAssignee, TaskStatus) VALUES (" +
                "'Cleaning', 'Clean the premises after the workday is over.', 'Joe', 'Completed'), " +
                "('Security check', 'Check if visitors are authorized', 'Michael', 'Ongoing')," +
                "('Locking down', 'Locking the facilities', 'Mary', 'Not started')";
        db.execSQL(insertTasks);
    }

    public boolean addUser (Employee emp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("EmployeeName", emp.geteName());
        cv.put("username", emp.geteUsername());
        cv.put("password", emp.getePassword());
        //cv.put("isAdmin", emp.getAdmin());

        long insert = db.insert("user", null, cv);
        if(insert == -1){
            return false;
        }else {
            return true;
        }
    }

    public boolean addTask (Task tsk) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("TaskName", tsk.gettName());
        cv.put("TaskDescription", tsk.gettDescription());
        cv.put("TaskAssignee", tsk.gettAssignee());
        cv.put("TaskStatus", tsk.gettStatus());

        long insert = db.insert("task", null, cv);
        if(insert == -1){
            return false;
        }else {
            return true;
        }

    }

    public void insertUsers(ContentValues contentValues){
        getWritableDatabase().insert("user","", contentValues);
    }

    public void insertTasks(ContentValues contentValues){
        getWritableDatabase().insert("task","", contentValues);
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
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.w(DatabaseHelper.class.getName(),
                "Upgrading database from version " + i + " to "
                        + i1 + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS 'user'");
        db.execSQL("DROP TABLE IF EXISTS 'task'");
        onCreate(db);
    }
}

/*    String status = "CREATE TABLE 'Status' ('StatusID' INTEGER NOT NULL," +
            "'Name' TEXT NOT NULL PRIMARY KEY('StatusID'));";

    /*String project = "CREATE TABLE 'Project' ('ProjectID' INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "'MemberID' INTEGER NOT NULL, " +
            "'ProjectName' TEXT NOT NULL, " +
            "'Description' TEXT, " +
            " FOREIGN KEY('MemberID') REFERENCES 'users'('id') ON DELETE CASCADE ON UPDATE CASCADE);";

    String task = "CREATE TABLE 'task' ('TaskID' INTEGER PRIMARY KEY AUTOINCREMENT," +
            "'ProjectID' INTEGER NOT NULL," +
            "'EmployeeID' INTEGER NOT NULL," +
            "'StatusID' INTEGER," +
            "'TaskName' TEXT NOT NULL," +
            "'TaskDescription' TEXT NOT NULL," +
            "FOREIGN KEY('ProjectID') REFERENCES 'Project'('ProjectID'));";

    String attendance = "CREATE TABLE 'attendance' ("+
            "'EmployeeID'	INTEGER NOT NULL," +
            "'Date'	NUMERIC NOT NULL," +
            "'Status'	BLOB NOT NULL," +
            "FOREIGN KEY('EmployeeID') REFERENCES 'users'('id') ON DELETE CASCADE ON UPDATE CASCADE);";*/
