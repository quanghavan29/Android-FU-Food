package com.example.fu_food.config;

public class Config {
    public final static String apiUrl = "http:192.168.0.100:8081/";

    public Config() {
    }

    public static String getApiUrl() {
        return apiUrl;
    }
}