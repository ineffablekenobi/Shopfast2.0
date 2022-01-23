package com.ineffable.appuserservice.Security.Filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class AuthorizationFilter extends OncePerRequestFilter {

    // Added before so intercepts every request

    @Value("${sign.secret}")
    private String signature;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(signature == null){
            signature = "secret";
        }

        if(request.getServletPath().equals("/login") || request.getServletPath().equals("/user/token/refresh")){
            filterChain.doFilter(request,response);
        }else {
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            // if it not an authorization request let it pass through
            // if he doesnt add the token to security context holder he wont get any access
            if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
                try {

                    String token = authorizationHeader.substring("Bearer ".length());
                    Algorithm algorithm = Algorithm.HMAC256(signature.getBytes(StandardCharsets.UTF_8));
                    JWTVerifier verifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = verifier.verify(token);
                    String username = decodedJWT.getSubject();
                    String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    for(int i = 0; i < roles.length; i++){
                        authorities.add(new SimpleGrantedAuthority(roles[i]));
                    }
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username,null,authorities);
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    filterChain.doFilter(request,response);

                }catch (Exception e){
                    response.setHeader("Failed at JWT Authorization Filter", e.getMessage());
                    response.setStatus(FORBIDDEN.value());
                    Map<String,String> mp = new HashMap<>();
                    mp.put("Error ", e.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(),mp);
                }
            }else {
                filterChain.doFilter(request, response);
            }
        }
    }
}
