package com.ineffable.appuserservice.Services;

import com.ineffable.appuserservice.DTO.AddressDTO;
import com.ineffable.appuserservice.DTO.AddressWrapper;
import com.ineffable.appuserservice.Exceptions.AddressNotFoundException;
import com.ineffable.appuserservice.Exceptions.UserNotFoundException;
import com.ineffable.appuserservice.Model.Address;
import com.ineffable.appuserservice.Model.ServiceUser;
import com.ineffable.appuserservice.Repositories.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private DTOService dtoService;

    @Autowired
    private UserService userService;

    @Autowired
    private ApiCheckService apiCheckService;

    @Autowired
    private AddressWrapper addressWrapper;

    public AddressDTO createNewAddress(String username,Address customerAddress) throws AddressNotFoundException {
        Optional<ServiceUser> user = userService.getByUserName(username);

        //apiCheckService.existByCountryAndCity(customerAddress.getCountry(), customerAddress.getCity());

        AddressDTO addressDTO;
        if(user.isPresent()) {
            user.get().getAddresses().add(customerAddress);
            customerAddress.setServiceUser(user.get());
            Address addressObj = addressRepo.save(customerAddress);
            userService.updateUser(user.get());
            addressDTO = dtoService.convertToDTO(addressObj);
        }else {
            addressDTO = new AddressDTO();
            addressDTO.setAddressType("Error");
        }
        return addressDTO;
    }

    public AddressWrapper getAddressByUser(String username) throws UserNotFoundException{
        Optional<ServiceUser> userOptional = userService.getByUserName(username);

        if(userOptional.isEmpty()){
            throw new UserNotFoundException("Cant get address");
        }

        List<Address> addresses = userOptional.get().getAddresses();

        addressWrapper.getAddressDTOS().clear();

        for(int i = 0; i < addresses.size(); i++){
            addressWrapper.getAddressDTOS().add(dtoService.convertToDTO(addresses.get(i)));
        }

        return addressWrapper;
    }

    public AddressDTO getAddressById(Long id) throws AddressNotFoundException {
        Optional<Address> addressOptional = addressRepo.findById(id);
        if(addressOptional.isEmpty()){
            throw new AddressNotFoundException("Address By Id doesnt Exists");
        }
        return dtoService.convertToDTO(addressOptional.get());
    }


    public Boolean existsAddressById(Long addressid) {
        return addressRepo.existsById(addressid);
    }
}
