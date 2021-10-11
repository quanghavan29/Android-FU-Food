package com.example.fu_food.models;

import java.util.List;

public class User {

    private String id;
    private String fullName;
    private String phone;
    private String password;
    private String email;
    private String imageUrl;
    private List<Authority> authorities;

    public User() {
    }

    public User(String id, String fullName, String phone, String password, String email, String imageUrl, List<Authority> authorities) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.password = password;
        this.email = email;
        this.imageUrl = imageUrl;
        this.authorities = authorities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }
}
