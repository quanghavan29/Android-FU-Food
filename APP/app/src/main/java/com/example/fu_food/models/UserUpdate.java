package com.example.fu_food.models;

public class UserUpdate {
    private int status;

    public UserUpdate() {

    }

    public UserUpdate(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
