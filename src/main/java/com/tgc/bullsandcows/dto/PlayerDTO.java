package com.tgc.bullsandcows.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
public class PlayerDTO {
    Long id;
    @NotEmpty(message = "Name should not be empty!")
    @Size(min = 2, max = 10, message = "Name size should be between 2 and 10 characters!")
    String name;
}
