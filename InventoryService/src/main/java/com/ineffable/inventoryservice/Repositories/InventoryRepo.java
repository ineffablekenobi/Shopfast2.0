package com.ineffable.inventoryservice.Repositories;

import com.ineffable.inventoryservice.Models.ProductInventory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.awt.*;
import java.util.Optional;

public interface InventoryRepo extends MongoRepository<ProductInventory,String> {
    Optional<ProductInventory> findBySku(String Sku);
    Boolean existsBySku(String Sku);

}
