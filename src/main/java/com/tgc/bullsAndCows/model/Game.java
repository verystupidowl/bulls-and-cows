package com.tgc.bullsAndCows.model;

import com.tgc.bullsAndCows.ConfigUnit;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "GAME")
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "RIGHT_ANSWER")
    private int rightAnswer;
    @Column(name = "is_guessed")
    private int isGuessed;
//    @Value("${limitation}")
    @Column(name = "limitation")
    private String limitation;
    @Column(name = "start_time")
    private long startTime;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id")
    @ToString.Exclude
    private List<Step> steps;

    public Game(int rightAnswer, int isGuessed, String limitation, long startTime) {
        this.rightAnswer = rightAnswer;
        this.isGuessed = isGuessed;
        this.limitation = limitation;
        this.startTime = startTime;
    }

    public Game(int rightAnswer, int isGuessed, long startTime) {
        this.rightAnswer = rightAnswer;
        this.isGuessed = isGuessed;
        this.startTime = startTime;
        this.limitation = ConfigUnit.getProperty();
    }

    public void addStep(Step step) {
        steps.add(step);
    }
}
