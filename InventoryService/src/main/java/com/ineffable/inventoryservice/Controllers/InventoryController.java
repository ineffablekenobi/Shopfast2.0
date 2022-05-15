package com.ineffable.inventoryservice.Controllers;

import com.ineffable.inventoryservice.DTO.InventoryWrapper;
import com.ineffable.inventoryservice.Models.ProductInventory;
import com.ineffable.inventoryservice.Services.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import jdk.jfr.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @Operation(summary = "Create New or Add to existing inventory",
    description = "To add new - " +
            "Send a product inventory object." +
            "To add to existing inventory -" +
            "Send a product inventory object." +
            "The quantity use sent will be added to the existing quantities"
    )
    @PostMapping("/add")
    public ResponseEntity<?> addProductInventory(@RequestBody ProductInventory productInventory){
        if(productInventory.getSku().equals("")){
            productInventory.generateSku();
        }else {
            //sku already exist. adds to existing inventory

            try {
                ProductInventory inventory = inventoryService.getBySku(productInventory.getSku());
                inventoryService.updateInventory(inventory);
            }catch (Exception e){
                return ResponseEntity.badRequest().build();
            }

        }

        try {

            ProductInventory response = inventoryService.addNew(productInventory);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get Quantity by providing warehouse and sku")
    @GetMapping("/quantityleft/warehousecode={warehousecode}&sku={sku}")
    public ResponseEntity<?> getQuantity(@PathVariable("warehousecode")String wareHouseCode, @PathVariable("sku")String sku){
        try {
            Long response = inventoryService.InventoryCheck(wareHouseCode,sku);
            return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/exists/sku={sku}")
    public ResponseEntity<Boolean> existsBySku(@PathVariable("sku") String sku){
        return ResponseEntity.ok(inventoryService.existsBySku(sku));
    }

    @PostMapping("/exists")
    public ResponseEntity<Boolean> existsByInventory(@RequestBody ProductInventory productInventory){
        String sku = productInventory.generateSku();
        return ResponseEntity.ok(inventoryService.existsBySku(sku));
    }

    @PutMapping("/exists")
    @Operation(summary = "Get SKU by providing shopcode,productcode,variants",
    description = "Example code - \n" +
            "{\n" +
            "  \"id\": \"string\",\n" +
            "  \"shopCode\": \"CFK\",\n" +
            "  \"productsPerWareHouse\": {\n" +
            "    \"additionalProp1\": 0,\n" +
            "    \"additionalProp2\": 0,\n" +
            "    \"additionalProp3\": 0\n" +
            "  },\n" +
            "  \"productCode\": \"CFT-200\",\n" +
            "  \"variants\": [\n" +
            "    {\n" +
            "      \"name\": \"COLOR\",\n" +
            "      \"value\": \"RED\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"SIZE\",\n" +
            "      \"value\": \"M\"\n" +
            "    }\n" +
            "\n" +
            "  ],\n" +
            "  \"sku\": \"string\"\n" +
            "}")

    public ResponseEntity<?> getInventorySku(@RequestBody ProductInventory productInventory){
        String sku = productInventory.generateSku();
        if(inventoryService.existsBySku(sku)){
            return ResponseEntity.ok(sku);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/quantityleft/sku={sku}")

    public ResponseEntity<?> getTotalQuantityBySku(@PathVariable("sku") String sku){
        try {
            return ResponseEntity.ok(inventoryService.getTotalQuantityBySku(sku));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/quantityleft")
    @Operation(summary = "Get total Quantity of product from all warehouse(NO SKU REQUIRED)",
    description = "Dont need provide the SKU. \n Just Provide the object. " +
            "\n ProductCode, ShopCode, Variants are mandatory")
    public ResponseEntity<?> getTotalQuantityByInventory(@RequestBody ProductInventory productInventory){
        try {
            return ResponseEntity.ok(inventoryService.getTotalQuantityBySku(productInventory.generateSku()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(0L);
        }
    }


    @Operation(summary = "Deduct Quantity by providing warehouse and sku",
            description = "If you are not using warehouse feature just provide warehouse unknowns"
    )
    @PostMapping("/quantitydeduct/warehousecode={warehousecode}&sku={sku}&quantity={quantity}")
    public ResponseEntity deductQuantity(@PathVariable("warehousecode")String wareHouseCode, @PathVariable("sku")String sku,
                                         @PathVariable("quantity") Long quantity){
        try {
            inventoryService.deductQuantity(wareHouseCode,sku,quantity);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<InventoryWrapper> getAll(){
        return ResponseEntity.ok(inventoryService.getAll());
    }



}
