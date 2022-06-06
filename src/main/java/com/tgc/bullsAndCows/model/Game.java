package com.tgc.bullsAndCows.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "GAME")
@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "STEP_COUNT")
    private int stepCount;
    @Column(name = "TIME")
    private int time;
    @Column(name = "ANSWER")
    private int answer;

    public Game(int stepCount, int time, int answer) {
        this.stepCount = stepCount;
        this.time = time;
        this.answer = answer;
    }
}
