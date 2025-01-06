package com.tgc.bullsandcows.service.impl;

import com.tgc.bullsandcows.dto.PlayerDTO;
import com.tgc.bullsandcows.dto.ProfileDTO;
import com.tgc.bullsandcows.entity.Player;
import com.tgc.bullsandcows.exception.PlayerNotFoundException;
import com.tgc.bullsandcows.mapper.PlayerMapper;
import com.tgc.bullsandcows.repository.PlayerRepository;
import com.tgc.bullsandcows.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    @Override
    public PlayerDTO savePlayer(PlayerDTO dto) {
        return playerRepository.findByName(dto.getName())
                .map(p -> {
                    playerRepository.save(p);
                    return playerMapper.toDto(p);
                })
                .orElseGet(() -> {
                    Player player = playerRepository.save(playerMapper.toEntity(dto));
                    return playerMapper.toDto(player);
                });
    }

    @Override
    public PlayerDTO findPlayerById(Long id) {
        return playerMapper.toDto(findPlayer(id));
    }

    @Override
    public Player findPlayer(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException("Player with ID " + id + " didn't found!"));
    }

    @Override
    public List<PlayerDTO> getAllPlayers() {
        return playerRepository.findAll().stream()
                .map(playerMapper::toDto)
                .toList();
    }

    @Override
    public ProfileDTO getProfile(Long id) {
        return playerMapper.toProfileDTO(findPlayer(id));
    }
}
