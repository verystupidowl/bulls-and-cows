package com.tgc.bullsAndCows.controllers;


import com.tgc.bullsAndCows.Utils.GameUtils;
import com.tgc.bullsAndCows.Utils.LimitException;
import com.tgc.bullsAndCows.Utils.PlayerErrorResponse;
import com.tgc.bullsAndCows.Utils.PlayerNotFoundException;
import com.tgc.bullsAndCows.dto.GameDTO;
import com.tgc.bullsAndCows.dto.PlayerDTO;
import com.tgc.bullsAndCows.dto.StepDTO;
import com.tgc.bullsAndCows.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
@CrossOrigin("http://localhost:3000/")
public class GameController {

    private final PlayerService playerService;

    @Autowired
    public GameController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/getLimit{id}")
    public Long getTimer(@PathVariable int id) {
        return playerService.getLimit(id);
    }

    @GetMapping("/getPlayer{id}")
    public PlayerDTO getPlayer(@PathVariable int id) {
        return playerService.findPlayer(id);
    }

    @GetMapping("/getAllPlayers")
    public List<PlayerDTO> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @PostMapping("/addStepToGame/{playerId}")
    public GameDTO addGameToPlayer(@PathVariable int playerId, @RequestBody StepDTO stepDTO) {
        GameDTO returnGame = playerService.addStep(playerId, stepDTO);
        if (returnGame.getRightAnswer() == stepDTO.getAnswer()) {
            returnGame = playerService.setIsGuessed(playerId);
        }
        return returnGame;
    }

    @GetMapping("/startGame{id}")
    public GameDTO startGame(@PathVariable int id) {
        return playerService.addGame(id, GameUtils.generateRandomNumber());
    }

    @ExceptionHandler
    private ResponseEntity<PlayerErrorResponse> exceptionHandler(LimitException e) {
        var response = new PlayerErrorResponse(
                e.getMessage(), System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    private ResponseEntity<PlayerErrorResponse> exceptionHandler(PlayerNotFoundException e) {
        var playerErrorResponse = new PlayerErrorResponse(
                e.getMessage(), System.currentTimeMillis()
        );
        return new ResponseEntity<>(playerErrorResponse, HttpStatus.NOT_FOUND);
    }
}
