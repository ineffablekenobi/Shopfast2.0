package com.ineffable.orderservice.Repostitories;

import com.ineffable.orderservice.Models.Orders;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrdersRepo extends MongoRepository<Orders,String> {
}
