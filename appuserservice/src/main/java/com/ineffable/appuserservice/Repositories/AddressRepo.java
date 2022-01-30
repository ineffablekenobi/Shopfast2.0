package com.ineffable.appuserservice.Repositories;


import com.ineffable.appuserservice.Model.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddressRepo extends CrudRepository<Address, Long> {

}
