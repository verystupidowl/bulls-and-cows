package com.tgc.bullsAndCows.service;

import com.tgc.bullsAndCows.model.Player;
import com.tgc.bullsAndCows.model.Game;
import com.tgc.bullsAndCows.model.Step;

import java.util.List;

public interface PlayerService {
    Player savePlayer(Player player);
    Player findPlayer(int id);
    List<Player> getAllPlayers();
    Player deletePlayer(int id);
    Game addGame(int id, Game game);
    Game addStep(int id, Step step);
}
