package com.example.fu_food.models;

public class UserSignIn {

    private User user;
    private int statusCode;
    private String errorMessage;

    public UserSignIn() {
    }

    public UserSignIn(User user, int statusCode, String errorMessage) {
        this.user = user;
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "UserSignIn{" +
                "user=" + user +
                ", statusCode=" + statusCode +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
