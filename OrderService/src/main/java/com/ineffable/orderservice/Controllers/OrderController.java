package com.ineffable.orderservice.Controllers;

import com.ineffable.orderservice.DTO.Inputs.OrderRequestWrapper;
import com.ineffable.orderservice.DTO.OrderRequestValidationWrapper;
import com.ineffable.orderservice.DTO.OrdersWrapper;
import com.ineffable.orderservice.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

    @PostMapping("/updateorderstate/orderid={orderid}&orderstate={orderstate}")
    public ResponseEntity<?> updateOrderState(@PathVariable("orderid")String orderid, @PathVariable("orderstate") String  orderState){
        try {
            return ResponseEntity.ok(orderService.updateOrderState(orderid,orderState));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/byshop/")
    public OrdersWrapper getAll(){
        return orderService.getAll();
    }

    @GetMapping("/byshop/shopcode={shopcode}")
    public OrdersWrapper getByShop(@PathVariable("shopcode") String shopcode){
        return orderService.getByShopCode(shopcode);
    }

}
