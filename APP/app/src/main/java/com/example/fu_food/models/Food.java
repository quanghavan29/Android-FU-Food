package com.example.fu_food.models;

public class Food {

    private String id;
    private String name;
    private int price;
    private String foodType;
    private String imageUrl;
    private int salesQuantity;

    public Food() {
    }

    public Food(String id, String name, int price, String foodType, String imageUrl, int salesQuantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.foodType = foodType;
        this.imageUrl = imageUrl;
        this.salesQuantity = salesQuantity;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getSalesQuantity() {
        return salesQuantity;
    }

    public void setSalesQuantity(int salesQuantity) {
        this.salesQuantity = salesQuantity;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", foodType='" + foodType + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", salesQuantity=" + salesQuantity +
                '}';
    }
}
