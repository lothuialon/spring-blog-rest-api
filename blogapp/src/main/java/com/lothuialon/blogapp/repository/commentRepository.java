package com.lothuialon.blogapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lothuialon.blogapp.entity.comment;

public interface commentRepository extends JpaRepository<comment, Integer>{
    //custom
    List<comment> findByPostId(int postId);
}
