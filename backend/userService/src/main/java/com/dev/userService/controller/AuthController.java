package com.dev.userService.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.userService.dto.LoginRequest;
import com.dev.userService.dto.LoginResponse;
import com.dev.userService.dto.MessageResponse;
import com.dev.userService.models.User;
import com.dev.userService.service.AuthService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody @Valid LoginRequest loginRequest,
            HttpServletResponse response) {

        User user = authService.authenticate(loginRequest.email(), loginRequest.password());
        String token = authService.generateToken(user);

        ResponseCookie cookie = buildJwtCookie(token);
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.ok(
                new LoginResponse("Login realizado com sucesso!", user.getEmail(), cookie.getMaxAge().getSeconds()));
    }

    @PostMapping("/logout")
    public ResponseEntity<MessageResponse> logout(HttpServletResponse response) {
        ResponseCookie cookie = buildJwtCookie("");
        cookie = ResponseCookie.from("jwt", "")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(0)
                .sameSite("Strict")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return ResponseEntity.ok(new MessageResponse("Logout realizado com sucesso!"));
    }

    private ResponseCookie buildJwtCookie(String token) {
        return ResponseCookie.from("jwt", token)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(24 * 60 * 60) /
                .sameSite("Strict")
                .build();
    }
}