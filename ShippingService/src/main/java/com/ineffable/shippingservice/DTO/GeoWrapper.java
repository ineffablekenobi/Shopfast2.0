package com.ineffable.shippingservice.DTO;

import com.ineffable.shippingservice.Models.Geo;

import java.util.List;

public class GeoWrapper {
    private List<Geo> geos;

    public GeoWrapper() {
    }

    public List<Geo> getGeos() {
        return geos;
    }

    public void setGeos(List<Geo> geos) {
        this.geos = geos;
    }
}
