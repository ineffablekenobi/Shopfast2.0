package com.ineffable.shopfastgateway;

import com.ineffable.shopfastgateway.config.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShopfastgatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopfastgatewayApplication.class, args);
    }

    @Bean
    public RouteLocator getLocator(RouteLocatorBuilder routeLocatorBuilder, AuthFilter authFilter){

        OrderedGatewayFilter orderedGatewayFilter = new OrderedGatewayFilter(authFilter.apply(new AuthFilter.Config()), 45);

        return routeLocatorBuilder.routes()
                .route(
                        p ->  p.path("/orders/**")
                                //.filters(f-> f.filter(orderedGatewayFilter))
                                .uri("lb://order-service/")
                ).route(
                        p-> p.path("/shop/**")
                                .filters(f-> f.filter(orderedGatewayFilter))
                                .uri("lb://shop-service/")
                ).route(
                        p -> p.path("/product/**")
                                .filters(f-> f.filter(orderedGatewayFilter))
                                .uri("lb://product-service")
                ).route(
                        p -> p.path("/geo/**")
                                .filters( f -> f.filter(orderedGatewayFilter))
                                .uri("lb://shipping-service")
                ).route(
                        p-> p.path("/shipping/**")
                                .filters( f -> f.filter(orderedGatewayFilter))
                                .uri("lb://shipping-service")
                ).route(
                        p-> p.path("/inventory/**")
                                .filters( f -> f.filter(orderedGatewayFilter))
                                .uri("lb://inventory-service")
                ).route(
                        p-> p.path("/role/**")
                                .filters( f -> f.filter(orderedGatewayFilter))
                                .uri("lb://user-service")
                ).route(
                        p-> p.path("/user/**")
                                .filters( f -> f.filter(orderedGatewayFilter))
                                .uri("lb://user-service")
                )

                .build();
    }

}
