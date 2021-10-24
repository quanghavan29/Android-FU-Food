package com.example.fu_food.models;

public class Cart {

    private User user;
    private Restaurant restaurant;
    private Food food;
    private int quantity;

    public Cart() {
    }

    public Cart(User user, Restaurant restaurant, Food food, int quantity) {
        this.user = user;
        this.restaurant = restaurant;
        this.food = food;
        this.quantity = quantity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    @Override
    public String toString() {
        return "Cart{" +
                "user=" + user +
                ", restaurant=" + restaurant +
                ", food=" + food +
                ", quantity=" + quantity +
                '}';
    }

}
