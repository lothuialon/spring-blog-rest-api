package com.lothuialon.blogapp.service;

import java.util.List;

import com.lothuialon.blogapp.DTOs.postDTO;
import com.lothuialon.blogapp.DTOs.postResponse;

public interface postService {
    
    public postDTO createPost(postDTO thePostDTO);
    public postResponse getAllPosts(int pageNo, int pageSize, String sortBy);
    public postDTO getPostById(int id);
    public postDTO updatePost(postDTO PostDTO, int id);
    public void deletePostById(int id);
}
