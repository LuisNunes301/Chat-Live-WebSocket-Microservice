package com.dev.userService.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRecordDto(@NotBlank String password,
        @NotBlank @Email String email) {
}