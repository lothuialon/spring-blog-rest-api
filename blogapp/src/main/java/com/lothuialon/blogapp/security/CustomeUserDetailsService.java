package com.lothuialon.blogapp.security;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lothuialon.blogapp.entity.user;
import com.lothuialon.blogapp.repository.userRepository;

@Service
public class CustomeUserDetailsService implements UserDetailsService{

    private userRepository userRepository;

    @Autowired
    public CustomeUserDetailsService(userRepository theUserRepository) {
        this.userRepository = theUserRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        user theUser = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Set<GrantedAuthority> authorities = theUser.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(theUser.getEmail(), theUser.getPassword(), authorities);
    }
    
}
