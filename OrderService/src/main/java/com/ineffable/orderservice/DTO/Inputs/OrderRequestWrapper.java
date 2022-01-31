package com.ineffable.orderservice.DTO.Inputs;

import java.util.List;

public class OrderRequestWrapper {

    private List<OrderRequest> orders;
    private Long billingId;
    private Long shippingId;
    private String userName;

    public OrderRequestWrapper() {
    }

    public List<OrderRequest> getOrders() {
        return orders;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setOrders(List<OrderRequest> orders) {
        this.orders = orders;
    }

    public Long getBillingId() {
        return billingId;
    }

    public void setBillingId(Long billingId) {
        this.billingId = billingId;
    }

    public Long getShippingId() {
        return shippingId;
    }

    public void setShippingId(Long shippingId) {
        this.shippingId = shippingId;
    }
}
