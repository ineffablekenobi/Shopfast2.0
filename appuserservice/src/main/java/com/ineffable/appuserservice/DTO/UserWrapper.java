package com.ineffable.appuserservice.DTO;

import java.util.ArrayList;
import java.util.List;

public class UserWrapper {
    List<ServiceUserDTO> serviceUsers;

    public UserWrapper() {
        serviceUsers = new ArrayList<>();
    }

    public UserWrapper(List<ServiceUserDTO> serviceUsers) {
        this.serviceUsers = serviceUsers;
    }

    public List<ServiceUserDTO> getServiceUsers() {
        return serviceUsers;
    }

    public void setServiceUsers(List<ServiceUserDTO> serviceUsers) {
        this.serviceUsers = serviceUsers;
    }
}
