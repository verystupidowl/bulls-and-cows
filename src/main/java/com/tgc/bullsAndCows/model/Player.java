package com.tgc.bullsAndCows.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id")
    private List<Game> games;

    public void addGame(Game game) {
        games.add(game);
    }

    public Player(String name, List<Game> games) {
        this.name = name;
        this.games = games;
    }
}