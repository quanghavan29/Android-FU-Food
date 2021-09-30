package com.example.fu_food.models;

public class Restaurant {

    private String id;
    private String name;
    private String imageUrl;
    private String address;

    public Restaurant() {
    }

    public Restaurant(String id, String name, String imageUrl, String address) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
