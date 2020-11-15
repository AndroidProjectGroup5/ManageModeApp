package com.example.logintest;

public class Attendance extends Employee {

    private String eName;
    private String AttDate;
    private String aClockIn;
    private String aClockOut;

    public Attendance() {}

    public Attendance(String eName, String attdate, String cin, String cout){
        this.eName = eName;
        this.AttDate = attdate;
        this.aClockIn = cin;
        this.aClockOut = cout;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                ", attEmpName='" + eName + '\'' +
                ", attDate='" + AttDate + '\'' +
                ", aClockIn='" + aClockIn + '\'' +
                ", aClockOut='" + aClockOut + '\'' +
                '}';
    }

    public String getAttDate() {return AttDate;}

    public void setAttDate(String attdate) {this.AttDate = attdate;}

    public String getaClockIn() {return aClockIn;}

    public void setaClockIn(String cin) {this.aClockIn = cin;}

    public String getaClockOut() {return aClockOut;}

    public void setaClockOut(String cout) {this.aClockOut = cout;}
}
