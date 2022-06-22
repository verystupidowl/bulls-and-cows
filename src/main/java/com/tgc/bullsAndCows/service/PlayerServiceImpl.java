package com.tgc.bullsAndCows.service;

import com.tgc.bullsAndCows.MainGame;
import com.tgc.bullsAndCows.dto.GameDTO;
import com.tgc.bullsAndCows.dto.PlayerDTO;
import com.tgc.bullsAndCows.dto.StepDTO;
import com.tgc.bullsAndCows.model.Game;
import com.tgc.bullsAndCows.model.Player;
import com.tgc.bullsAndCows.model.Step;
import com.tgc.bullsAndCows.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final MappingUtils mappingUtils;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, MappingUtils mappingUtils) {
        this.playerRepository = playerRepository;
        this.mappingUtils = mappingUtils;
    }

    @Override
    public PlayerDTO savePlayer(PlayerDTO player) {
        Player player1 = playerRepository.findAll().stream().filter(p -> p.getName().equals(player.getName())).findAny().orElse(null);
        if (player1 == null) {
            return mappingUtils.mapToPlayerDto(playerRepository.save(mappingUtils.mapToPlayerEntity(player)));
        } else {
            playerRepository.save(player1);
            return mappingUtils.mapToPlayerDto(player1);
        }
    }

    @Override
    public PlayerDTO findPlayer(int id) {
        return mappingUtils.mapToPlayerDto(Objects.requireNonNull(playerRepository.findAll().stream().filter(p -> p.getId() == id).findAny().orElse(null)));
    }

    @Override
    public List<PlayerDTO> getAllPlayers() {
        return playerRepository.findAll().stream().map(mappingUtils::mapToPlayerDto).collect(Collectors.toList());
    }

    @Override
    public GameDTO addGame(int id, int answer) {
        Game game = new Game(answer, 0, new Date().getTime());
        Player player = mappingUtils.mapToPlayerEntity(findPlayer(id));
        player.setId(id);
        player.addGame(game);
        playerRepository.save(player);
        return mappingUtils.mapToGameDto(player.getGames().get(player.getGames().size() - 1));
    }

    @Override
    public GameDTO addStep(int id, StepDTO step) {
        Player player = mappingUtils.mapToPlayerEntity(findPlayer(id));
        player.setId(id);
        System.out.println(player);
        Step step1;
        Game rightGame = player.getGames().get(player.getGames().size() - 1);
        step1 = MainGame.mainGame(step.getAnswer(), rightGame.getRightAnswer());
        Objects.requireNonNull(player.getGames().stream().filter(g -> g.getId() == step.getId()).findAny().orElse(null)).addStep(step1);
        playerRepository.save(player);
        Game returnGame = Objects.requireNonNull(player.getGames().stream().filter(g -> g.getId() == step.getId()).findAny().orElse(null));
        return mappingUtils.mapToGameDto(returnGame);
    }
}
