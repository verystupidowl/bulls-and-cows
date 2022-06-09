package com.tgc.bullsAndCows.service;

import com.tgc.bullsAndCows.MainGame;
import com.tgc.bullsAndCows.model.Game;
import com.tgc.bullsAndCows.model.Player;
import com.tgc.bullsAndCows.model.Step;
import com.tgc.bullsAndCows.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Player savePlayer(Player player) {
        Player player1 = playerRepository.findAll().stream()
                .filter(p -> p.getName().equals(player.getName()))
                .findAny()
                .orElse(null);
        if (player1 == null) {
            playerRepository.save(player);
            return player;
        } else {
            playerRepository.save(player1);
            return player1;
        }
    }

    @Override
    public Player findPlayer(int id) {
        return playerRepository.findAll().stream().filter(p -> p.getId() == id).findAny().orElse(null);
    }

    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Player deletePlayer(int id) {
        Player deletedPlayer = playerRepository.findAll().stream().filter(p -> p.getId() == id).findAny().orElse(null);
        assert deletedPlayer != null;
        playerRepository.delete(deletedPlayer);
        return deletedPlayer;
    }

    @Override
    public Game addGame(int id, Game game) {
        Player player = playerRepository.findAll().stream().filter(p -> p.getId() == id).findAny().orElse(null);
        assert player != null;
        player.addGame(game);
        playerRepository.save(player);
        System.out.println(player);
        return player.getGames().get(player.getGames().size() - 1);
    }

    @Override
    public Game addStep(int id, Step step) {
        Player player = playerRepository.findAll().stream().filter(p -> p.getId() == id).findAny().orElse(null);
        assert player != null;
        Step step1;
        Game rightGame = player.getGames().get(player.getGames().size() - 1);
        step1 = MainGame.mainGame(step, rightGame.getRightAnswer());
        Objects.requireNonNull(player.getGames().stream().filter(g -> g.getId() == step.getId()).findAny().orElse(null)).addStep(step1);
        playerRepository.save(player);
        return Objects.requireNonNull(player.getGames().stream().filter(g -> g.getId() == step.getId()).findAny().orElse(null));
    }
}
