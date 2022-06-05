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

    public Game(int stepCount, int time) {
        this.stepCount = stepCount;
        this.time = time;
    }
}
