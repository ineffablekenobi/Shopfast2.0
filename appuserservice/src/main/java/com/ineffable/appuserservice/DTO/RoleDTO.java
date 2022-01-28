package com.ineffable.appuserservice.DTO;

import java.util.ArrayList;
import java.util.List;

public class RoleDTO {
    private Long id;

    private String role;

    private List<Long> serviceUserIds;

    public RoleDTO() {
        this.serviceUserIds = new ArrayList<>();
    }



    public RoleDTO(Long id, String role, List<Long> serviceUserIds) {
        this.id = id;
        this.role = role;
        this.serviceUserIds = serviceUserIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Long> getServiceUsers() {
        return serviceUserIds;
    }

    public void setServiceUsers(List<Long> serviceUsers) {
        this.serviceUserIds = serviceUsers;
    }
}
