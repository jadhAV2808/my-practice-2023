package com.demo.controller;

import com.demo.entity.UserInfo;
import com.demo.service.UserInfoUserDetailsService;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public String addNewUser( @RequestBody UserInfo user){
        return userService.addUser(user);
    }

}
