package com.ineffable.productservice.Repositories;

import com.ineffable.productservice.Models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepo extends MongoRepository<Product,String> {
    Optional<Product> findByProductCode(String productCode);
    Boolean existsByProductCode(String productCode);
}
