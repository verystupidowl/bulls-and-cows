package com.tgc.bullsAndCows.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Step {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int cows;
    private int bulls;
    private int answer;

    public Step(int cows, int bulls, int answer) {
        this.cows = cows;
        this.bulls = bulls;
        this.answer = answer;
    }
}
