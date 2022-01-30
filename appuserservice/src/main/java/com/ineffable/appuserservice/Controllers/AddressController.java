package com.ineffable.appuserservice.Controllers;

import com.ineffable.appuserservice.DTO.AddressDTO;
import com.ineffable.appuserservice.DTO.AddressWrapper;
import com.ineffable.appuserservice.Model.Address;
import com.ineffable.appuserservice.Services.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
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
        AddressDTO addressDTO;
        try {
            addressDTO = addressService.createNewAddress(username,customerAddress);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }


        if(addressDTO.getAddressType().equals("Error")){
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(addressDTO);
        }
    }

    @GetMapping("/username={username}")
    public ResponseEntity<?> getAddresses(@PathVariable("username") String username){
        try {
            return ResponseEntity.ok(addressService.getAddressByUser(username));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/addressid={addressid}")
    public ResponseEntity<?> getAddressById(@PathVariable("addressid") Long addresid){
        try {
            return ResponseEntity.ok(addressService.getAddressById(addresid));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
