package com.dev.userService.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    private static final long EXPIRATION_TIME = 86400000;

    public String generateToken(String username) {
        long now = System.currentTimeMillis();
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(now + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(secret.getBytes()));
    }

    public String validateTokenAndGetUsername(String token) {
        return JWT.require(Algorithm.HMAC512(secret.getBytes()))
                .build()
                .verify(token)
                .getSubject();
    }
}
