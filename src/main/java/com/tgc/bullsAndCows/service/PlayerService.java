package com.tgc.bullsAndCows.service;

import com.tgc.bullsAndCows.dto.GameDTO;
import com.tgc.bullsAndCows.dto.PlayerDTO;
import com.tgc.bullsAndCows.dto.StepDTO;

import java.util.List;

public interface PlayerService {

    PlayerDTO savePlayer(PlayerDTO player);

    PlayerDTO findPlayer(int id);

    List<PlayerDTO> getAllPlayers();

    GameDTO addGame(int id, int answer);

    GameDTO addStep(int id, StepDTO step);

    GameDTO setIsGuessed(int id);

    Long getLimit(int id);
}
