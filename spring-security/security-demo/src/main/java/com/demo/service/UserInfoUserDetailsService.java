package com.demo.service;

import com.demo.config.UserInfoUserDetails;
import com.demo.entity.UserInfo;
import com.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Optional<UserInfo> user = userRepo.findByName(username);
        return  user.map(UserInfoUserDetails:: new)
                .orElseThrow(()-> new UsernameNotFoundException("user not found" + username) );

    }

}
