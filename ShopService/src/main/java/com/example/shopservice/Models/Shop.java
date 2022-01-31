package com.example.shopservice.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Document("Shop")
public class Shop {
    @Id
    private String id;

    @Indexed(unique = true)
    @NotNull(message = "ShopCode Cant be Null")
    private String shopCode; // user can select

    private String shopName;
    private String shopAddress;
    private String shopWebsite;
    private String shopType;
    private List<String> phoneNumber; // customer Help
    private List<String> email; // customer help
    private Boolean usingWareHouseFeature;


    public Shop() {
        this.phoneNumber = new ArrayList<>();
        this.email = new ArrayList<>();
        this.usingWareHouseFeature = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopWebsite() {
        return shopWebsite;
    }

    public void setShopWebsite(String shopWebsite) {
        this.shopWebsite = shopWebsite;
    }

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType;
    }

    public List<String> getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(List<String> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public Boolean getUsingWareHouseFeature() {
        return usingWareHouseFeature;
    }

    public void setUsingWareHouseFeature(Boolean usingWareHouseFeature) {
        this.usingWareHouseFeature = usingWareHouseFeature;
    }
}
