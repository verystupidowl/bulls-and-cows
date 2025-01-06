package com.tgc.bullsandcows.service.impl;

import com.tgc.bullsandcows.dto.AnswerDTO;
import com.tgc.bullsandcows.dto.Filter;
import com.tgc.bullsandcows.dto.GameDTO;
import com.tgc.bullsandcows.dto.GameStatisticDTO;
import com.tgc.bullsandcows.dto.GameWithStepsDTO;
import com.tgc.bullsandcows.entity.Game;
import com.tgc.bullsandcows.entity.Limitation;
import com.tgc.bullsandcows.entity.Player;
import com.tgc.bullsandcows.exception.GameNotFoundException;
import com.tgc.bullsandcows.mapper.GameMapper;
import com.tgc.bullsandcows.repository.GameRepository;
import com.tgc.bullsandcows.service.GameService;
import com.tgc.bullsandcows.service.LimitService;
import com.tgc.bullsandcows.service.PlayerService;
import com.tgc.bullsandcows.util.GameUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final PlayerService playerService;
    private final LimitService limitService;
    private final GameMapper gameMapper;

    @Override
    public GameDTO getGameById(Long gameId) {
        return gameMapper.toDto(getGame(gameId));
    }

    @Override
    public Game getGame(Long gameId) {
        return gameRepository.findById(gameId)
                .orElseThrow(() -> new GameNotFoundException("Game with id " + gameId + " not found"));
    }

    @Override
    public List<GameStatisticDTO> getGamesByPlayer(Long playerId) {
        Player player = playerService.findPlayer(playerId);
        return gameRepository.findGamesByPlayer(player).stream()
                .map(gameMapper::toStatisticDTO)
                .toList();
    }

    @Override
    public void saveGame(Game game) {
        gameRepository.save(game);
    }

    @Override
    public GameDTO addGame(Long playerId, int answer) {
        Limitation limitation = limitService.getLimitation();
        Game game = Game.builder()
                .rightAnswer(answer)
                .isGuessed(false)
                .startTime(LocalDateTime.now())
                .limitation(limitation)
                .build();
        Player player = playerService.findPlayer(playerId);
        game.setPlayer(player);
        gameRepository.save(game);
        return gameMapper.toDto(game);
    }


    @Override
    public void setIsGuessed(Long gameId) {
        gameRepository.findById(gameId)
                .ifPresent(game -> {
                    game.setGuessed(true);
                    gameRepository.save(game);
                });
    }


    @Override
    public AnswerDTO getBullsAndCowsCount(int userAnswer, int rightAnswer) {
        if (userAnswer == rightAnswer) {
            return new AnswerDTO(
                    4,
                    0,
                    true
            );
        }
        int cows = 0;
        int bulls = 0;
        AnswerDTO dto = new AnswerDTO();
        StringBuilder answerBuilder = new StringBuilder(String.valueOf(userAnswer));
        StringBuilder rightAnswerBuilder = new StringBuilder(String.valueOf(rightAnswer));

        String answerString = String.valueOf(userAnswer);
        String rightAnswerString = String.valueOf(rightAnswer);

        for (int i = 0; i < 4; ++i) {
            if (answerString.charAt(i) == rightAnswerString.charAt(i)) {
                bulls++;
                answerBuilder.setCharAt(i, ' ');
                rightAnswerBuilder.setCharAt(i, ' ');
            }
        }
        answerString = answerBuilder.toString();
        rightAnswerString = rightAnswerBuilder.toString();

        for (int i = 0; i < 4; ++i) {
            if (answerString.charAt(i) == ' ')
                continue;
            int countLeft = countBetween(answerString.charAt(i), answerString, i);
            int countInComputerNumber = countBetween(
                    answerString.charAt(i), rightAnswerString, rightAnswerString.length());
            if (countInComputerNumber != 0
                    && countLeft < countInComputerNumber)
                cows++;
        }
        dto.setCows(cows);
        dto.setBulls(bulls);
        dto.setIsPlayerGuessed(false);
        return dto;
    }

    @Override
    public GameWithStepsDTO getGameWithSteps(Long gameId, Long playerId) {
        Game game = getGame(gameId);
        if (!game.getPlayer().getId().equals(playerId)) {
            throw new GameNotFoundException("Game with id " + gameId + " not found");
        }
        return gameMapper.toGameWithStepsDTO(getGame(gameId));
    }

    @Override
    public List<GameStatisticDTO> getGamesByFilter(Filter filter) {
        return gameRepository.findAll().stream()
                .limit(filter.getGamesPerPage() * filter.getPageNumber())
                .sorted(GameUtils.gameComparator(filter.getSort()))
                .map(gameMapper::toStatisticDTO)
                .toList();
    }

    private int countBetween(char what, String where, int to) {
        int count = 0;
        for (int i = 0; i < to; i++) {
            if (where.charAt(i) == what)
                count++;
        }
        return count;
    }
}
