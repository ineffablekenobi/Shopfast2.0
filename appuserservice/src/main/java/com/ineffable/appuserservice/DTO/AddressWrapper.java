package com.ineffable.appuserservice.DTO;

import java.util.ArrayList;
import java.util.List;

public class AddressWrapper {
    private List<AddressDTO> addressDTOS;

    public AddressWrapper(){
        this.addressDTOS = new ArrayList<>();
    }

    public List<AddressDTO> getAddressDTOS() {
        return addressDTOS;
    }

    public void setAddressDTOS(List<AddressDTO> addressDTOS) {
        this.addressDTOS = addressDTOS;
    }
}
