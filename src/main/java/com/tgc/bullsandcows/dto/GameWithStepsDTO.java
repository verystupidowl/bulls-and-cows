package com.tgc.bullsandcows.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@Accessors(chain = true)
public class GameWithStepsDTO {
    String id;
    Integer rightAnswer;
    boolean isGuessed;
    List<StepDTO> steps;
}
