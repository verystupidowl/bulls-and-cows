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
    @Column(name = "is_guessed")
    private int isGuessed;

    public Game(int stepCount, int time, int answer, int isGuessed) {
        this.stepCount = stepCount;
        this.time = time;
        this.answer = answer;
        this.isGuessed = isGuessed;
    }

    public void addStep() {
        setStepCount(stepCount += 1);
    }
}
