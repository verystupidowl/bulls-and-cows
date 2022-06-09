package com.tgc.bullsAndCows.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

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
    @Column(name = "RIGHT_ANSWER")
    private int rightAnswer;
    @Column(name = "is_guessed")
    private int isGuessed;
    @Value("${limitation}")
    @Column(name = "limitation")
    private String limitation;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id")
    @ToString.Exclude
    private List<Step> steps;

    public Game(int rightAnswer, int isGuessed) {
        this.rightAnswer = rightAnswer;
        this.isGuessed = isGuessed;
    }

    public void addStep(Step step) {
        steps.add(step);
    }
}
