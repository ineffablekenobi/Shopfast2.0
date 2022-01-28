package com.ineffable.inventoryservice.Models;

import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Document("ProductInventory")
public class ProductInventory {

    // this is a product Inventory

    @Id
    private String id;

    @NotNull
    private String shopCode;


/*
    A product can have its quantity share among it's different warehouses
    for instance -
    a product has total 50 units where
    warehouseName[String] : Quantity[Long]
    warehouse 1 : 20
    warehouse 2 : 30

 */
    private Map<String, Long> productsPerWareHouse;

    @NotNull
    private String productCode;

    /*
    * As it is a product inventory a product can be a part of a list of variants
    * for instance -
    * a t-shirt[Product] is of M size[variant 1] and color blue[variant 2]
    * */
    private List<Variant> variants;


    /*
        this will uniquely identify a product in stock. In other words, its a unique code
        of the product inventory.
     */
    @Indexed(unique = true)
    private String sku;

    public ProductInventory() {
        this.sku = "";
        variants = new ArrayList<>();
        productsPerWareHouse = new HashMap<>();
    }

    public String generateSku(){
        this.sku = "";
        this.sku += this.shopCode+"-";
        this.sku += this.productCode+"-";
        if(this.variants.size() != 0){
            for(int i = 0; i < this.variants.size() - 1; i++){
                this.sku += variants.get(i).toString()+"-";
            }
            this.sku += variants.get(variants.size() - 1).toString();
        }
        return this.sku;
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

    public Map<String, Long> getProductsPerWareHouse() {
        return productsPerWareHouse;
    }

    public void setProductsPerWareHouse(Map<String, Long> productsPerWareHouse) {
        this.productsPerWareHouse = productsPerWareHouse;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }


    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
}
