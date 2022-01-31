package com.ineffable.orderservice.Services;

import com.ineffable.orderservice.DTO.Inputs.OrderRequest;
import com.ineffable.orderservice.DTO.Inputs.OrderRequestWrapper;
import com.ineffable.orderservice.DTO.OrderRequestValidationWrapper;
import com.ineffable.orderservice.Models.OrderWrapper;
import com.ineffable.orderservice.Models.Orders;
import com.ineffable.orderservice.Models.SubOrders;
import com.ineffable.orderservice.Repostitories.OrderWrapperRepo;
import com.ineffable.orderservice.Repostitories.OrdersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {

    @Autowired
    private OrderWrapperRepo orderWrapperRepo;

    @Autowired
    private OrdersRepo ordersRepo;

    @Autowired
    private ApiCheckService apiCheckService;

    public OrderRequestValidationWrapper createNewOrder(OrderRequestWrapper orderRequestWrapper) throws NoSuchFieldException {
        OrderRequestValidationWrapper orderRequestValidationWrapper = apiCheckService.validOrderRequestCheck(orderRequestWrapper);

        if(!orderRequestValidationWrapper.getSuccessfullyValidated()){
            return orderRequestValidationWrapper;
        }

        OrderWrapper orderWrapper = new OrderWrapper();
        orderWrapper.setUserName(orderRequestWrapper.getUserName());

        Map<String, List<SubOrders>> subOrdersByShop = new HashMap<>();

        List<OrderRequest> orderRequests = orderRequestWrapper.getOrders();

        for(int i = 0; i < orderRequests.size(); i++){
            SubOrders subOrders = new SubOrders();
            subOrders.setProductCode(orderRequests.get(i).getProductCode());
            subOrders.setQuantity(orderRequests.get(i).getQuantity());
            subOrders.setVariants(orderRequests.get(i).getVariants());
            if(subOrdersByShop.containsKey(orderRequests.get(i).getShopCode())){
                subOrdersByShop.get(orderRequests.get(i).getShopCode()).add(subOrders);
            }else {
                subOrdersByShop.put(orderRequests.get(i).getShopCode(),new ArrayList<>());
                subOrdersByShop.get(orderRequests.get(i).getShopCode()).add(subOrders);
            }
        }

        List<Orders> ordersList = new ArrayList<>();

        for(Map.Entry<String,List<SubOrders>> entry : subOrdersByShop.entrySet()){
            Orders orders = new Orders();
            orders.setBillingId(orderRequestWrapper.getBillingId());
            orders.setShippingId(orderRequestWrapper.getShippingId());
            orders.setSubOrders(entry.getValue());
            orders.setShopCode(entry.getKey());
            Orders insertedOrder = ordersRepo.insert(orders);
            ordersList.add(insertedOrder);
        }

        orderWrapper.setOrders(ordersList);

        orderRequestValidationWrapper.setOrderWrapper(orderWrapperRepo.insert(orderWrapper));

        return orderRequestValidationWrapper;

    }

}
