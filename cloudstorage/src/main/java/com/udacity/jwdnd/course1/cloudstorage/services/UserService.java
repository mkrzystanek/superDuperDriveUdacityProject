package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.model.UserBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private HashService hashService;

    public User getUser(String userName) {
        return userMapper.getUser(userName);
    }

    public int createUser(User user) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);

        User newUser = new UserBuilder()
                .withUserName(user.getUsername())
                .withFirstName(user.getFirstname())
                .withLastName(user.getLastname())
                .withPassword(hashedPassword)
                .withSalt(encodedSalt)
                .build();

        return userMapper.createUser(newUser);
    }
}
