package com.ineffable.orderservice.DTO.Inputs;


import com.ineffable.orderservice.Models.Variant;

import java.util.List;

public class OrderRequest {

    private String shopCode;
    private String productCode;
    private Long quantity;
    private List<Variant> variants;
    /*
    Api user can add addition discounts as properties in the product that we might not be aware of
    So we are taking pricePerUnit as an input
     */
    private Double pricePerUnit;

    public OrderRequest() {
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
}
