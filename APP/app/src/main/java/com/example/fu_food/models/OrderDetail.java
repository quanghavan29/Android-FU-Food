package com.example.fu_food.models;

import java.util.List;

public class OrderDetail {

    private Order order;
    private List<OrderItem> orderItems;

    public OrderDetail() {

    }

    public OrderDetail(Order order, List<OrderItem> orderItems) {
        this.order = order;
        this.orderItems = orderItems;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "order=" + order +
                ", orderItems=" + orderItems +
                '}';
    }
}
