package com.example.fu_food.models;

public class UserSignIn {

    private User user;
    private int statusCode;
    private String message;

    public UserSignIn() {
    }

    public UserSignIn(User user, int statusCode, String message) {
        this.user = user;
        this.statusCode = statusCode;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "UserSignIn{" +
                "user=" + user +
                ", statusCode=" + statusCode +
                ", message='" + message + '\'' +
                '}';
    }
}
