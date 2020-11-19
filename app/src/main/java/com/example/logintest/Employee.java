package com.example.logintest;

public class Employee {



    private int id;
    private String eName;
    private String eUsername;
    private String ePassword;
    //private Boolean isAdmin = true;

    public Employee() {}

    public Employee(String name, String username, String password){
        this.eName = name;
        this.eUsername = username;
        this.ePassword = password;
    }


    @Override
    public String toString() {
        return "Employee{" +
                ", eName='" + eName + '\'' +
                ", eUsername='" + eUsername + '\'' +
                ", ePassword='" + ePassword + '\'' +
                '}';
    }

    //----- Getters and Setters

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String geteName() {return eName;}

    public void seteName(String eName) {this.eName = eName;}

    public String geteUsername() {return eUsername;}

    public void seteUsername(String eUsername) {this.eUsername = eUsername;}

    public String getePassword() {return ePassword;}

    public void setePassword(String ePassword) {this.ePassword = ePassword;}
}
