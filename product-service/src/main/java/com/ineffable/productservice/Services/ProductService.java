package com.ineffable.productservice.Services;

import com.ineffable.productservice.Exceptions.ProductAlreadyExistsException;
import com.ineffable.productservice.Exceptions.ProductNotFoundException;
import com.ineffable.productservice.Models.Product;
import com.ineffable.productservice.Repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public Product createNew(Product product) throws ProductAlreadyExistsException {
        if(productRepo.existsByProductCode(product.getProductCode())){
            throw new ProductAlreadyExistsException("Try update if you want to update");
        }
        product.setLastUpdated(new Date(System.currentTimeMillis()));
        return productRepo.insert(product);
    }

    public Product updateProduct(Product product) throws ProductNotFoundException{
        if(!productRepo.existsByProductCode(product.getProductCode())){
            throw new ProductNotFoundException("Try inserting the product first");
        }
        product.setLastUpdated(new Date(System.currentTimeMillis()));
        return productRepo.save(product);
    }

    public Product getByCode(String productCode) throws ProductNotFoundException{
        Optional<Product> product = productRepo.findByProductCode(productCode);
        if(product.isEmpty()){
            throw new ProductNotFoundException("Wrong Code");
        }
        return product.get();
    }

    public List<Product>  getAllProducts(){
        return productRepo.findAll();
    }

    public void deleteProduct(String productCode) throws ProductNotFoundException{
        Optional<Product> product = productRepo.findByProductCode(productCode);
        if(product.isEmpty()){
            throw new ProductNotFoundException("Wrong Code");
        }
        productRepo.deleteById(product.get().getId());
    }


}
