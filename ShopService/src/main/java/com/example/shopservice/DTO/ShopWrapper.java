package com.example.shopservice.DTO;

import com.example.shopservice.Models.Shop;

import java.util.List;

public class ShopWrapper {
    private List<Shop> shops;

    public ShopWrapper() {
    }

    public List<Shop> getShops() {
        return shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }
}
