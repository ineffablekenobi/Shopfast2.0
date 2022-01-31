package com.ineffable.orderservice.Controllers;

import com.ineffable.orderservice.DTO.Inputs.OrderRequestWrapper;
import com.ineffable.orderservice.DTO.OrderRequestValidationWrapper;
import com.ineffable.orderservice.Models.OrderWrapper;
import com.ineffable.orderservice.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/new")
    public ResponseEntity<OrderRequestValidationWrapper> createOrder(@RequestBody OrderRequestWrapper orderRequestWrapper){
        try {
            return ResponseEntity.ok(orderService.createNewOrder(orderRequestWrapper));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }


}
