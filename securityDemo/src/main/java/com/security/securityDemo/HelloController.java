package com.security.securityDemo;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    public String getHome(HttpServletRequest request){
        return "Hello " + request.getSession().getId();
    }
}
