package com.ineffable.orderservice.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document("OrdersByShop")
public class Orders {

    @Id
    private String id;

    private List<SubOrders> subOrders;
    private String shopCode;
    private OrderState orderState; // pending processing shipped etc fronted will decide

    private Date lastUpdated;
    private Long billingId;
    private Long shippingId;

    public Orders() {
        subOrders = new ArrayList<>();
        lastUpdated = new Date(System.currentTimeMillis());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<SubOrders> getSubOrders() {
        return subOrders;
    }

    public void setSubOrders(List<SubOrders> subOrders) {
        this.subOrders = subOrders;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
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
