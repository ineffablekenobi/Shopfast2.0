package com.ineffable.inventoryservice.Controllers;

import com.ineffable.inventoryservice.DTO.InventoryWrapper;
import com.ineffable.inventoryservice.Models.ProductInventory;
import com.ineffable.inventoryservice.Services.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Deduct Quantity by providing warehouse and sku"
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
