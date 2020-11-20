package com.example.logintest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    static String name = "database21";
    static int version = 2;

    private static final String TAG = DatabaseHelper.class.getSimpleName();
    public DatabaseHelper(@Nullable Context context) {
        super(context, name, null, version);
    }

    private SQLiteDatabase mWritableDB;
    private SQLiteDatabase mReadableDB;

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


    String attendance = "CREATE TABLE 'attendance' ("+
            "'EmployeeName'	VARCHAR NOT NULL," +
            "'AttDate'	TEXT NOT NULL," +
            "'ClockIn'	TEXT NOT NULL," +
            "'ClockOut'	TEXT NOT NULL," +
            "FOREIGN KEY('EmployeeName') REFERENCES 'user'('eName') ON DELETE CASCADE ON UPDATE CASCADE);";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(user);
        db.execSQL(task); // right way would be to check if table exists first, this just simplifies
        db.execSQL(attendance); // right way would be to check if table exists first, this just simplifies

        fillEmployees(db);
        fillTasks(db);
        fillAttendances(db);
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
                "'Cleaning', 'Clean the premises after the workday is over.', 'Danilo Andrade', 'Completed'), " +
                "('Security check', 'Check if visitors are authorized', 'Jaimish Patel', 'Ongoing')," +
                "('Locking down', 'Locking the facilities', 'Suzuka Natsuhara', 'Not started')";
        db.execSQL(insertTasks);
    }

    private void fillAttendances(SQLiteDatabase db) {
        String insertAttendances = "INSERT INTO attendance (EmployeeName, AttDate, ClockIn, ClockOut) VALUES (" +
                "'Danilo Andrade', '2020-11-05', '09:00:00.000', '04:00:00.000'), " +
                "('Andrea Cruz', '2020-10-26', '10:30:00.000', '06:00:00.000')," +
                "('Jaimish Patel', '2020-09-26', '09:30:00.000', '05:00:00.000')," +
                "('Suzuka Natsuhara', '2020-11-13', '08:30:00.000', '07:00:00.000')," +
                "('Employee 1', '2020-09-17', '07:45:00.000', '03:45:00.000')," +
                "('Danilo Andrade', date('now'), '09:30:00.000', '04:45:00.000')";
        db.execSQL(insertAttendances);
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


    public Employee searchEmployee (int position){
        String query = "SELECT * FROM user ORDER BY EmployeeName ASC LIMIT " + position +", 1";
        Cursor cursor = null;
        Employee emp = new Employee();

        try {
            if(mReadableDB == null){
                mReadableDB = getReadableDatabase();
            }
            cursor = mReadableDB.rawQuery(query, null);
            cursor.moveToFirst();

            emp.setId(cursor.getInt(0));
            emp.seteName(cursor.getString(1));
            emp.seteUsername(cursor.getString(2));
            emp.setePassword(cursor.getString(3));

        } catch (Exception e){
            Log.d(TAG, "EXCEPTION! " + e);
        } finally {
            cursor.close(); // have to close the cursor to release the resources
            return emp;
        }
    }

    public long count(){
        if (mReadableDB == null) {
            mReadableDB = getReadableDatabase();
        }
        return DatabaseUtils.queryNumEntries(mReadableDB, "user");
    }

    public boolean saveAttendance (Attendance att) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("EmployeeName", att.geteName());
        cv.put("AttDate", att.getAttDate());
        cv.put("ClockIn", att.getaClockIn());
        cv.put("ClockOut", att.getaClockOut());

        long insert = db.insert("attendance", null, cv);
        if(insert == -1){
            return false;
        }else {
            return true;
        }
    }

    public int delete (int id){
        int deleted = 0;
        try{
            if(mWritableDB == null){
                mWritableDB = getWritableDatabase();
            }
            deleted = mWritableDB.delete("user", 0 + " =? ", new String[]{String.valueOf(id)});
        }catch (Exception e){
            Log.d(TAG, "Delete Exception!" + e.getMessage());
        }
        return deleted;
    }

//----- NOTE: I don't think we need this one, Ive commented it for now.
/*    public void insertUsers(ContentValues contentValues){
        getWritableDatabase().insert("user","", contentValues);
    }*/

    public void insertTasks(ContentValues contentValues){
        getWritableDatabase().insert("task","", contentValues);
    }

    public void insertAttendances(ContentValues contentValues){
        getWritableDatabase().insert("attendance","", contentValues);
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
        db.execSQL("DROP TABLE IF EXISTS 'attendance'");
        onCreate(db);
    }






    // For Attendance Spinner
    public List<String> getAttendAssigneeLabels(){
        List<String> list = new ArrayList<>();

        String selectQuery = "SELECT * FROM 'attendance'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null); //selectQuery,selectedArguments

        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(2));//adding 3rd column data
            } while (cursor.moveToNext());
        }
        cursor.close();

        return list;
    }
}


// Danilo's spare code to test and adjust
/*    String status = "CREATE TABLE 'Status' ('StatusID' INTEGER NOT NULL," +
            "'Name' TEXT NOT NULL PRIMARY KEY('StatusID'));";

    String task = "CREATE TABLE 'task' ('TaskID' INTEGER PRIMARY KEY AUTOINCREMENT," +
            "'userID' INTEGER NOT NULL," +
            "'EmployeeID' INTEGER NOT NULL," +
            "'StatusID' INTEGER," +
            "'TaskName' TEXT NOT NULL," +
            "'TaskDescription' TEXT NOT NULL," +
            "FOREIGN KEY('userID') REFERENCES 'user'('id'));";

    */
