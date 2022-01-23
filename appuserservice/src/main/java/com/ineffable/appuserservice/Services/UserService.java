package com.ineffable.appuserservice.Services;

import com.ineffable.appuserservice.DTO.UserWrapper;
import com.ineffable.appuserservice.Model.ServiceUser;
import com.ineffable.appuserservice.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserWrapper userWrapper;

    @Autowired
    private DTOService dtoService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public UserWrapper getAll(){

        List<ServiceUser> serviceUserList = userRepo.findAll();

        for(int i = 0; i <serviceUserList.size(); i++){
            userWrapper.getServiceUsers().add(dtoService.convertToDTO(serviceUserList.get(i)));
        }

        return userWrapper;
    }

    public Optional<ServiceUser> getByUserName(String userName){
        return userRepo.findByUsername(userName);
    }

    public ServiceUser createNew(ServiceUser serviceUser){
        serviceUser.setPassword(passwordEncoder.encode(serviceUser.getPassword()));
        ServiceUser user = userRepo.save(serviceUser);
        return user;
    }

    public User loadSpringUser(String username){
        Optional<ServiceUser> user = userRepo.findByUsername(username); // this is our own user
        if(user.isPresent()){
            Collection<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
            for(int i = 0; i < user.get().getRoles().size(); i++){
                String role = user.get().getRoles().get(i).getRole();
                grantedAuthorities.add(new SimpleGrantedAuthority(role));
            }

            //this spring security user class
            User springUser = new User(user.get().getUsername(), user.get().getPassword(),grantedAuthorities);
            return springUser;
        }else {
            throw new UsernameNotFoundException("Name not found -> User Details Service Extension");
        }
    }

    public void updateUser(ServiceUser serviceUser){
        userRepo.save(serviceUser);
    }


}
