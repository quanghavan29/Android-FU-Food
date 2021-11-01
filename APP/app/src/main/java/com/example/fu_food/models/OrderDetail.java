package com.example.fu_food.models;

public class OrderDetail {
    private Order order;
    private Food food;
    private int quantity;
    private int subAmount;

    public OrderDetail() {
    }

    public OrderDetail(Order order, Food food, int quantity, int subAmount) {
        this.order = order;
        this.food = food;
        this.quantity = quantity;
        this.subAmount = subAmount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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

    public int getSubAmount() {
        return subAmount;
    }

    public void setSubAmount(int subAmount) {
        this.subAmount = subAmount;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "order=" + order +
                ", food=" + food +
                ", quantity=" + quantity +
                ", subAmount=" + subAmount +
                '}';
    }

}
