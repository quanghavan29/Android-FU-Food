package com.example.fu_food.models;

public class Authority {

    private String name;

    public Authority() {
    }

    public Authority(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "name='" + name + '\'' +
                '}';
    }
}
