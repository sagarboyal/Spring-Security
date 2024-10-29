package com.security.securityDemo.repository;

import com.security.securityDemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    public User findByUsername(String username);
}
