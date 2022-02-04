package com.example.shopservice.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/test")
public class testController {

    @RequestMapping("/**")
    public String getThrought(HttpServletRequest httpServletRequest){
        return httpServletRequest.getRequestURI();
    }

}
