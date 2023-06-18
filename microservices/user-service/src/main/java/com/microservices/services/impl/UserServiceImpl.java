package com.microservices.services.impl;

import com.microservices.entities.User;
import com.microservices.repo.UserRepo;
import com.microservices.exception.ResourceNotFoundException;
import com.microservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public User saveUser(User user) {

        //to autoGenerate user id
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);

        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUser(String userId) {
        return userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server"+ userId));
    }


}
