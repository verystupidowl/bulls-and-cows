package com.tgc.bullsAndCows.model;

import com.tgc.bullsAndCows.ConfigLimitation;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

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
//    @Value("${limitation}")
    @Column(name = "limitation")
    private String limitation;
    @Column(name = "start_time")
    private long startTime;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id")
    private List<Step> steps;

//    public Game(int rightAnswer, int isGuessed, String limitation, long startTime) {
//        this.rightAnswer = rightAnswer;
//        this.isGuessed = isGuessed;
//        this.limitation = limitation;
//        this.startTime = startTime;
//    }

    public Game(int rightAnswer, int isGuessed, long startTime) {
        this.rightAnswer = rightAnswer;
        this.isGuessed = isGuessed;
        this.startTime = startTime;
        this.limitation = ConfigLimitation.getProperty();
    }

    public Game(int rightAnswer, int isGuessed, String limitation, long startTime, List<Step> steps) {
        this.rightAnswer = rightAnswer;
        this.isGuessed = isGuessed;
        this.startTime = startTime;
        this.limitation = limitation;
        this.steps = steps;
    }

    public void addStep(Step step) {
        steps.add(step);
    }
}
