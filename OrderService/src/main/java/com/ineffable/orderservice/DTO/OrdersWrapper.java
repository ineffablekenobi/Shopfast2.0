package com.ineffable.orderservice.DTO;

import com.ineffable.orderservice.Models.Orders;

import java.util.List;

public class OrdersWrapper {
    private String shopCode;
    private List<Orders> orders;

    public OrdersWrapper() {
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }
}
