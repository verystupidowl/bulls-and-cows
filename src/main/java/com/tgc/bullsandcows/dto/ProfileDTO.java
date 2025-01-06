package com.tgc.bullsandcows.dto;

import com.tgc.bullsandcows.entity.Gender;
import com.tgc.bullsandcows.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class ProfileDTO {
    Long id;
    String name;
    Gender gender;
    String email;
    Role role;
    Boolean isActive;
}
