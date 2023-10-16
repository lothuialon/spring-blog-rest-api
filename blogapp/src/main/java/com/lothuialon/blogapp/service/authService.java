package com.lothuialon.blogapp.service;

import com.lothuialon.blogapp.DTOs.loginDTO;
import com.lothuialon.blogapp.DTOs.registerDTO;

public interface authService {
    String login(loginDTO loginDTO);
    String register(registerDTO registerDTO);
}
