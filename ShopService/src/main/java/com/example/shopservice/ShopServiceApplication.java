package com.example.shopservice;

import com.example.shopservice.Models.StringWrapper;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
@OpenAPIDefinition
public class ShopServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopServiceApplication.class, args);
    }

    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener() {
        return new ValidatingMongoEventListener(validator());
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public StringWrapper getPhoneNumberWrapper(){
        return new StringWrapper();
    }

}
