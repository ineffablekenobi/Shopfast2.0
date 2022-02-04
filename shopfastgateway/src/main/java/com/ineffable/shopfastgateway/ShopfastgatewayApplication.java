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
                                .filters(f-> f.filter(orderedGatewayFilter))
                                .uri("lb://order-service/")

                ).build();
    }

}
