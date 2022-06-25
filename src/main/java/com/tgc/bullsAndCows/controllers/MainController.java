package com.tgc.bullsAndCows.controllers;


import com.tgc.bullsAndCows.Utils.GameUtils;
import com.tgc.bullsAndCows.dto.GameDTO;
import com.tgc.bullsAndCows.dto.PlayerDTO;
import com.tgc.bullsAndCows.dto.StepDTO;
import com.tgc.bullsAndCows.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
