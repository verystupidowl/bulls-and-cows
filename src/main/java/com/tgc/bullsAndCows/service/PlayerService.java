package com.tgc.bullsAndCows.service;

import com.tgc.bullsAndCows.model.Player;
import com.tgc.bullsAndCows.model.Step;

import java.util.List;

public interface PlayerService {
    void savePlayer(Player player);
    Player findPlayer(int id);
    List<Player> getAllPlayers();
    Player deletePlayer(int id);
    void addStep(int id, Step step);
}
