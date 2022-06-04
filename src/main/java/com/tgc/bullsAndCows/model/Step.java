package com.tgc.bullsAndCows.model;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Step {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int cows;
    private int bulls;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PERSON_ID")
    private Player player;

    public Step(int cows, int bulls) {
        this.bulls = bulls;
        this.cows = cows;
    }
}
