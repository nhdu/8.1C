package com.example.a81c_task;

import java.util.ArrayList;

public class UserData {
    public String userName;
    public String userPassword;
    public String userLink;
    public int userID;
    public static ArrayList<UserData> itemList = new ArrayList<>();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserLink() {
        return userLink;
    }

    public void setUserLink(String userLink) {
        this.userLink = userLink;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public UserData(String userName, String userPassword, String userLink, int userID) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userLink = userLink;
        this.userID = userID;
    }
}
