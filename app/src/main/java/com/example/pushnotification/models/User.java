package com.example.pushnotification.models;

public class User {

    private String userEmail;
    private String userToken;

    public User() {
    }

    public User(String userEmail, String userToken) {
        this.userEmail = userEmail;
        this.userToken = userToken;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }


    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }


}
