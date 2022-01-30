package com.ineffable.shippingservice.Repositories;

import com.ineffable.shippingservice.Models.ShippingCharge;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ShippingRepo extends MongoRepository<ShippingCharge,String> {
    Optional<ShippingCharge> findByShopCode(String shopCode);
    Boolean existsByShopCode(String shopCode);
}
