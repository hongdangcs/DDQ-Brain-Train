package com.ddq.braintrain.models;

public class AccountModel {
    private String userName;
    private String password;
    private String gender;
    private String dob;
    private String personal_id;

    public AccountModel(String userName, String password, String gender, String dob, String personal_id) {
        this.userName = userName;
        this.password = password;
        this.gender = gender;
        this.dob = dob;
        this.personal_id = personal_id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPersonal_id() {
        return personal_id;
    }

    public void setPersonal_id(String personal_id) {
        this.personal_id = personal_id;
    }

    @Override
    public String toString() {
        return "AccountModel{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", dob='" + dob + '\'' +
                ", personal_id='" + personal_id + '\'' +
                '}';
    }
}
