package com.tgc.bullsandcows.controller;


import com.tgc.bullsandcows.dto.Filter;
import com.tgc.bullsandcows.dto.GameDTO;
import com.tgc.bullsandcows.dto.GameStatisticDTO;
import com.tgc.bullsandcows.dto.GameWithStepsDTO;
import com.tgc.bullsandcows.dto.LimitDTO;
import com.tgc.bullsandcows.dto.PlayerErrorResponseDTO;
import com.tgc.bullsandcows.entity.Player;
import com.tgc.bullsandcows.exception.GameNotFoundException;
import com.tgc.bullsandcows.exception.LimitException;
import com.tgc.bullsandcows.exception.PlayerNotFoundException;
import com.tgc.bullsandcows.service.GameService;
import com.tgc.bullsandcows.service.LimitService;
import com.tgc.bullsandcows.util.GameUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/game")
@CrossOrigin("http://localhost:3000/")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;
    private final LimitService limitService;

    @GetMapping("/getLimit/{gameId}")
    public LimitDTO getLimit(@PathVariable Long gameId) {
        return limitService.getLimit(gameId);
    }

    @PostMapping("/games")
    public List<GameStatisticDTO> getGamesByFilter(@RequestBody Filter filter) {
        return gameService.getGamesByFilter(filter);
    }

    @GetMapping("/getAllGames")
    public List<GameStatisticDTO> getAllGamesByPlayer(UsernamePasswordAuthenticationToken token) {
        Player player = (Player) token.getPrincipal();
        return gameService.getGamesByPlayer(player.getId());
    }

    @GetMapping("/startGame")
    public GameDTO startGame(UsernamePasswordAuthenticationToken token) {
        Player player = (Player) token.getPrincipal();
        return gameService.addGame(player.getId(), GameUtils.generateRandomNumber());
    }

    @GetMapping("/getGame/{gameId}")
    public GameWithStepsDTO getGame(@PathVariable Long gameId, UsernamePasswordAuthenticationToken token) {
        Player player = (Player) token.getPrincipal();
        return gameService.getGameWithSteps(gameId, player.getId());
    }

    @ExceptionHandler(GameNotFoundException.class)
    private ResponseEntity<PlayerErrorResponseDTO> handleGameNotFoundException(GameNotFoundException e) {
        var response = PlayerErrorResponseDTO.builder()
                .message(e.getMessage())
                .timestamp(System.currentTimeMillis())
                .build();
        return ResponseEntity.status(NOT_FOUND).body(response);
    }

    @ExceptionHandler
    private ResponseEntity<PlayerErrorResponseDTO> exceptionHandler(LimitException e) {
        var response = new PlayerErrorResponseDTO(
                e.getMessage(),
                System.currentTimeMillis(),
                null
        );
        return new ResponseEntity<>(response, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    private ResponseEntity<PlayerErrorResponseDTO> exceptionHandler(PlayerNotFoundException e) {
        var playerErrorResponse = new PlayerErrorResponseDTO(
                e.getMessage(),
                System.currentTimeMillis(),
                null
        );
        return new ResponseEntity<>(playerErrorResponse, NOT_FOUND);
    }
}
