package com.ineffable.orderservice.Models;

import java.util.ArrayList;
import java.util.List;

public class SubOrders {
    private String productCode;
    private List<Variant> variants;
    private Long quantity;
    private Double pricePerUnit;

    public SubOrders() {
        variants = new ArrayList<>();
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
}
