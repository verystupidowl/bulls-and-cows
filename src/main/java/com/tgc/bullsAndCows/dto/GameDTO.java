package com.tgc.bullsAndCows.dto;

import com.tgc.bullsAndCows.entity.Limitation;
import lombok.Data;

import java.util.List;

@Data
public class GameDTO {

    private int id;
    private int rightAnswer;
    private int isGuessed;
    private Limitation limitation;
    private long startTime;
    private List<StepDTO> steps;
}
