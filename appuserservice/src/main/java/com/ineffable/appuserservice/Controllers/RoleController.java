package com.ineffable.appuserservice.Controllers;


import com.ineffable.appuserservice.DTO.RoleWrapper;
import com.ineffable.appuserservice.Model.Role;
import com.ineffable.appuserservice.Services.RoleService;
import com.ineffable.appuserservice.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Value("${roles.available}")
    private Set<String> availableRoles; // should be under the refresh scope

    private List<Role> roles;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @PostMapping("/add") //after every refresh this should be called
    public ResponseEntity<RoleWrapper> addRoles(){
        return ResponseEntity.ok().body(roleService.addRoles(availableRoles));
    }

    @GetMapping("/")
    public ResponseEntity<RoleWrapper> getRoles(){
        return ResponseEntity.ok().body(roleService.getRoles());
    }

    @GetMapping("/assign/username={username}&role={role}")
    public ResponseEntity<?> assignRole(@PathVariable("username") String username, @PathVariable("role") String role){
        if(userService.getByUserName(username).isEmpty()){
            return ResponseEntity.notFound().build();
        }

        if(roleService.findRole(role).isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(roleService.assignRole(username,role));

    }


}
