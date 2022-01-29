package com.ineffable.productservice.Services;

import com.ineffable.productservice.Exceptions.ProductAlreadyExistsException;
import com.ineffable.productservice.Exceptions.ProductNotFoundException;
import com.ineffable.productservice.Models.Product;
import com.ineffable.productservice.Repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Value("${codes.lowercaseOnly}")
    private String caseSensitive;

    public Product createNew(Product product) throws ProductAlreadyExistsException {

        if(caseSensitive.toLowerCase(Locale.ROOT).equals("true")) {
            product.setShopCode(product.getShopCode().toLowerCase(Locale.ROOT));
            product.setProductCode(product.getShopCode().toLowerCase(Locale.ROOT));
        }
        if(productRepo.existsByProductCodeAndShopCode(product.getProductCode(), product.getShopCode())){
            throw new ProductAlreadyExistsException("Try update if you want to update");
        }
        product.setLastUpdated(new Date(System.currentTimeMillis()));
        return productRepo.insert(product);
    }

    public Boolean existByProductCode(String productCode, String shopCode){
        return productRepo.existsByProductCodeAndShopCode(productCode,shopCode);
    }

    public Product updateProduct(Product product) throws ProductNotFoundException{
        if(caseSensitive.toLowerCase(Locale.ROOT).equals("true")) {
            product.setShopCode(product.getShopCode().toLowerCase(Locale.ROOT));
            product.setProductCode(product.getShopCode().toLowerCase(Locale.ROOT));
        }
        if(!productRepo.existsByProductCodeAndShopCode(product.getProductCode(), product.getShopCode())){
            throw new ProductNotFoundException("Try inserting the product first");
        }
        product.setLastUpdated(new Date(System.currentTimeMillis()));
        return productRepo.save(product);
    }

    public Product getByCode(String productCode, String shopCode) throws ProductNotFoundException{
        if(caseSensitive.toLowerCase(Locale.ROOT).equals("true")) {
            productCode = productCode.toLowerCase(Locale.ROOT);
            shopCode = shopCode.toLowerCase(Locale.ROOT);
        }
        Optional<Product> product = productRepo.findByProductCodeAndShopCode(productCode,shopCode);
        if(product.isEmpty()){
            throw new ProductNotFoundException("Wrong Code");
        }
        return product.get();
    }

    public List<Product>  getAllProducts(){
        return productRepo.findAll();
    }

    public void deleteProduct(String productCode, String shopCode) throws ProductNotFoundException{
        if(caseSensitive.toLowerCase(Locale.ROOT).equals("true")) {
            productCode = productCode.toLowerCase(Locale.ROOT);
            shopCode = shopCode.toLowerCase(Locale.ROOT);
        }
        Optional<Product> product = productRepo.findByProductCodeAndShopCode(productCode,shopCode);
        if(product.isEmpty()){
            throw new ProductNotFoundException("Wrong Code");
        }
        productRepo.deleteById(product.get().getId());
    }


}
