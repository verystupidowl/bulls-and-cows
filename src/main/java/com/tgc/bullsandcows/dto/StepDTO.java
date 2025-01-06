package com.tgc.bullsandcows.dto;

import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
public class StepDTO {
    Long id;
    AnswerDTO answerBullsAndCows;
    int answer;
    LocalDateTime time;
}
