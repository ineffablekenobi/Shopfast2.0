package com.ineffable.appuserservice.DTO;

import com.ineffable.appuserservice.Model.Role;
import com.ineffable.appuserservice.Model.ServiceUser;

public class UserRoleResponse {
    private ServiceUserDTO serviceUserDTO;
    private RoleDTO roleDTO;

    public UserRoleResponse(ServiceUserDTO serviceUserDTO, RoleDTO roleDTO) {
        this.serviceUserDTO = serviceUserDTO;
        this.roleDTO = roleDTO;
    }

    public UserRoleResponse() {
        serviceUserDTO = new ServiceUserDTO();
        roleDTO = new RoleDTO();
    }

    public ServiceUserDTO getServiceUserDTO() {
        return serviceUserDTO;
    }

    public void setServiceUserDTO(ServiceUserDTO serviceUserDTO) {
        this.serviceUserDTO = serviceUserDTO;
    }

    public RoleDTO getRoleDTO() {
        return roleDTO;
    }

    public void setRoleDTO(RoleDTO roleDTO) {
        this.roleDTO = roleDTO;
    }
}
