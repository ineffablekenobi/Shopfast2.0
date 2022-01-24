package com.ineffable.appuserservice.Services;


import com.ineffable.appuserservice.DTO.RoleWrapper;
import com.ineffable.appuserservice.DTO.UserRoleResponse;
import com.ineffable.appuserservice.Model.Role;
import com.ineffable.appuserservice.Model.ServiceUser;
import com.ineffable.appuserservice.Repositories.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleService{
    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private RoleWrapper roleWrapper;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleResponse userRoleResponse;

    @Autowired
    private DTOService dtoService;

    public RoleWrapper addRoles(Set<String> roles){

        Iterator<String> itr = roles.iterator();


        List<Role> roleList = roleRepo.findAll();

        for(int i = 0; i < roleList.size(); i++){
            String existingRole =roleList.get(i).getRole();
            if(!roles.contains(existingRole)){
                roleRepo.deleteById(roleList.get(i).getId());
                roleList.remove(roleList.get(i));
                i--;
            }
        }

        while (itr.hasNext()){
            String currentRole = itr.next();
            if(!roleRepo.existsByRole(currentRole)){
                Role createdRole = new Role();
                createdRole.setRole(currentRole);
                roleList.add(createdRole);
                roleRepo.save(createdRole);
            }
        }


        roleWrapper.getRoles().clear();

        for(int i = 0; i < roleList.size(); i++){
            roleWrapper.getRoles().add(dtoService.convertToDTO(roleList.get(i)));
        }
        return roleWrapper;

    }

    public RoleWrapper getRoles(){

        List<Role> roleList = roleRepo.findAll();

        roleWrapper.getRoles().clear();

        for(int i = 0; i < roleList.size(); i++){
            roleWrapper.getRoles().add(dtoService.convertToDTO(roleList.get(i)));
        }
        return roleWrapper;
    }

    public Optional<Role> findRole(String role){
        return roleRepo.findByRole(role);
    }

    public UserRoleResponse assignRole(String username, String role){
       Role currentRole = roleRepo.findByRole(role).get(); // we already checked its present at Controller
        ServiceUser serviceUser = userService.getByUserName(username).get(); // we already checked its present at Controller

        currentRole.getServiceUsers().add(serviceUser);
        serviceUser.getRoles().add(currentRole);

        userRoleResponse.setRoleDTO(dtoService.convertToDTO(currentRole));
        userRoleResponse.setServiceUserDTO(dtoService.convertToDTO(serviceUser));
        roleRepo.save(currentRole);
        userService.updateUser(serviceUser);

        return userRoleResponse;
    }

}
