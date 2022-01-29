package com.example.shopfasteurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ShopfastEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopfastEurekaServerApplication.class, args);
    }

}
