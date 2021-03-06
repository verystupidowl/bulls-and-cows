package com.tgc.bullsAndCows.service;

import com.tgc.bullsAndCows.Utils.GameUtils;
import com.tgc.bullsAndCows.Utils.MappingUtils;
import com.tgc.bullsAndCows.dto.GameDTO;
import com.tgc.bullsAndCows.dto.PlayerDTO;
import com.tgc.bullsAndCows.dto.StepDTO;
import com.tgc.bullsAndCows.entity.Game;
import com.tgc.bullsAndCows.entity.Player;
import com.tgc.bullsAndCows.entity.Step;
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
        GameUtils gameUtils = new GameUtils();
        Player player = mappingUtils.mapToPlayerEntity(findPlayer(id));
        player.setId(id);
        Step step1;
        Game rightGame = player.getGames().get(player.getGames().size() - 1);
        gameUtils.getBullsAndCowsCount(step.getAnswer(), rightGame.getRightAnswer());
        step1 = new Step(gameUtils.getCows(), gameUtils.getBulls(), step.getAnswer(), new Date().getTime());
        Objects.requireNonNull(player.getGames().stream().filter(g -> g.getId() == rightGame.getId()).findAny().orElse(null)).addStep(step1);
        playerRepository.save(player);
        Game returnGame = Objects.requireNonNull(player.getGames().stream().filter(g -> g.getId() == rightGame.getId()).findAny().orElse(null));
        return mappingUtils.mapToGameDto(returnGame);
    }

    @Override
    public GameDTO setIsGuessed(int id) {
        Player player = mappingUtils.mapToPlayerEntity(findPlayer(id));
        player.setId(id);
        Game game = player.getGames().get(player.getGames().size() - 1);
        game.setIsGuessed(1);
        playerRepository.save(player);
        return mappingUtils.mapToGameDto(game);
    }

    @Override
    public Long getLimit(int id) {
        PlayerDTO playerDTO = findPlayer(id);
        if (!playerDTO.getGames().isEmpty()) {
            GameDTO gameDTO = playerDTO.getGames().get(playerDTO.getGames().size() - 1);
            switch (gameDTO.getLimitation()) {
                case TIME: {
                    long end = gameDTO
                            .getStartTime() + 300000;
                    return end - new Date().getTime() > 0 ? end - new Date().getTime() : (long) -1;
                }
                case STEPS: {
                    long end = 10;
                    return end - gameDTO.getSteps().size() > 0 ? end - gameDTO.getSteps().size() : (long) -1;
                }
                case WITHOUT: {
                    return (long) -2;
                }
                default: {
                    return (long) -3;
                }
            }
        } else
            return (long) -100;
    }
}
