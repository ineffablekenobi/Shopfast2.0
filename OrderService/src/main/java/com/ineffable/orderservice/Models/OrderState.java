package com.ineffable.orderservice.Models;

public enum OrderState {
    PENDING,
    CONFIRMED,
    PROCESSING,
    SHIPPING,
    SHIPPED,
    DELIVERED;

    public String getState(){
        return this.name();
    }

}
