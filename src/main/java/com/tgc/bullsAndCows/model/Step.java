package com.tgc.bullsAndCows.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Step {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int cows;
    private int bulls;
    private int answer;
    private int time;

    public Step(int cows, int bulls, int answer, int time) {
        this.cows = cows;
        this.bulls = bulls;
        this.answer = answer;
        this.time =  time;
    }
}
