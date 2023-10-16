package com.lothuialon.blogapp.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lothuialon.blogapp.DTOs.loginDTO;
import com.lothuialon.blogapp.DTOs.registerDTO;
import com.lothuialon.blogapp.entity.role;
import com.lothuialon.blogapp.entity.user;
import com.lothuialon.blogapp.exception.BlogApiException;
import com.lothuialon.blogapp.repository.roleRepository;
import com.lothuialon.blogapp.repository.userRepository;
import com.lothuialon.blogapp.security.JwtTokenProvider;
import com.lothuialon.blogapp.service.authService;

@Service
public class authServiceImp implements authService{


    private AuthenticationManager authenticationManager;
    private userRepository theUserRepository;
    private roleRepository theRoleRepository;
    private PasswordEncoder thePasswordEncoder;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public authServiceImp(AuthenticationManager authenticationManager, userRepository theUserRepository, roleRepository theRoleRepository, PasswordEncoder thePasswordEncoder, JwtTokenProvider theJwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.theUserRepository = theUserRepository;
        this.theRoleRepository = theRoleRepository;
        this.thePasswordEncoder = thePasswordEncoder;
        this.jwtTokenProvider = theJwtTokenProvider;
    }


    @Override
    public String login(loginDTO loginDTO) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            loginDTO.getUsernameOrEmail(),
            loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

    @Override
    public String register(registerDTO registerDTO) {
    
        if(theUserRepository.existsByUsername(registerDTO.getUsername())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Username already exists");
        }

        if(theUserRepository.existsByEmail(registerDTO.getEmail())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Email already exists");
        }

        user newUser = new user();
        newUser.setUsername(registerDTO.getUsername());
        newUser.setEmail(registerDTO.getEmail());
        newUser.setPassword(thePasswordEncoder.encode(registerDTO.getPassword()));

        List<role> roles = new ArrayList<>();
        role theRole = theRoleRepository.findByName("ROLE_USER").get();
        roles.add(theRole);
        newUser.setRoles(roles);
        return "User is registered";

    }
    
}
