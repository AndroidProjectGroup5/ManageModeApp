package com.example.logintest;

public class Task extends Employee {

    private int task_id;
    private int EmployeeID;
    private String TaskName;
    private String TaskDescription;
    private String TaskStatus;

    public Task(){}

    public Task(String taskname, String taskdesc, String taskstatus){
        this.TaskName = taskname;
        this.TaskDescription = taskdesc;
        this.TaskStatus = taskstatus;
    }

    @Override
    public String toString() {
        return "Task{" +
                ", EmployeeID='" + EmployeeID + '\'' +
                ", TaskName='" + TaskName + '\'' +
                ", TaskDescription='" + TaskDescription + '\'' +
                ", TaskStatus='" + TaskStatus + '\'' +
                '}';
    }

    public int getId() {
        return this.task_id;
    }

    public void settId(int id) {
        this.task_id = id;
    }

    public String getTaskName() {
        return this.TaskName;
    }

    public void setTaskName(String name) {
        this.TaskName = name;
    }

    public String getDescription() {
        return this.TaskDescription;
    }

    public int getEmployeeID() {return this.EmployeeID;}

    public void setEmployeeID(int employeeID) {this.EmployeeID = employeeID;}

    public void setDescription(String dscp) {
        this.TaskDescription = dscp;
    }

    public String getStatus() {
        return this.TaskStatus;
    }

    public void setStatus(String stat) {
        this.TaskStatus = stat;
    }

}
