package com.ineffable.productservice.Controllers;

import com.ineffable.productservice.DTO.ProductWrapper;
import com.ineffable.productservice.Models.Product;
import com.ineffable.productservice.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ResponseEntity<ProductWrapper> getAll(){
        ProductWrapper productWrapper = new ProductWrapper();
        productWrapper.setProductList(productService.getAllProducts());
        return ResponseEntity.ok(productWrapper);
    }

    @PostMapping("/new")
    public ResponseEntity<?> createNewProduct(@RequestBody Product product){
        try {
            Product response = productService.createNew(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/productcode={productCode}")
    public ResponseEntity<?> getByProductCode(@PathVariable("productCode") String productCode){
        try {
            Product response = productService.getByCode(productCode);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateExistingProduct(@RequestBody Product product){
        try {
            Product response = productService.updateProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/delete/productcode={productCode}")
    public ResponseEntity<?> deleteProduct(@PathVariable("productCode") String productCode){
        try {
            productService.deleteProduct(productCode);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }


}
