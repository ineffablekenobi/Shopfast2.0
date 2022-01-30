package com.ineffable.shippingservice.DTO;

import com.ineffable.shippingservice.Models.ShippingCharge;

import java.util.List;

public class ShippingChargeWrapper {
    private List<ShippingCharge> shippingCharges;

    public ShippingChargeWrapper() {
    }

    public List<ShippingCharge> getShippingCharges() {
        return shippingCharges;
    }

    public void setShippingCharges(List<ShippingCharge> shippingCharges) {
        this.shippingCharges = shippingCharges;
    }
}
