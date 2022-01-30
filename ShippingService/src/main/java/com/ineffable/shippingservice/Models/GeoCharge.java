package com.ineffable.shippingservice.Models;

public class GeoCharge {
    private Geo geo;
    private Double charge;

    public GeoCharge() {
    }

    public Geo getGeo() {
        return geo;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }

    public Double getCharge() {
        return charge;
    }

    public void setCharge(Double charge) {
        this.charge = charge;
    }
}
