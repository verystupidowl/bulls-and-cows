package com.tgc.bullsandcows.service.impl;

import com.tgc.bullsandcows.dto.PlayerDTO;
import com.tgc.bullsandcows.dto.ProfileDTO;
import com.tgc.bullsandcows.entity.Player;
import com.tgc.bullsandcows.exception.PlayerNotFoundException;
import com.tgc.bullsandcows.mapper.PlayerMapper;
import com.tgc.bullsandcows.repository.PlayerRepository;
import com.tgc.bullsandcows.service.AdminService;
import com.tgc.bullsandcows.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final PlayerService playerService;
    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    @Override
    public List<PlayerDTO> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @Override
    public ProfileDTO getOnePlayer(Long id) {
        return playerService.getProfile(id);
    }

    @Override
    public ProfileDTO setBlocked(long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException("Player with id " + id + " not found"));
        player.setIsActive(!player.getIsActive());
        return playerMapper.toProfileDTO(playerRepository.save(player));
    }
}
