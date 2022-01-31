package com.ineffable.orderservice.DTO;

import com.ineffable.orderservice.Models.OrderWrapper;

import java.util.List;

public class OrderRequestValidationWrapper {
    private List<OrderRequestValidation> validations;
    private Boolean successfullyValidated;
    private OrderWrapper orderWrapper;

    public OrderRequestValidationWrapper() {
    }

    public List<OrderRequestValidation> getValidations() {
        return validations;
    }

    public void setValidations(List<OrderRequestValidation> validations) {
        this.validations = validations;
    }

    public Boolean getSuccessfullyValidated() {
        return successfullyValidated;
    }

    public void setSuccessfullyValidated(Boolean successfullyValidated) {
        this.successfullyValidated = successfullyValidated;
    }

    public OrderWrapper getOrderWrapper() {
        return orderWrapper;
    }

    public void setOrderWrapper(OrderWrapper orderWrapper) {
        this.orderWrapper = orderWrapper;
    }
}
