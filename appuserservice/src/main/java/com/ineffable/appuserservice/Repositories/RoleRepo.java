package com.ineffable.appuserservice.Repositories;

import com.ineffable.appuserservice.Model.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepo extends CrudRepository<Role, Long> {
    public Optional<Role> findByRole(String role);
    public boolean existsByRole(String role);
    public List<Role> findAll();
}
