package com.ineffable.appuserservice.DTO;

import com.ineffable.appuserservice.Model.Role;

import java.util.*;

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
