package com.ineffable.orderservice.Services;

import com.ineffable.orderservice.DTO.Inputs.OrderRequest;
import com.ineffable.orderservice.DTO.Inputs.OrderRequestWrapper;
import com.ineffable.orderservice.DTO.OrderRequestValidation;
import com.ineffable.orderservice.DTO.OrderRequestValidationWrapper;
import com.ineffable.orderservice.Models.Orders;
import com.ineffable.orderservice.Models.SubOrders;
import com.ineffable.orderservice.Models.Variant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RefreshScope
public class ApiCheckService {


    @Value("${product.service.uri}")
    private String productServiceUri;

    @Value("${inventory.service.uri}")
    private String inventoryServiceUri;

    @Value("${user.service.uri}")
    private String userServiceUri;

    @Autowired
    private WebClient.Builder webClientBuilder;


    public String generateSku(SubOrders subOrders, String shopCode){
        String productCode = subOrders.getProductCode();
        List<Variant> variants = subOrders.getVariants();
        String sku = "";
        sku += shopCode+"-";
        sku += productCode+"-";
        variants.sort(Comparator.comparing(Variant::getName).thenComparing(Variant::getValue));
        if(variants.size() != 0){
            for(int i = 0; i < variants.size() - 1; i++){
                sku += variants.get(i).toString()+"-";
            }
            sku += variants.get(variants.size() - 1).toString();
        }
        return sku;
    }

    public void quantityDeduct(Orders orders, String shopCode){

        List<SubOrders> subOrders = orders.getSubOrders();

        for(int i = 0; i < subOrders.size(); i++){
            String sku = generateSku(subOrders.get(i), shopCode);
            webClientBuilder.build()
            .post()
            .uri(inventoryServiceUri +"/quantitydeduct/warehousecode="+"unknown"+"&sku="+sku+"&quantity="+subOrders.get(i).getQuantity())
            .retrieve()
            .bodyToMono(Void.class)
            .block();

        }

    }


    public OrderRequestValidationWrapper validOrderRequestCheck(OrderRequestWrapper orderRequestWrapper) throws NoSuchFieldException {
        Boolean exists = false;
        String username = orderRequestWrapper.getUserName();
        exists = webClientBuilder.build()
                .get()
                .uri(userServiceUri +"/exists/username=" + username)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if(Boolean.FALSE.equals(exists)){
            throw new NoSuchFieldException("user not found");
        }

        exists = webClientBuilder.build()
                .get()
                .uri(userServiceUri +"/address/exists/addressid=" + orderRequestWrapper.getBillingId())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if(Boolean.FALSE.equals(exists)){
            throw new NoSuchFieldException("Billing address invalid");
        }

        exists = webClientBuilder.build()
                .get()
                .uri(userServiceUri +"/address/exists/addressid=" + orderRequestWrapper.getShippingId())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if(Boolean.FALSE.equals(exists)){
            throw new NoSuchFieldException("Shipping address invalid");
        }

        List<OrderRequest> orderRequests = orderRequestWrapper.getOrders();
        List<OrderRequestValidation> orderRequestValidations = new ArrayList<>();

        boolean successfulValidation = true;

        for(int i = 0; i < orderRequests.size(); i++){
            OrderRequestValidation orderRequestValidation = new OrderRequestValidation();
            orderRequestValidation.setOrderRequest(orderRequests.get(i));
            Boolean validated = true;
            String productCode = orderRequests.get(i).getProductCode();
            String shopCode = orderRequests.get(i).getShopCode();
            validated = webClientBuilder.build()
                    .get()
                    .uri(productServiceUri +"/exists/productcode="+productCode+"&shopcode="+shopCode)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();

            if(Boolean.FALSE.equals(validated)){
                orderRequestValidation.setValidated(false);
                orderRequestValidations.add(orderRequestValidation);
                successfulValidation = false;
                continue;
            }

            validated = webClientBuilder.build()
                    .post()
                    .uri(inventoryServiceUri +"/exists")
                    .body(Mono.just(orderRequests.get(i)),OrderRequest.class)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();

            if(Boolean.FALSE.equals(validated)){
                orderRequestValidation.setValidated(false);
                orderRequestValidations.add(orderRequestValidation);
                successfulValidation = false;
                continue;
            }

            Long quantity = webClientBuilder.build()
                    .post()
                    .uri(inventoryServiceUri +"/quantityleft")
                    .body(Mono.just(orderRequests.get(i)),OrderRequest.class)
                    .retrieve()
                    .bodyToMono(Long.class)
                    .block();

            if(quantity < orderRequests.get(i).getQuantity()){
                validated = false;
                successfulValidation = false;
            }

            orderRequestValidation.setValidated(validated);
            orderRequestValidations.add(orderRequestValidation);

        }


        OrderRequestValidationWrapper  wrapper = new OrderRequestValidationWrapper();
        wrapper.setValidations(orderRequestValidations);
        wrapper.setSuccessfullyValidated(successfulValidation);

        return wrapper;


    }


}
