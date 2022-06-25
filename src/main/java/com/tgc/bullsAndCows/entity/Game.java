package com.tgc.bullsAndCows.entity;

import com.tgc.bullsAndCows.Utils.ConfigLimitation;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "GAME")
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@ToString
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "RIGHT_ANSWER")
    private int rightAnswer;

    @Column(name = "is_guessed")
    private int isGuessed;

    @Column(name = "limitation")
    private Limitation limitation;

    @Column(name = "start_time")
    private long startTime;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id")
    private List<Step> steps;

    public Game(int rightAnswer, int isGuessed, long startTime) {
        this.rightAnswer = rightAnswer;
        this.isGuessed = isGuessed;
        this.startTime = startTime;
        this.limitation = ConfigLimitation.getProperty();
    }

    public void addStep(Step step) {
        steps.add(step);
    }
}
