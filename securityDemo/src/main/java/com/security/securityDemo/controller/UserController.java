package com.security.securityDemo.controller;

import com.security.securityDemo.model.User;
import com.security.securityDemo.repository.UserRepo;
import com.security.securityDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @PostMapping("/register")
    public String register(@RequestBody User user){
        user.setPassword(encoder.encode(user.getPassword()));
        User data = userService.save(user);
        return (data != null)?data.toString():"error";
    }
    @PostMapping("/login")
    public String login(@RequestBody User user){
        return userService.verify(user);
    }
}
