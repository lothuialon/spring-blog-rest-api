package com.lothuialon.blogapp.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lothuialon.blogapp.DTOs.postDTO;
import com.lothuialon.blogapp.DTOs.postResponse;
import com.lothuialon.blogapp.entity.blogpost;
import com.lothuialon.blogapp.repository.blogpostRepository;
import com.lothuialon.blogapp.service.postService;


@Service
public class postServiceImp implements postService{

    private blogpostRepository BlogpostRepository;

    @Autowired
    public postServiceImp(blogpostRepository theBlogpostRepository) {
        BlogpostRepository = theBlogpostRepository;
    }


    @Override
    public postDTO createPost(postDTO thePostDTO) {
        

        blogpost post = mapToPost(thePostDTO);

        blogpost savedPost = BlogpostRepository.save(post);

        //create a new data transfer object to send data
        postDTO postResponse = mapToDTO(savedPost);

        return postResponse;
    }



    
    public postDTO mapToDTO(blogpost post){
        postDTO newPostDTO = new postDTO();
        newPostDTO.setId(post.getId());
        newPostDTO.setContent(post.getContent());
        newPostDTO.setDescription(post.getDescription());
        newPostDTO.setTitle(post.getTitle());
        return newPostDTO;
    }

    public blogpost mapToPost(postDTO thePostDTO){
        blogpost newPost = new blogpost();
        //newPost.setId(thePostDTO.getId());
        newPost.setContent(thePostDTO.getContent());
        newPost.setDescription(thePostDTO.getDescription());
        newPost.setTitle(thePostDTO.getTitle());
        return newPost;

    }


    @Override
    public postDTO getPostById(int id) {


        blogpost theBlogPost = BlogpostRepository.getReferenceById(id);
        postDTO mappedDTO = mapToDTO(theBlogPost);

        return mappedDTO;

    }


    @Override
    public postDTO updatePost(postDTO PostDTO, int id) {

        blogpost blogpostFromDb = BlogpostRepository.getReferenceById(id);

        blogpostFromDb.setContent(PostDTO.getContent());
        blogpostFromDb.setDescription(PostDTO.getDescription());
        blogpostFromDb.setTitle(PostDTO.getTitle());

        blogpost savedPost = BlogpostRepository.save(blogpostFromDb);
        return mapToDTO(savedPost);
        
    }


    @Override
    public void deletePostById(int id) {

        blogpost blogPostFromDb = BlogpostRepository.getReferenceById(id);
        BlogpostRepository.delete(blogPostFromDb);

    }


    @Override
    public postResponse getAllPosts(int pageNo, int pageSize, String sortBy) {

        //pagination setup
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<blogpost> blogposts = BlogpostRepository.findAll(pageable);

        List<blogpost> blogpostList = blogposts.getContent();

        List<postDTO> responseContent = blogpostList.stream().map(blogpost -> mapToDTO(blogpost)).collect(Collectors.toList());

        //setting postResponse
        postResponse thePostResponse = new postResponse();
        thePostResponse.setContent(responseContent);
        thePostResponse.setPageNo(blogposts.getNumber());
        thePostResponse.setPageSize(blogposts.getSize());
        thePostResponse.setTotalElements(blogposts.getSize());
        thePostResponse.setTotalPages(blogposts.getTotalPages());
        thePostResponse.setIsLast(blogposts.isLast());

        return thePostResponse;
    }
}