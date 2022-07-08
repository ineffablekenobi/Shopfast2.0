package com.ineffable.appuserservice.Controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ineffable.appuserservice.DTO.ServiceUserDTO;
import com.ineffable.appuserservice.DTO.UserWrapper;
import com.ineffable.appuserservice.Model.ServiceUser;
import com.ineffable.appuserservice.Services.DTOService;
import com.ineffable.appuserservice.Services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private DTOService dtoService;

    @Operation(summary = "Get all users")
    @GetMapping("/")
    public ResponseEntity<UserWrapper> getAll(){
        return ResponseEntity.ok().body(userService.getAll());
    }

    @Operation(summary = "Get user by username")
    @GetMapping("/username={username}")
    public ResponseEntity<?> getByUserName(@PathVariable("username") String username){
        Optional<ServiceUser> serviceUserOptional = userService.getByUserName(username);
        if(serviceUserOptional.isPresent()){
            return ResponseEntity.ok().body(dtoService.convertToDTO(serviceUserOptional.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get shopCode given user",
            description = "if the user is a customer you'll receive null and if " +
                    "he is a staff you will receive the shop he is assigned to"
    )
    @GetMapping("/shop/username={username}")
    public ResponseEntity<?> getUserShop(@PathVariable("username") String username){
        Optional<ServiceUser> serviceUserOptional = userService.getByUserName(username);
        if(serviceUserOptional.isPresent()){
            return ResponseEntity.ok().body(serviceUserOptional.get().getShopCode());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Create new user")
    @PostMapping("/add")
    public ResponseEntity<ServiceUserDTO> addUser(@RequestBody ServiceUser serviceUser){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/add").toUriString());
        return ResponseEntity.created(uri).body(dtoService.convertToDTO(userService.createNew(serviceUser)));
    }

    @Operation(summary = "Check if a user exists given the username")
    @GetMapping("/exists/username={username}")
    public ResponseEntity<Boolean> existsByUserName(@PathVariable("username")String username){
        return ResponseEntity.ok(userService.existsByUserName(username));
    }

    @Operation(summary = "Update user",
        description = "You'll get a response 200 if user is updated"
    )
    @PostMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody ServiceUser serviceUser){
        if(userService.existsByUserName(serviceUser.getUsername())){
            serviceUser.setId(userService.getByUserName(serviceUser.getUsername()).get().getId());

            userService.updateUser(serviceUser);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }



}
