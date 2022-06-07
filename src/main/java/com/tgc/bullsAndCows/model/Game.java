package com.tgc.bullsAndCows.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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
    @Column(name = "RIGHT_ANSWER")
    private int rightAnswer;
    @Column(name = "is_guessed")
    private int isGuessed;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id")
    private List<Step> steps;

    public Game(int stepCount, int time, int rightAnswer, int isGuessed) {
        this.stepCount = stepCount;
        this.time = time;
        this.rightAnswer = rightAnswer;
        this.isGuessed = isGuessed;
    }

    public void addStep(Step step) {
        setStepCount(stepCount += 1);
        steps.add(step);
    }
}
