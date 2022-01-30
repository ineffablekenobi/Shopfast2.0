package com.ineffable.shippingservice.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RefreshScope
public class ApiCheckService {

    @Value("${shop.service.uri}")
    private String shopServiceUri;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public void shopExistsCheck(String shopCode) throws NoSuchFieldException{
        Boolean exists = webClientBuilder.build()
                .get()
                .uri(shopServiceUri + "/exists/shopcode="+shopCode)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if(Boolean.FALSE.equals(exists)){
            throw new NoSuchFieldException("Shop Code is Wrong");
        }

    }


}
