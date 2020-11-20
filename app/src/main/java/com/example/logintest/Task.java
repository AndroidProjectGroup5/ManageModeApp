package com.example.logintest;

public class Task extends Employee {

    private int tID;
    private String tName;
    private String tDescr;
    private String tAssignee;
    private String tStat;

    public Task(){}

    public Task(String taskname, String taskdesc, String taskstatus){
        this.tName = taskname;
        this.tDescr = taskdesc;
        this.tAssignee = super.geteName();
        this.tStat = taskstatus;
    }

    @Override
    public String toString() {
        return "Task{" +
                ", tAssignee='" + tAssignee + '\'' +
                ", tName='" + tName + '\'' +
                ", tDescr='" + tDescr + '\'' +
                ", tStat='" + tStat + '\'' +
                '}';
    }

    public int gettId() {
        return this.tID;
    }

    public void settId(int id) {
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
        return this.tAssignee;
    }

    public void settAssignee(String assignee) {
        this.tAssignee = assignee;
    }

    public String gettStatus() {
        return this.tStat;
    }

    public void settStatus(String stat) {
        this.tStat = stat;
    }

}
