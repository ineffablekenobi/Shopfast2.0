package com.ineffable.appuserservice.Repositories;

import com.ineffable.appuserservice.Model.ServiceUser;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface UserRepo extends CrudRepository<ServiceUser,Long> {
    public List<ServiceUser> findAll();
    public Optional<ServiceUser> findByUsername(String username);
}
