package com.lothuialon.blogapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lothuialon.blogapp.entity.blogpost;

public interface blogpostRepository extends JpaRepository<blogpost, Integer>{
    //no need to annotate since we extend the JpaRepositoy.
    //No need for @Repository or @Transanctional since they are implemented at the JpaRepository.
}