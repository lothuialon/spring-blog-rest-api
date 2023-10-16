package com.lothuialon.blogapp.service;

import java.util.List;

import com.lothuialon.blogapp.DTOs.commentDTO;
import com.lothuialon.blogapp.DTOs.postDTO;
import com.lothuialon.blogapp.DTOs.postResponse;
import com.lothuialon.blogapp.entity.comment;

public interface commentService {
    
    commentDTO createComment(commentDTO theCommentDTO, int postId);
    List<commentDTO> getCommentsByPostId(int postId);
    commentDTO updateComment(commentDTO theCommentDTO, int commentId, int postId);
    void deleteCommentById(int commentId, int postId);
    commentDTO getCommentById(int postId, int commentId);

}
