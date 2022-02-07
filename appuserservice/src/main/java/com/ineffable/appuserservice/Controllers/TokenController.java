package com.ineffable.appuserservice.Controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ineffable.appuserservice.Services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.invoke.CallSite;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.List;

@RestController
public class TokenController {

    @Autowired
    private UserService userService;

    @Value("#{${role.permissions}}")
    private Map<String,List<String>> permissions;


    private String signature = "secret";

    @Operation(
            summary = "Check if the user has permission to visit a certain url"
    )

    @GetMapping("/token/verify/accesstoken={token}/path={path}")
    public ResponseEntity<?> verifyToken(@PathVariable("token") String token, @PathVariable("token") String path, HttpServletRequest request){

        try {
            Algorithm algorithm = Algorithm.HMAC256(signature.getBytes(StandardCharsets.UTF_8));
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
            boolean permissionFound = false;
            for(int i = 0; i < roles.length; i++){
                List<String> urls = permissions.get(roles[i]);
                for(int j = 0; j < urls.size(); j++){
                    if(urls.get(j).startsWith(path)){
                        permissionFound = true;
                        break;
                    }
                }
                if(permissionFound){
                    break;
                }
            }
            if(permissionFound) {
                return ResponseEntity.ok(decodedJWT.getSubject());
            }else{
                return ResponseEntity.notFound().build();
            }
        }catch (Exception exception){
            return ResponseEntity.notFound().build();
        }

    }

    @Operation(summary = "Provide Refresh token and Get Access token")
    @GetMapping("/token/refresh/refreshtoken={refresh_token}")
    public ResponseEntity<Map<String,String>> refreshToken(@PathVariable("refresh_token")String refresh_token) throws IOException {

            try {
                Algorithm algorithm = Algorithm.HMAC256(signature.getBytes(StandardCharsets.UTF_8));
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                User springUser = userService.loadSpringUser(username);

                String access_token = JWT.create()
                        .withSubject(springUser.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 2 * 60 * 1000))
                        .withIssuer("Ineffable")
                        .withClaim("roles",springUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String,String> mp = new HashMap<>();
                mp.put("access_token", access_token);
                mp.put("refresh_token", refresh_token);
                return ResponseEntity.ok(mp);

            } catch (Exception e) {
                return ResponseEntity.notFound().build();
            }
        }
    }

