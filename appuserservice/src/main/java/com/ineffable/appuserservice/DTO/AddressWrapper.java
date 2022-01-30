package com.ineffable.appuserservice.DTO;

import java.util.List;

public class AddressWrapper {
    private List<AddressDTO> addressDTOS;

    public List<AddressDTO> getAddressDTOS() {
        return addressDTOS;
    }

    public void setAddressDTOS(List<AddressDTO> addressDTOS) {
        this.addressDTOS = addressDTOS;
    }
}
