package com.example.fu_food.models;

import java.util.Date;

public class Order {

    private String id;
    private User user;
    private Date orderedDate;
    private String address;
    private int totalAmount;
    private int totalQuantity;

    public Order() {
    }

    public Order(String id, User user, Date orderedDate, String address, int totalAmount, int totalQuantity) {
        this.id = id;
        this.user = user;
        this.orderedDate = orderedDate;
        this.address = address;
        this.totalAmount = totalAmount;
        this.totalQuantity = totalQuantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(Date orderedDate) {
        this.orderedDate = orderedDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", user=" + user +
                ", orderedDate=" + orderedDate +
                ", address='" + address + '\'' +
                ", totalAmount=" + totalAmount +
                '}';
    }

}
