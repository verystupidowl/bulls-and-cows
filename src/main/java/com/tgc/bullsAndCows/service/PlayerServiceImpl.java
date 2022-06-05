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
        steps.add(new Step(1, 2));
        player.setSteps(steps);
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

    @Override
    public Player deletePlayer(int id) {
        Player deletedPlayer = playerRepository.findAll().stream().filter(p -> p.getId() == id).findAny().orElse(null);
        System.out.println(deletedPlayer);
        assert deletedPlayer != null;
        playerRepository.delete(deletedPlayer);
        return deletedPlayer;
    }

    @Override
    public void addStep(int id, Step step) {
        Player player = playerRepository.findAll().stream().filter(p -> p.getId() == id).findAny().orElse(null);
        System.out.println(player);
        assert player != null;
        player.addStep(step);
        playerRepository.save(player);
    }
}
