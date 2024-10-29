package com.security.securityDemo;

import com.security.securityDemo.model.User;
import com.security.securityDemo.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserRepo userRepo;

    @Test
    public void createUser(){
        User user = new User(null, "tiger", "scott");
        User data = userRepo.save(user);
        Assert.notNull(data, "Okay");
    }
}
