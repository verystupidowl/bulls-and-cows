package com.tgc.bullsAndCows.entity;

import jakarta.persistence.*;
import lombok.*;


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
    private long time;

    public Step(int cows, int bulls, int answer, long time) {
        this.cows = cows;
        this.bulls = bulls;
        this.answer = answer;
        this.time = time;
    }
}
