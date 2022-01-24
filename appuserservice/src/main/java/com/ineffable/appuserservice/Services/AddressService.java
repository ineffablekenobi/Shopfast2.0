package com.ineffable.appuserservice.Services;

import com.ineffable.appuserservice.DTO.AddressDTO;
import com.ineffable.appuserservice.Model.Address;
import com.ineffable.appuserservice.Model.ServiceUser;
import com.ineffable.appuserservice.Repositories.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private DTOService dtoService;

    @Autowired
    private UserService userService;

    public AddressDTO createNewAddress(String username,Address customerAddress){
        Optional<ServiceUser> user = userService.getByUserName(username);
        AddressDTO addressDTO;
        if(user.isPresent()) {
            user.get().getAddresses().add(customerAddress);
            customerAddress.setServiceUser(user.get());
            Address addressObj = addressRepo.save(customerAddress);
            userService.updateUser(user.get());
            addressDTO = dtoService.covertToDTO(addressObj);
        }else {
            addressDTO = new AddressDTO();
            addressDTO.setAddressType("Error");
        }
        return addressDTO;
    }



}
