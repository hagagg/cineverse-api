package com.hagag.cineverse.repository;

import com.hagag.cineverse.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByUserNameOrEmail(String username, String email);
}
