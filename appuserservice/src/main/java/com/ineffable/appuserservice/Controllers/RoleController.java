package com.ineffable.appuserservice.Controllers;

import com.ineffable.appuserservice.DTO.RoleWrapper;
import com.ineffable.appuserservice.Services.RoleService;
import com.ineffable.appuserservice.Services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("/role")
@RefreshScope
public class RoleController {

    @Value("${test.name}")
    private String testVal;

    @Value("${roles.available}")
    private Set<String> availableRoles; // should be under the refresh scope

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Operation(summary = "Add new Roles by this Endpoint",
    description = "Change the roles from the config then do an actuator refresh." +
            "After that call this method")
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
