package com.example.fu_food.models;

public class FoodCategory {

    private String id;
    private String name;
    private int image;

    public FoodCategory() {
    }

    public FoodCategory(String id, String name, int image) {
        this.id = id;
        this.name = name;
        this.image = image;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "FoodCategory{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", image=" + image +
                '}';
    }
}
