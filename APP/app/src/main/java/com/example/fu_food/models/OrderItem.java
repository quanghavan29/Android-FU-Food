package com.example.fu_food.models;

public class OrderItem {
    private String imageFood;
    private String foodName;
    private int subQuantity;
    private int subAmount;

    public OrderItem() {
    }

    public OrderItem(String imageFood, String foodName, int subQuantity, int subAmount) {
        this.imageFood = imageFood;
        this.foodName = foodName;
        this.subQuantity = subQuantity;
        this.subAmount = subAmount;
    }

    public String getImageFood() {
        return imageFood;
    }

    public void setImageFood(String imageFood) {
        this.imageFood = imageFood;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getSubQuantity() {
        return subQuantity;
    }

    public void setSubQuantity(int subQuantity) {
        this.subQuantity = subQuantity;
    }

    public int getSubAmount() {
        return subAmount;
    }

    public void setSubAmount(int subAmount) {
        this.subAmount = subAmount;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "imageFood='" + imageFood + '\'' +
                ", foodName='" + foodName + '\'' +
                ", subQuantity=" + subQuantity +
                ", subAmount=" + subAmount +
                '}';
    }
}
