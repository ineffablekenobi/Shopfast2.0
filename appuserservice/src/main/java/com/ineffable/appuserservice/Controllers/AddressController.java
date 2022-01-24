package com.ineffable.appuserservice.Controllers;

import com.ineffable.appuserservice.DTO.AddressDTO;
import com.ineffable.appuserservice.Model.Address;
import com.ineffable.appuserservice.Services.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Operation(summary = "Create New Address")
    @PostMapping("/new/username={username}")
    public ResponseEntity<?> createNewAddress(@PathVariable("username") String username, @RequestBody Address customerAddress){
        AddressDTO addressDTO = addressService.createNewAddress(username,customerAddress);
        if(addressDTO.getAddressType().equals("Error")){
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(addressDTO);
        }
    }

}
