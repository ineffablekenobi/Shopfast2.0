package com.ineffable.shopfastgateway;

import com.ineffable.shopfastgateway.config.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class ShopfastgatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopfastgatewayApplication.class, args);
    }

    @Bean
    public CorsWebFilter corsWebFilter() {

        final CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Collections.singletonList("*"));
        corsConfig.setMaxAge(3600L);
        corsConfig.setAllowedMethods(Arrays.asList("*"));
        corsConfig.addAllowedHeader("*");

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }


    @Bean
    public RouteLocator getLocator(RouteLocatorBuilder routeLocatorBuilder, AuthFilter authFilter){

        OrderedGatewayFilter orderedGatewayFilter = new OrderedGatewayFilter(authFilter.apply(new AuthFilter.Config()), 45);

        return routeLocatorBuilder.routes()
                .route(
                        p -> p.path("/login")
                                .uri("lb://user-service")
                )
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
