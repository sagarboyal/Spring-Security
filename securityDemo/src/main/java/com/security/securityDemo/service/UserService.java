package com.security.securityDemo.service;

import com.security.securityDemo.model.User;
import com.security.securityDemo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public User save(User user){
        return userRepo.save(user);
    }
}
