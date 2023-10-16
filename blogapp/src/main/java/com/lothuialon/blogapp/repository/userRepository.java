package com.lothuialon.blogapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lothuialon.blogapp.entity.user;

public interface userRepository extends JpaRepository<user, Integer>{
    
    Optional<user> findByEmail(String email);
    Optional<user> findByUsernameOrEmail(String username, String email);
    Optional<user> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
