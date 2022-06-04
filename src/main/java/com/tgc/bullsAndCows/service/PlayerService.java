package com.tgc.bullsAndCows.service;

import com.tgc.bullsAndCows.model.Player;

public interface PlayerService {
    void savePlayer(Player player);
    Player findPlayer(int id);
}
