package com.ineffable.shopfastgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShopfastgatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopfastgatewayApplication.class, args);
    }

    @Bean
    public RouteLocator getLocator(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes()
                .route(
                        p -> p.path("/orders/**")
                                .uri("lb://order-service/")
                ).build();
    }

}
