package com.ineffable.inventoryservice.DTO;

import com.ineffable.inventoryservice.Models.ProductInventory;

import java.util.List;

public class InventoryWrapper {
    private List<ProductInventory> productInventories;

    public InventoryWrapper() {
    }

    public List<ProductInventory> getProductInventories() {
        return productInventories;
    }

    public void setProductInventories(List<ProductInventory> productInventories) {
        this.productInventories = productInventories;
    }
}
