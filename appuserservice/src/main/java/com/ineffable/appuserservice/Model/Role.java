package com.ineffable.appuserservice.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String role;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @Schema(hidden = true)
    private List<ServiceUser> serviceUsers;

    public Role() {
        serviceUsers = new ArrayList<>();
    }

    public Role(Long id, String role, List<ServiceUser> serviceUsers) {
        this.id = id;
        this.role = role;
        this.serviceUsers = serviceUsers;
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

    public List<ServiceUser> getServiceUsers() {
        return serviceUsers;
    }

    public void setServiceUsers(List<ServiceUser> serviceUsers) {
        this.serviceUsers = serviceUsers;
    }
}
