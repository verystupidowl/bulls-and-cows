package com.tgc.bullsandcows.service;

import com.tgc.bullsandcows.dto.PlayerDTO;
import com.tgc.bullsandcows.dto.ProfileDTO;
import com.tgc.bullsandcows.entity.Player;

import java.util.List;

public interface PlayerService {

    PlayerDTO savePlayer(PlayerDTO player);

    PlayerDTO findPlayerById(Long id);

    Player findPlayer(Long id);

    List<PlayerDTO> getAllPlayers();

    ProfileDTO getProfile(Long id);
}
