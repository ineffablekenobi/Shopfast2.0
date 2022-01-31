package com.ineffable.orderservice.DTO;

import com.ineffable.orderservice.DTO.Inputs.OrderRequest;

public class OrderRequestValidation {
    private OrderRequest orderRequest;
    private Boolean validated;

    public OrderRequestValidation() {
    }

    public OrderRequest getOrderRequest() {
        return orderRequest;
    }

    public void setOrderRequest(OrderRequest orderRequest) {
        this.orderRequest = orderRequest;
    }

    public Boolean getValidated() {
        return validated;
    }

    public void setValidated(Boolean validated) {
        this.validated = validated;
    }
}
