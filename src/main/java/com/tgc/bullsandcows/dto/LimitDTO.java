package com.tgc.bullsandcows.dto;

import com.tgc.bullsandcows.entity.Limitation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = PRIVATE)
public class LimitDTO {
    Limitation code;
    Long value;
    Boolean isEnded;
}
