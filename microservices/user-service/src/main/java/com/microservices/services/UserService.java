package com.microservices.services;

import com.microservices.entities.User;

import java.util.List;

public interface UserService {


    User saveUser(User user);
    List<User> getAllUsers();
    User getUser(String userId);

}
