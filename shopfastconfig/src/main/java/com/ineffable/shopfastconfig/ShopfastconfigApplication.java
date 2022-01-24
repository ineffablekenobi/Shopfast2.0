package com.ineffable.shopfastconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ShopfastconfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopfastconfigApplication.class, args);
    }

}
