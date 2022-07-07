package com.ineffable.appuserservice.Model;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(hidden = true)
    private Long id;

    private String firstName;
    private String lastName;
    private String address;
    private String area;
    private String city;
    private String district;
    private String country;
    private String addressType; // can be billing or shipping address

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = ServiceUser.class)
    @JoinColumn(name = "user_fk", referencedColumnName = "id")
    @Schema(hidden = true)
    private ServiceUser serviceUser;



    public Address() {
        serviceUser = new ServiceUser();
    }


    public Address(Long id, String firstName, String lastName, String address, String area, String city, String district, String country, String addressType) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.area = area;
        this.city = city;
        this.district = district;
        this.country = country;
        this.addressType = addressType;
    }

    public ServiceUser getServiceUser() {
        return serviceUser;
    }

    public void setServiceUser(ServiceUser serviceUser) {
        this.serviceUser = serviceUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }
}
