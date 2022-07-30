package com.tgc.bullsAndCows.Utils;

import com.tgc.bullsAndCows.dto.GameDTO;
import com.tgc.bullsAndCows.dto.PlayerDTO;
import com.tgc.bullsAndCows.dto.StepDTO;
import com.tgc.bullsAndCows.entity.Game;
import com.tgc.bullsAndCows.entity.Player;
import com.tgc.bullsAndCows.entity.Step;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class MappingUtils {

    public StepDTO mapToStepDto(Step step) {
        StepDTO dto = new StepDTO();
        dto.setId(step.getId());
        dto.setTime(step.getTime());
        dto.setCows(step.getCows());
        dto.setBulls(step.getBulls());
        dto.setAnswer(step.getAnswer());
        return dto;
    }

    public Step mapToStepEntity(StepDTO dto) {
        return new Step(dto.getId(), dto.getCows(), dto.getBulls(), dto.getAnswer(), dto.getTime());
    }

    public GameDTO mapToGameDto(Game game) {
        GameDTO dto = new GameDTO();
        dto.setStartTime(game.getStartTime());
        dto.setId(game.getId());
        dto.setRightAnswer(game.getRightAnswer());
        dto.setSteps(
                game.getSteps() != null ?
                        game.getSteps().stream()
                                .map(this::mapToStepDto)
                                .collect(Collectors.toList()) : null
        );
        dto.setIsGuessed(game.getIsGuessed());
        dto.setLimitation(game.getLimitation());
        return dto;
    }

    public Game mapToGameEntity(GameDTO dto) {
        return new Game(
                dto.getId(),
                dto.getRightAnswer(),
                dto.getIsGuessed(),
                dto.getLimitation(),
                dto.getStartTime(),
                dto.getSteps() != null ? dto.getSteps().stream()
                        .map(this::mapToStepEntity)
                        .collect(Collectors.toList()) : null
        );
    }

    public PlayerDTO mapToPlayerDto(Player player) {
        PlayerDTO dto = new PlayerDTO();
        dto.setId(player.getId());
        dto.setName(player.getName());
        dto.setGames(
                player.getGames() != null ?
                        player.getGames().stream()
                                .map(this::mapToGameDto)
                                .collect(Collectors.toList()) : null
        );
        return dto;
    }

    public Player mapToPlayerEntity(PlayerDTO dto) {
        return new Player(
                dto.getName(),
                dto.getGames() != null ? dto.getGames().stream()
                        .map(this::mapToGameEntity)
                        .collect(Collectors.toList()) : null
        );
    }
}