package com.example.logintest;

public class Attendance extends Employee {

    private int aId;
    private String aAssignee;
    private String AttDate;
    private String aClockIn;
    private String aClockOut;

    public Attendance() {
    }

    public Attendance(int aId, String attdate, String cin, String cout){
        this.setId(aId);
        this.aAssignee = super.geteName();
        this.AttDate = attdate;
        this.aClockIn = cin;
        this.aClockOut = cout;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                ", attEmpName='" + aAssignee + '\'' +
                ", attDate='" + AttDate + '\'' +
                ", aClockIn='" + aClockIn + '\'' +
                ", aClockOut='" + aClockOut + '\'' +
                '}';
    }

    public int getId() {
        return this.aId;
    }

    public void setId(int id) {
        this.aId = id;
    }

    public String getaAssignee() {
        return this.aAssignee;
    }

    public void setaAssignee(String assignee) {
        this.aAssignee = assignee;
    }

    public String getAttDate() {return AttDate;}

    public void setAttDate(String attdate) {this.AttDate = attdate;}

    public String getaClockIn() {return aClockIn;}

    public void setaClockIn(String cin) {this.aClockIn = cin;}

    public String getaClockOut() {return aClockOut;}

    public void setaClockOut(String cout) {this.aClockOut = cout;}
}
