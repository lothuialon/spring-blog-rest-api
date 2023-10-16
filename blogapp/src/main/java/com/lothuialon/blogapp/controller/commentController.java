package com.lothuialon.blogapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lothuialon.blogapp.DTOs.commentDTO;
import com.lothuialon.blogapp.entity.blogpost;
import com.lothuialon.blogapp.service.commentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class commentController {
    
    commentService CommentService;

    @Autowired
    public commentController(commentService theCommentService) {
        this.CommentService = theCommentService;
    }




    @PostMapping(value="/posts/{postId}/comments")
    public ResponseEntity<commentDTO> createComment(
        @PathVariable(required = true, value = "postId") int postId,
        @RequestBody commentDTO theCommentDTO)
    {

        return new ResponseEntity<>(CommentService.createComment(theCommentDTO, postId), HttpStatus.CREATED);
    }

    @GetMapping(value="/posts/{postId}/comments")
    public ResponseEntity<List<commentDTO>> getCommentsById(@PathVariable(name = "postId") int postId) {

        return new ResponseEntity<>(CommentService.getCommentsByPostId(postId), HttpStatus.OK);

    }
    
    @GetMapping(value="/posts/{postId}/comments/{commentId}")
    public ResponseEntity<commentDTO> getCommentById(@PathVariable(value = "postId") int postId,
     @PathVariable(value = "commentId") int commentId) {
        commentDTO theCommentDTO = CommentService.getCommentById(postId, commentId); 
        return new ResponseEntity<>(theCommentDTO, HttpStatus.OK);
    }
    
    @PutMapping(value="/posts/{postId}/comments/{commentId}")
    public ResponseEntity<commentDTO> updateComment(@PathVariable(value = "postId") int postId,
     @PathVariable(value = "commentId") int commentId, @RequestBody commentDTO theCommentDTO) {
        commentDTO updatedCommentDTO = CommentService.updateComment(theCommentDTO, commentId, postId); 
        return new ResponseEntity<>(updatedCommentDTO, HttpStatus.OK);
    }


    @DeleteMapping(value="/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteById(@PathVariable(value = "postId") int postId,
     @PathVariable(value = "commentId") int commentId) {

        CommentService.deleteCommentById(commentId, postId);

        return new ResponseEntity<>("deletion is succesfull", HttpStatus.OK);
    }

}
