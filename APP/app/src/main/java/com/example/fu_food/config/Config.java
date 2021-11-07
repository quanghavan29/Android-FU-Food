package com.example.fu_food.config;

public class Config {
    public final static String apiUrl = "http:192.168.0.102:8081/";
    public final static String imageUrl = "https://res.cloudinary.com/fpt-food/image/upload/v1633196770/FPT%20FOOD/";

    public Config() {
    }

    public static String getApiUrl() {
        return apiUrl;
    }

    public static String getImageUrl() {
        return imageUrl;
    }
}