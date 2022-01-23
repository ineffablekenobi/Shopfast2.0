package com.ineffable.appuserservice.Configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigHolder {

    @Value("${sign.secret}")
    public String signature;
}
