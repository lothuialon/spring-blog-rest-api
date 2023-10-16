package com.lothuialon.blogapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lothuialon.blogapp.entity.role;


public interface roleRepository extends JpaRepository<role, Integer>{
    
    Optional<role> findByName(String name);

}
