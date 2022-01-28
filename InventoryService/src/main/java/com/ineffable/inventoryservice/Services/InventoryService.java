package com.ineffable.inventoryservice.Services;

import com.ineffable.inventoryservice.DTO.InventoryWrapper;
import com.ineffable.inventoryservice.Exceptions.InventoryNotFoundException;
import com.ineffable.inventoryservice.Exceptions.StockOverflowException;
import com.ineffable.inventoryservice.Exceptions.WareHouseNotRegisteredException;
import com.ineffable.inventoryservice.Models.ProductInventory;
import com.ineffable.inventoryservice.Repositories.InventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.SocketTimeoutException;
import java.util.Map;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepo inventoryRepo;

    public ProductInventory getBySku(String Sku) throws Exception{
        Optional<ProductInventory> productInventoryOptional = inventoryRepo.findBySku(Sku);
        if(productInventoryOptional.isEmpty()){
            throw new InventoryNotFoundException("Cant get Inventory by sku");
        }
        return productInventoryOptional.get();
    }

    public ProductInventory addNew(ProductInventory productInventory){
        // create new inventory
        return inventoryRepo.insert(productInventory);
    }

    public ProductInventory updateInventory(ProductInventory productInventory) throws InventoryNotFoundException {
        // we add the new inventories to the existing inventory
        Optional<ProductInventory> productInventoryOptional = inventoryRepo.findBySku(productInventory.getSku());
        if(productInventoryOptional.isEmpty()){
            throw new InventoryNotFoundException("Cant update Inventory");
        }
        Map<String,Long> warehouses = productInventory.getProductsPerWareHouse();
        ProductInventory dbInventory = productInventoryOptional.get();
        for(Map.Entry<String,Long> entry : warehouses.entrySet()){
           Long existingVal = dbInventory.getProductsPerWareHouse().get(entry.getKey());
           if(existingVal == null){
               existingVal = 0L;
           }
           existingVal += entry.getValue();;
           dbInventory.getProductsPerWareHouse().put(entry.getKey(),existingVal);
        }
        return inventoryRepo.save(dbInventory);
    }

    public Long InventoryCheck(String wareHouseCode, String sku) throws InventoryNotFoundException, WareHouseNotRegisteredException {
        Optional<ProductInventory> productInventoryOptional = inventoryRepo.findBySku(sku);
        if(productInventoryOptional.isEmpty()){
            throw new InventoryNotFoundException("Inventory Check Failed");
        }
        if(productInventoryOptional.get().getProductsPerWareHouse().get(wareHouseCode)==null){
            throw new WareHouseNotRegisteredException("Inventory Check Failed");
        }
        return productInventoryOptional.get().getProductsPerWareHouse().get(wareHouseCode);
    }

    public void deductQuantity(String wareHouseCode, String sku, Long quantity) throws InventoryNotFoundException, WareHouseNotRegisteredException,
            StockOverflowException {
        Optional<ProductInventory> productInventoryOptional = inventoryRepo.findBySku(sku);
        if(productInventoryOptional.isEmpty()){
            throw new InventoryNotFoundException("Inventory Deduct Failed");
        }
        if(productInventoryOptional.get().getProductsPerWareHouse().get(wareHouseCode)==null){
            throw new WareHouseNotRegisteredException("Inventory Deduct Failed");
        }
        if(quantity > productInventoryOptional.get().getProductsPerWareHouse().get(wareHouseCode)){
            throw new StockOverflowException("Inventory Deduct Failed");
        }
        Map<String, Long> warehouse = productInventoryOptional.get().getProductsPerWareHouse();
        Long existingStock = warehouse.get(wareHouseCode);
        existingStock-= quantity;
        warehouse.put(wareHouseCode,existingStock);
        productInventoryOptional.get().setProductsPerWareHouse(warehouse);
        inventoryRepo.save(productInventoryOptional.get());
    }

    public InventoryWrapper getAll() {
        InventoryWrapper inventoryWrapper= new InventoryWrapper();
        inventoryWrapper.setProductInventories(inventoryRepo.findAll());
        return inventoryWrapper;
    }
}
