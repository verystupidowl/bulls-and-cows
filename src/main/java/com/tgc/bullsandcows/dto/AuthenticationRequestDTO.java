package com.tgc.bullsandcows.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record AuthenticationRequestDTO(
        @NotEmpty(message = "Email should not be empty!")
        @Email(message = "Email should be valid!")
        String email,
        @NotEmpty(message = "Password should not be empty!")
        String password
) {
}
