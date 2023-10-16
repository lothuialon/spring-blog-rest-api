package com.lothuialon.blogapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lothuialon.blogapp.DTOs.JwtAuthenticationResponse;
import com.lothuialon.blogapp.DTOs.loginDTO;
import com.lothuialon.blogapp.DTOs.registerDTO;
import com.lothuialon.blogapp.service.authService;

@RestController
@RequestMapping("/api/auth")
public class authController {
    
    private authService authService;

    @Autowired
    public authController(authService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody loginDTO loginDTO){

        String token = authService.login(loginDTO);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthenticationResponse);

    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody registerDTO registerDTO){

        String response = authService.register(registerDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

}
