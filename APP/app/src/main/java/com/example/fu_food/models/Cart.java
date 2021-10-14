package com.example.fu_food.models;

public class Cart {

    private Restaurant restaurant;
    private Food food;
    private int quantity;
    private int subTotal;

    public Cart() {
    }

    public Cart(Restaurant restaurant, Food food, int quantity, int subTotal) {
        this.restaurant = restaurant;
        this.food = food;
        this.quantity = quantity;
        this.subTotal = subTotal;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(int subTotal) {
        this.subTotal = subTotal;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "restaurant=" + restaurant +
                ", food=" + food +
                ", quantity=" + quantity +
                ", subTotal=" + subTotal +
                '}';
    }
}
