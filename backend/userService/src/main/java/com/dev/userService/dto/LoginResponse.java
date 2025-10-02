package com.dev.userService.dto;

public record LoginResponse(
        String message,
        String email,
        long expiresIn) {
}