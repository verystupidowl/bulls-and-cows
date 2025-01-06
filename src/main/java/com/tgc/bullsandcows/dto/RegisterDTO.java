package com.tgc.bullsandcows.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record RegisterDTO(
        @NotEmpty(message = "Email should not be empty!")
        @Email(message = "Email should be valid!")
        String email,
        @NotEmpty(message = "Name should not be empty!")
        @Size(min = 2, max = 20, message = "Name length should be between 2 and 20 characters")
        String name,
        @NotEmpty(message = "Password should not be empty!")
        @Size(min = 3, max = 30, message = "Password length should be between 3 and 30 characters")
        String password,
        @NotEmpty(message = "Password confirmation should not be empty!")
        String passwordConfirmation,
        @NotEmpty(message = "Gender should not be empty!")
        String gender
) {
}
