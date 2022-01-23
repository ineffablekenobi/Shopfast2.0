package com.ineffable.appuserservice.DTO;

import com.ineffable.appuserservice.Model.ServiceUser;

import java.util.ArrayList;
import java.util.List;

public class ServiceUserDTO {
    private Long id;

    private String username;

    private String password;
    private String Gender;
    private String phoneNumber;
    private String email;


    private List<Long> addresses;

    private List<String> roles;



    public ServiceUserDTO() {
        addresses = new ArrayList<>();
        roles = new ArrayList<>();
    }

    public ServiceUserDTO(Long id, String username, String password, String gender, String phoneNumber, String email, List<Long> addresses, List<String> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        Gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.addresses = addresses;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Long> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Long> addresses) {
        this.addresses = addresses;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
