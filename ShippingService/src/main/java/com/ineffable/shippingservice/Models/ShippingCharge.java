package com.ineffable.shippingservice.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Document("ShippingCharge")
public class ShippingCharge {

    @Id
    private String id;

    private String shopCode;

    private Double globalCharge;
    private List<GeoCharge> chargeByGeo;

    public ShippingCharge() {
        globalCharge = 1e18;
        chargeByGeo = new ArrayList<>();
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

    public Double getGlobalCharge() {
        return globalCharge;
    }

    public void setGlobalCharge(Double globalCharge) {
        this.globalCharge = globalCharge;
    }

    public List<GeoCharge> getChargeByGeo() {
        return chargeByGeo;
    }

    public void setChargeByGeo(List<GeoCharge> chargeByGeo) {
        this.chargeByGeo = chargeByGeo;
    }
}
