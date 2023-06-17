package com.demo.repo;

import com.demo.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserInfo, Integer> {

    Optional<UserInfo> findByName(String username);
}
