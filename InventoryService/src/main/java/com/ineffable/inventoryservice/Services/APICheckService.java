package com.ineffable.inventoryservice.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RefreshScope
public class APICheckService {

    @Value("${shop.service.uri}")
    private String shopServiceUri;

    @Value("${product.service.uri}")
    private String productServiceUri;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Boolean shopExistsCheck(String shopCode){
        Boolean exists = webClientBuilder.build()
                .get()
                .uri(shopServiceUri + "/exists/shopcode="+shopCode)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        return exists;

    }

    public Boolean productExistsCheck(String productCode, String shopCode){

        Boolean exists = webClientBuilder.build()
                .get()
                .uri(productServiceUri +"/exists/productcode="+productCode+"&shopcode="+shopCode)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
        return exists;
    }

    public void inventoryInsertableCheck(String productCode, String shopCode) throws NoSuchFieldException{
        Boolean exists = false;

        exists = webClientBuilder.build()
                .get()
                .uri(shopServiceUri + "/exists/shopcode="+shopCode)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if(Boolean.FALSE.equals(exists)){
            throw new NoSuchFieldException("No Such Shop");
        }


        exists = webClientBuilder.build()
                .get()
                .uri(productServiceUri +"/exists/productcode="+productCode+"&shopcode="+shopCode)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if(Boolean.FALSE.equals(exists)){
            throw new NoSuchFieldException("No Such Product");
        }


    }



}
