package com.tgc.bullsAndCows.service;

import com.tgc.bullsAndCows.model.Player;
import com.tgc.bullsAndCows.model.Step;
import com.tgc.bullsAndCows.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public void savePlayer(Player player) {
        List<Step> steps = new ArrayList<>();
        steps.add(new Step(1,2));
        player.setSteps(steps);
        steps.get(0).setPlayer(player);
        System.out.println(player);
        playerRepository.save(player);
    }

    @Override
    public Player findPlayer(int id) {
        System.out.println(playerRepository.findAll().stream().filter(p -> p.getId() == id).findAny().orElse(null));
        return playerRepository.findAll().stream().filter(p -> p.getId() == id).findAny().orElse(null);
    }

    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }
}
