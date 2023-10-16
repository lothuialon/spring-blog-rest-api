package com.lothuialon.blogapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.lothuialon.blogapp.security.JwtAuthenticationEntryPoint;
import com.lothuialon.blogapp.security.JwtAuthenticationFilter;

@Configuration
public class securityConfig {
    


    private UserDetailsService userDetailsService;
    
    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    private JwtAuthenticationFilter authenticationFilter;

    @Autowired
    public securityConfig(UserDetailsService theUserDetailsService,
    JwtAuthenticationEntryPoint theAuthenticationEntryPoint,
    JwtAuthenticationFilter theAuthenticationFilter) {
        
        this.authenticationEntryPoint = theAuthenticationEntryPoint;
        this.userDetailsService = theUserDetailsService;
        this.authenticationFilter = theAuthenticationFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{

        return configuration.getAuthenticationManager();
    }


    @Bean
    public static PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();

    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{


        http.csrf((csrf) -> csrf.disable())
        .authorizeHttpRequests((authorize) -> authorize
        .requestMatchers(HttpMethod.GET, "/api/**").permitAll()
        .requestMatchers("/api/auth/**").permitAll()
        .anyRequest().authenticated())
        .httpBasic(Customizer.withDefaults())
        .exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint))
        .sessionManagement( session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); //since jwt is stateless authenticaiton

        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }

    /*  in memory authentication is not used
    @Bean
    public UserDetailsManager userDetailsManager(){

        UserDetails x = User.builder()
        .username("")
        .password(passwordEncoder()
        .encode(""))
        .roles("ADMIN").build();

        return new InMemoryUserDetailsManager(cannosa); 
    }
    */

}
