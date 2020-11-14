package com.example.logintest;

public class Task {

    private int tID;
    private String tName;
    private String tDescr;
    private String tAssign;
    private String tStat;

    public Task(){

    }

    public Task(String taskname, String taskdesc, String taskassignee, String taskstatus){
        this.tName = taskname;
        this.tDescr = taskdesc;
        this.tAssign = taskassignee;
        this.tStat = taskstatus;
    }

    public int getId() {
        return this.tID;
    }

    public void setId(int id) {
        this.tID = id;
    }

    public String gettName() {
        return this.tName;
    }

    public void settName(String name) {
        this.tName = name;
    }

    public String gettDescription() {
        return this.tDescr;
    }

    public void settDescription(String dscp) {
        this.tDescr = dscp;
    }

    public String gettAssignee() {
        return this.tAssign;
    }

    public void settAssignee(String assg) {
        this.tAssign = assg;
    }

    public String gettStatus() {
        return this.tStat;
    }

    public void settStatus(String stat) {
        this.tStat = stat;
    }

}
