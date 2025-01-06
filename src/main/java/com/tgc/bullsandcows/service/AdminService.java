package com.tgc.bullsandcows.service;

import com.tgc.bullsandcows.dto.PlayerDTO;
import com.tgc.bullsandcows.dto.ProfileDTO;

import java.util.List;

public interface AdminService {

    List<PlayerDTO> getAllPlayers();

    ProfileDTO getOnePlayer(Long id);

    ProfileDTO setBlocked(long id);
}
