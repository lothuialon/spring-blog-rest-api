package com.lothuialon.blogapp.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.lothuialon.blogapp.exception.BlogApiException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;
    @Value("${app.jwt-expiration-miliseconds}")
    private long jwtExpire;

    public String generateToken(Authentication authentication) {

        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpire);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .signWith(key())
                .compact();

        return token;

    }

    private Key key() {

        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret));

    }

    // get username from token
    public String getUsername(String token) {

        String username = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        return username;

    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(token);

            return true;
        } catch (MalformedJwtException exc) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Invalid token");
        } catch (ExpiredJwtException exc) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Expired token");
        } catch (UnsupportedJwtException exc) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Unsupported token");
        } catch (IllegalArgumentException exc) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Illegal token");
        }

    }

}
