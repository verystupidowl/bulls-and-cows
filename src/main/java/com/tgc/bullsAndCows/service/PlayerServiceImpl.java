package com.tgc.bullsAndCows.service;

import com.tgc.bullsAndCows.model.Player;
import com.tgc.bullsAndCows.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public void savePlayer(Player player) {
        playerRepository.save(player);
    }

    @Override
    public Player findPlayer(int id) {
        System.out.println(playerRepository.getReferenceById(1));
        return playerRepository.getReferenceById(id);
    }
}
