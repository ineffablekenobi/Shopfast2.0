package com.ineffable.shippingservice;

import com.ineffable.shippingservice.DTO.GeoWrapper;
import com.ineffable.shippingservice.DTO.ShippingChargeWrapper;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@OpenAPIDefinition
public class ShippingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShippingServiceApplication.class, args);
    }

    @Bean
    public ShippingChargeWrapper getShippingChargeWrapper(){
        return new ShippingChargeWrapper();
    }

    @Bean
    @LoadBalanced
    public WebClient.Builder getClientBuilder(){
        return WebClient.builder();
    }

    @Bean
    public GeoWrapper getGeoWrapper(){
        return new GeoWrapper();
    }

}
