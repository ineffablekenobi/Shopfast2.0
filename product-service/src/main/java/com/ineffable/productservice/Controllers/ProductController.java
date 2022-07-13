package com.ineffable.productservice.Controllers;

import com.ineffable.productservice.DTO.ProductWrapper;
import com.ineffable.productservice.Models.Product;
import com.ineffable.productservice.Services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "Get all products")
    @GetMapping("/")
    public ResponseEntity<ProductWrapper> getAll(){
        ProductWrapper productWrapper = new ProductWrapper();
        productWrapper.setProductList(productService.getAllProducts());
        return ResponseEntity.ok(productWrapper);
    }

    @Operation(summary = "Check if exists a product")
    @GetMapping("/exists/productcode={productcode}&shopcode={shopcode}")
    public ResponseEntity<Boolean> existByProductCode(@PathVariable("productcode")String productCode ,@PathVariable("shopcode") String shopCode){
        return ResponseEntity.ok(productService.existByProductCode(productCode,shopCode));
    }

    @Operation(summary = "Create new product")
    @PostMapping("/new")
    public ResponseEntity<?> createNewProduct(@RequestBody Product product){
        try {
            Product response = productService.createNew(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Get product By product code and shop code")
    @GetMapping("/productcode={productCode}&shopcode={shopcode}")
    public ResponseEntity<?> getByProductCode(@PathVariable("productCode") String productCode, @PathVariable("shopcode")String shopCode){
        try {
            Product response = productService.getByCode(productCode,shopCode);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Update a product details.")
    @PutMapping("/update")
    public ResponseEntity<?> updateExistingProduct(@RequestBody Product product){
        try {
            Product response = productService.updateProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Delete the product. Make sure the ID is set.")
    @DeleteMapping("/delete/productcode={productCode}&shopcode={shopcode}")
    public ResponseEntity<?> deleteProduct(@PathVariable("productCode") String productCode, @PathVariable("shopcode")String shopCode){
        try {
            productService.deleteProduct(productCode,shopCode);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }


}
