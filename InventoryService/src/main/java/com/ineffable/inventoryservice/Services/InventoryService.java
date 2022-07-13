package com.ineffable.inventoryservice.Services;

import com.ineffable.inventoryservice.DTO.InventoryWrapper;
import com.ineffable.inventoryservice.Exceptions.InventoryNotFoundException;
import com.ineffable.inventoryservice.Exceptions.StockOverflowException;
import com.ineffable.inventoryservice.Exceptions.WareHouseNotRegisteredException;
import com.ineffable.inventoryservice.Models.ProductInventory;
import com.ineffable.inventoryservice.Repositories.InventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepo inventoryRepo;

    @Autowired
    private InventoryWrapper inventoryWrapper;

    @Autowired
    private APICheckService apiCheckService;



    public ProductInventory getBySku(String Sku) throws Exception{
        Optional<ProductInventory> productInventoryOptional = inventoryRepo.findBySku(Sku);
        if(productInventoryOptional.isEmpty()){
            throw new InventoryNotFoundException("Cant get Inventory by sku");
        }
        return productInventoryOptional.get();
    }

    public ProductInventory addNew(ProductInventory productInventory) throws NoSuchFieldException {
        // create new inventory
        apiCheckService.inventoryInsertableCheck(productInventory.getProductCode(), productInventory.getShopCode());
        return inventoryRepo.insert(productInventory);
    }

    public ProductInventory updateInventory(ProductInventory productInventory) throws InventoryNotFoundException, NoSuchFieldException {
        // we add the new inventories to the existing inventory

        apiCheckService.inventoryInsertableCheck(productInventory.getProductCode(), productInventory.getShopCode());
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


    public Boolean existsBySku(String sku){
        return inventoryRepo.existsBySku(sku);
    }

    public InventoryWrapper getAll() {
        inventoryWrapper.setProductInventories(inventoryRepo.findAll());
        return inventoryWrapper;
    }

    public Long getTotalQuantityBySku(String sku) throws InventoryNotFoundException {
        Optional<ProductInventory> productInventoryOptional = inventoryRepo.findBySku(sku);
        if(productInventoryOptional.isEmpty()){
            throw new InventoryNotFoundException("Cant get Inventory By Sku");
        }

        Long totalQuantity = 0L;

        Map<String,Long> products = productInventoryOptional.get().getProductsPerWareHouse();

        for(Map.Entry<String,Long> entry: products.entrySet()){
            totalQuantity+= entry.getValue();
        }

        return totalQuantity;

    }

    public InventoryWrapper getAllInventoriesOfProduct(String shopCode, String productCode){
        List<ProductInventory> productInventories = inventoryRepo.findByShopCodeAndProductCode(shopCode,productCode);
        inventoryWrapper.setProductInventories(productInventories);
        return inventoryWrapper;
    }

}
