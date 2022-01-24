package com.ineffable.appuserservice;

import com.ineffable.appuserservice.DTO.*;
import com.ineffable.appuserservice.Security.UserDetailServiceConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AppuserserviceApplication {


    public static void main(String[] args) {
        SpringApplication.run(AppuserserviceApplication.class, args);
    }

    @Bean
    public UserWrapper getUserWrapper(){
        return new UserWrapper();
    }

    @Bean
    public RoleWrapper getRoleWrapper(){
        return new RoleWrapper();
    }

    @Bean
    public UserRoleResponse getUserRoleResponse(){
        return new UserRoleResponse();
    }

    @Bean
    public RoleDTO getRoleDTO(){
        return new RoleDTO();
    }

    @Bean
    public AddressDTO getAddressDTO(){
        return new AddressDTO();
    }

    @Bean
    public ServiceUserDTO getServiceUserDTO(){
        return new ServiceUserDTO();
    }

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailServiceConfig getUserDetails(){
        return new UserDetailServiceConfig();
    }

}