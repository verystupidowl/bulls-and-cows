package com.tgc.bullsandcows.service;

import com.tgc.bullsandcows.dto.AnswerDTO;
import com.tgc.bullsandcows.dto.Filter;
import com.tgc.bullsandcows.dto.GameDTO;
import com.tgc.bullsandcows.dto.GameStatisticDTO;
import com.tgc.bullsandcows.dto.GameWithStepsDTO;
import com.tgc.bullsandcows.entity.Game;

import java.util.List;

public interface GameService {

    GameDTO addGame(Long id, int answer);

    void saveGame(Game game);

    void setIsGuessed(Long gameId);

    GameDTO getGameById(Long gameId);

    Game getGame(Long gameId);

    List<GameStatisticDTO> getGamesByPlayer(Long playerId);

    AnswerDTO getBullsAndCowsCount(int userAnswer, int rightAnswer);

    GameWithStepsDTO getGameWithSteps(Long gameId, Long id);

    List<GameStatisticDTO> getGamesByFilter(Filter filter);
}
