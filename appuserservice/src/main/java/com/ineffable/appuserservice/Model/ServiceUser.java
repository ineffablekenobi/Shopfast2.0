package com.ineffable.appuserservice.Model;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ServiceUser {
    @Id
    @GeneratedValue(strategy =GenerationType.SEQUENCE)
    @Schema(hidden = true)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true)
    private String shopCode;

    private String password;
    private String Gender;
    private String phoneNumber;
    private String email;


    @OneToMany(targetEntity = Address.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_fk", referencedColumnName = "id")
    @Schema(hidden = true)
    private List<Address> addresses;

    @ManyToMany(mappedBy = "serviceUsers", fetch = FetchType.EAGER)
    @Schema(hidden = true)
    private List<Role> roles;

    public ServiceUser() {
        addresses = new ArrayList<>();
        roles = new ArrayList<>();
    }

    public ServiceUser(Long id, String username, String password, String gender, String phoneNumber, String email, List<Address> addresses, List<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        Gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.addresses = addresses;
        this.roles = roles;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
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

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
