package com.tgc.bullsandcows.service.impl;

import com.tgc.bullsandcows.dto.AnswerDTO;
import com.tgc.bullsandcows.dto.StepDTO;
import com.tgc.bullsandcows.entity.Game;
import com.tgc.bullsandcows.entity.Step;
import com.tgc.bullsandcows.exception.GameEndedException;
import com.tgc.bullsandcows.mapper.GameMapper;
import com.tgc.bullsandcows.mapper.StepMapper;
import com.tgc.bullsandcows.repository.StepRepository;
import com.tgc.bullsandcows.service.GameService;
import com.tgc.bullsandcows.service.LimitService;
import com.tgc.bullsandcows.service.StepService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StepServiceImpl implements StepService {
    private final StepRepository stepRepository;
    private final GameService gameService;
    private final GameMapper gameMapper;
    private final StepMapper stepMapper;
    private final LimitService limitService;

    @Override
    public void addStep(Long gameId, StepDTO dto) {
        if (Boolean.TRUE.equals(limitService.isEnded(gameId))) {
            throw new GameEndedException();
        }
        Game game = gameService.getGame(gameId);
        Step step = stepMapper.toEntity(dto);
        AnswerDTO answerDTO = gameService.getBullsAndCowsCount(dto.getAnswer(), game.getRightAnswer());
        if (Boolean.TRUE.equals(answerDTO.getIsPlayerGuessed())) {
            gameService.setIsGuessed(gameId);
        }
        step.setGame(game);
        step.setTime(LocalDateTime.now());
        step.setCows(answerDTO.getCows());
        step.setBulls(answerDTO.getBulls());
        game.getSteps().add(step);
        gameService.saveGame(game);
    }

    public List<StepDTO> getStepsByGameId(Long gameId) {
        Game game = gameMapper.toEntity(gameService.getGameById(gameId));
        return stepRepository.findAllByGame(game).stream()
                .map(stepMapper::toDto)
                .toList();
    }
}
