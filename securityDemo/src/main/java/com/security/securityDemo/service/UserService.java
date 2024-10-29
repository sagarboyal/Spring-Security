package com.security.securityDemo.service;

import com.security.securityDemo.model.User;
import com.security.securityDemo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    public User save(User user){
        return userRepo.save(user);
    }

    public String verify(User user) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                user.getUsername(),
                                user.getPassword()
                        ));
//        return (authentication.isAuthenticated())?"success":"un-success";
        if(authentication.isAuthenticated()){
            jwtService.generateToken();
        }
    }
}
