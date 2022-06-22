package com.tgc.bullsAndCows.controllers;


import com.tgc.bullsAndCows.dto.GameDTO;
import com.tgc.bullsAndCows.dto.PlayerDTO;
import com.tgc.bullsAndCows.dto.StepDTO;
import com.tgc.bullsAndCows.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/game")
@CrossOrigin("http://localhost:3000/")
public class MainController {

    private final PlayerService playerService;

    @Autowired
    public MainController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/addPlayer")
    public PlayerDTO addNewPlayer(@RequestBody PlayerDTO playerDTO) {
        return playerService.savePlayer(playerDTO);
    }

    @GetMapping("/getLimit{id}")
    public Long getTimer(@PathVariable int id) {
        PlayerDTO playerDTO = playerService.findPlayer(id);
        if (!playerDTO.getGames().isEmpty()) {
            GameDTO gameDTO = playerDTO.getGames().get(playerDTO.getGames().size() - 1);
            switch (gameDTO.getLimitation()) {
                case "time": {
                    long end = gameDTO
                            .getStartTime() + 300000;
//                    .getStartTime() + 10000;
                    return end - new Date().getTime() > 0 ? end - new Date().getTime() : (long) -1;
                }
                case "steps": {
                    long end = 10;
                    return end - gameDTO.getSteps().size() > 0 ? end - gameDTO.getSteps().size() : (long) -1;
                }
                case "without":
                    return (long) -2;
                default:
                    return (long) -3;
            }
        } else
            return (long) -100;
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
        int answer = 0;
        while (String.valueOf(answer).length() < 4) {
            answer = (int) (Math.random() * 10000);
        }
        return playerService.addGame(id, answer);
    }
}
