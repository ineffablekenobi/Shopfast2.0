package com.ineffable.orderservice.Repostitories;

import com.ineffable.orderservice.Models.Orders;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrdersRepo extends MongoRepository<Orders,String> {
    @Query("{ 'id' : ?0 }")
    Optional<Orders> findById(String id);
    List<Orders> findByShopCode(String shopCode);

}
