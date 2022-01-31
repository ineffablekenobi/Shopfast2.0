package com.ineffable.orderservice.Models;

import com.ineffable.orderservice.Models.Orders;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document("Orders")
public class OrderWrapper {
    @Id
    private String id;

    @NotNull(message = "username cant be null")
    private String userName;
    @Schema(hidden = true)
    private Date creationTime;
    @NotNull(message = "orders list cant be null")
    private List<Orders> orders;

    public OrderWrapper() {
        orders = new ArrayList<>();
        creationTime = new Date(System.currentTimeMillis());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }
}
