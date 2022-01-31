package com.example.shopservice.Repositories;

import com.example.shopservice.Models.Shop;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ShopRepo extends MongoRepository<Shop, String> {
    Optional<Shop> findByShopCode(String shopCode);
    Boolean existsByShopCode(String shopCode);
}
