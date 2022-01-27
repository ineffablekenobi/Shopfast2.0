package com.ineffable.productservice.Repositories;

import com.ineffable.productservice.Models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends MongoRepository<Product,String> {
    Optional<Product> findByProductCodeAndShopCode(String productCode, String ShopCode);
    Boolean existsByProductCodeAndShopCode(String productCode, String ShopCode);

    @Override
    List<Product> findAll();
}
