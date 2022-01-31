package com.ineffable.orderservice.Repostitories;

import com.ineffable.orderservice.Models.OrderWrapper;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderWrapperRepo extends MongoRepository<OrderWrapper, String> {

}
