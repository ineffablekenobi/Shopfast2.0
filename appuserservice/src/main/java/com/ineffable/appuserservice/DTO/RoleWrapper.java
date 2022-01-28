package com.ineffable.appuserservice.DTO;

import java.util.ArrayList;
import java.util.List;

public class RoleWrapper {
    private List<RoleDTO> roles;


    public RoleWrapper() {
        roles = new ArrayList<>();
    }

    public RoleWrapper(List<RoleDTO> roles) {
        this.roles = roles;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }
}
