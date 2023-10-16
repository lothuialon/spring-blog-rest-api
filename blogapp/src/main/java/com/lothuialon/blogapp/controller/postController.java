package com.lothuialon.blogapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lothuialon.blogapp.DTOs.postDTO;
import com.lothuialon.blogapp.DTOs.postResponse;
import com.lothuialon.blogapp.service.postService;
import com.lothuialon.blogapp.service.implementation.postServiceImp;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class postController{
    
    private postService thePostService;

    @Autowired
    public postController(postService incomingPostService) {
        thePostService = incomingPostService;
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/posts")
    public ResponseEntity<postDTO> createPost(@Valid @RequestBody postDTO thePostDTO){

        return new ResponseEntity<postDTO>(thePostService.createPost(thePostDTO), HttpStatus.CREATED);

    }

    //pagesort
    @GetMapping("/posts")
    public postResponse getAllPosts(@RequestParam(required = false, defaultValue = "0", value = "pageNo") int pageNo, 
    @RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize,
    @RequestParam(required = false, value = "sortBy", defaultValue = "id") String sortBy){

        return thePostService.getAllPosts(pageNo, pageSize, sortBy);
    }

    @GetMapping("posts/{id}")
    public ResponseEntity<postDTO> getPostById(@PathVariable int id){
        
        return ResponseEntity.ok(thePostService.getPostById(id));

    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("posts/{id}")
    public ResponseEntity<postDTO> getPostById(@RequestBody postDTO incomingPostDTO, @PathVariable int id){
        
        return ResponseEntity.ok(thePostService.updatePost(incomingPostDTO, id));

    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("posts/{id}")
    public ResponseEntity<String> DeletePostById(@PathVariable int id){
        
    thePostService.deletePostById(id);
    return new ResponseEntity<>("Deletion is succesful", HttpStatus.OK);

    }
}
