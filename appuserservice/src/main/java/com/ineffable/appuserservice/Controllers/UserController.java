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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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


    @GetMapping("/")
    public ResponseEntity<UserWrapper> getAll(){
        return ResponseEntity.ok().body(userService.getAll());
    }

    @GetMapping("/username={username}")
    public ResponseEntity<?> getByUserName(@PathVariable("username") String username){
        Optional<ServiceUser> serviceUserOptional = userService.getByUserName(username);
        if(serviceUserOptional.isPresent()){
            return ResponseEntity.ok().body(dtoService.convertToDTO(serviceUserOptional.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    public ResponseEntity<ServiceUserDTO> addUser(@RequestBody ServiceUser serviceUser){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/add").toUriString());
        return ResponseEntity.created(uri).body(dtoService.convertToDTO(userService.createNew(serviceUser)));
    }




}
