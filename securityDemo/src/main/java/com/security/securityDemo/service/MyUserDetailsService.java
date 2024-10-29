package com.security.securityDemo.service;

import com.security.securityDemo.model.User;
import com.security.securityDemo.model.UserPrinciple;
import com.security.securityDemo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if(user == null)
            throw new UsernameNotFoundException("username not found!");
        // we can't return UserDetails because it was an interface
        // so implement it we create any model class in here the class name
        // UserPrinciple
        return new UserPrinciple(user);
    }
}
