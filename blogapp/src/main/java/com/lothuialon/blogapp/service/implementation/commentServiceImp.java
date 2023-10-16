package com.lothuialon.blogapp.service.implementation;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.lothuialon.blogapp.DTOs.commentDTO;
import com.lothuialon.blogapp.entity.blogpost;
import com.lothuialon.blogapp.entity.comment;
import com.lothuialon.blogapp.exception.BlogApiException;
import com.lothuialon.blogapp.exception.resourceNotFoundException;
import com.lothuialon.blogapp.repository.blogpostRepository;
import com.lothuialon.blogapp.repository.commentRepository;
import com.lothuialon.blogapp.service.commentService;

@Service
public class commentServiceImp implements commentService {

    private commentRepository CommentRepository;
    private blogpostRepository BlogpostRepository;

    @Autowired
    public commentServiceImp(commentRepository theCommentRepository, blogpostRepository theBlogpostRepository) {
        this.CommentRepository = theCommentRepository;
        this.BlogpostRepository = theBlogpostRepository;
    }

    // DTO Mappings
    private commentDTO mapToDTO(comment theComment) {
        commentDTO theCommentDTO = new commentDTO();
        // set fields
        theCommentDTO.setId(theComment.getId());
        theCommentDTO.setBody(theComment.getBody());
        theCommentDTO.setEmail(theComment.getEmail());
        theCommentDTO.setName(theComment.getName());
        return theCommentDTO;

    }

    private comment mapToEntity(commentDTO theCommentDTO) {

        comment theComment = new comment();
        // set fields
        theComment.setBody(theCommentDTO.getBody());
        theComment.setEmail(theCommentDTO.getEmail());
        theComment.setId(theCommentDTO.getId());
        theComment.setName(theCommentDTO.getName());
        return theComment;

    }

    @Override
    public commentDTO createComment(commentDTO theCommentDTO, int postId) {

        comment incomingComment = mapToEntity(theCommentDTO);

        blogpost theBlogpost = BlogpostRepository.getReferenceById(postId);
        
        incomingComment.setPost(theBlogpost);

        comment savedComment = CommentRepository.save(incomingComment);
        
        return mapToDTO(savedComment);
        
       
    }



    @Override
    public commentDTO updateComment(commentDTO theCommentDTO, int commentId, int postId) {

        blogpost theBlogpost = BlogpostRepository.getReferenceById(postId);

        comment theComment = CommentRepository.getReferenceById(commentId);


        if(!(theComment.getPost().getId() == postId)){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not exist");
        }

        theComment.setBody(theCommentDTO.getBody());
        theComment.setEmail(theCommentDTO.getEmail());
        theComment.setName(theCommentDTO.getName());

        commentDTO returnedDTO = mapToDTO(CommentRepository.save(theComment));

        return returnedDTO;

    }

    @Override
    public void deleteCommentById(int commentId, int postId) {

        blogpost theBlogpost = BlogpostRepository.getReferenceById(postId);

        comment theComment = CommentRepository.getReferenceById(commentId);


        if(!(theComment.getPost().getId() == postId)){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not exist");
        }

        CommentRepository.delete(theComment);

    }

    @Override
    public List<commentDTO> getCommentsByPostId(int postId) {


        List<comment> commentList = CommentRepository.findByPostId(postId);
        return commentList.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());

    }

    @Override
    public commentDTO getCommentById(int postId, int commentId) {


        blogpost theBlogpost = BlogpostRepository.getReferenceById(postId);

        comment theComment = CommentRepository.getReferenceById(commentId);

        if(!(theComment.getPost().getId() == postId)){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not exist");
        }

        return mapToDTO(theComment);

        
    }



}
