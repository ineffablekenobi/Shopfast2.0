package com.ineffable.appuserservice.Services;

import com.ineffable.appuserservice.DTO.AddressDTO;
import com.ineffable.appuserservice.DTO.RoleDTO;
import com.ineffable.appuserservice.DTO.ServiceUserDTO;
import com.ineffable.appuserservice.Model.Address;
import com.ineffable.appuserservice.Model.Role;
import com.ineffable.appuserservice.Model.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DTOService {

    @Autowired
    private ServiceUserDTO serviceUserDTO;

    @Autowired
    private RoleDTO roleDTO;

    @Autowired
    private AddressDTO addressDTO;

    public ServiceUserDTO convertToDTO(ServiceUser serviceUser){
        serviceUserDTO.setId( serviceUser.getId());
        serviceUserDTO.setUsername(serviceUser.getUsername());
        serviceUserDTO.setPassword(serviceUser.getPassword());
        serviceUserDTO.setGender(serviceUser.getGender());
        serviceUserDTO.setPhoneNumber(serviceUser.getPhoneNumber());
        serviceUserDTO.setEmail(serviceUser.getEmail());
        serviceUserDTO.setShopCode(serviceUser.getShopCode());
        serviceUserDTO.getRoles().clear();
        for(int i = 0; i < serviceUser.getRoles().size(); i++){
            serviceUserDTO.getRoles().add(serviceUser.getRoles().get(i).getRole());
        }
        serviceUser.getAddresses().clear();
        for(int i = 0; i <serviceUser.getAddresses().size(); i++){
            serviceUserDTO.getAddresses().add(serviceUser.getAddresses().get(i).getId());
        }
        return serviceUserDTO;
    }


    public AddressDTO covertToDTO(Address addressObj){
        addressDTO.setId(addressObj.getId());
        addressDTO.setFirstName(addressObj.getFirstName());
        addressDTO.setLastName(addressObj.getLastName());
        addressDTO.setAddress(addressObj.getAddress());
        addressDTO.setArea(addressObj.getArea());
        addressDTO.setCity(addressObj.getCity());
        addressDTO.setDistrict(addressObj.getDistrict());
        addressDTO.setCountry(addressObj.getCountry());
        addressDTO.setAddressType(addressObj.getAddressType());
        addressDTO.setServiceUserId(addressObj.getServiceUser().getId());
        return  addressDTO;
    }

    public RoleDTO convertToDTO(Role role){
        roleDTO.setId(role.getId());
        roleDTO.setRole(role.getRole());
        roleDTO.getServiceUsers().clear();
        for(int i = 0; i  < role.getServiceUsers().size(); i++){
            roleDTO.getServiceUsers().add(role.getServiceUsers().get(i).getId());
        }
        return roleDTO;
    }


}
