package com.tgc.bullsAndCows.dto;

import lombok.Data;

import java.util.List;

@Data
public class GameDTO {

    private int id;
    private int rightAnswer;
    private int isGuessed;
    private String limitation;
    private long startTime;
    private List<StepDTO> steps;
}
