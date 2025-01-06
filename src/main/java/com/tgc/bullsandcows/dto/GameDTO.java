package com.tgc.bullsandcows.dto;

import com.tgc.bullsandcows.entity.Limitation;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
public class GameDTO {
    Long id;
    int rightAnswer;
    Boolean isGuessed;
    Limitation limitation;
    LocalDateTime startTime;
}
