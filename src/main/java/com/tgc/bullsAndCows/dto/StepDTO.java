package com.tgc.bullsAndCows.dto;

import lombok.Data;

@Data
public class StepDTO {

    private int id;
    private int cows;
    private int bulls;
    private int answer;
    private long time;
}
