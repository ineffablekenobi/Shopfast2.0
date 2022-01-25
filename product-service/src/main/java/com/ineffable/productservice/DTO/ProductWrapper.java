package com.ineffable.productservice.DTO;

import com.ineffable.productservice.Models.Product;

import java.util.List;

public class ProductWrapper {
    private List<Product> productList;

    public ProductWrapper() {
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
