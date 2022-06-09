package com.tgc.bullsAndCows.controllers;


import com.tgc.bullsAndCows.model.Game;
import com.tgc.bullsAndCows.model.Player;
import com.tgc.bullsAndCows.model.Step;
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
    public Player addNewPlayer(@RequestBody Player player) {
        System.out.println(player);
        Player player1 = playerService.savePlayer(player);
        System.out.println(player1);
        return player1;
    }

    @GetMapping("/getPlayer{id}")
    public Player getPlayer(@PathVariable int id) {
        return playerService.findPlayer(id);
    }

    @GetMapping("/getAllPlayers")
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @PostMapping("/deletePlayer{id}")
    public Player deletePlayer(@PathVariable int id) {
        return playerService.deletePlayer(id);
    }

    @PostMapping("/addStepToGame/{playerId}")
    public Game addGameToPlayer(@PathVariable int playerId, @RequestBody Step step) {
        System.out.println(step);
        Game returnGame = playerService.addStep(playerId, step);
        System.out.println(returnGame);
        if (returnGame.getRightAnswer() == step.getAnswer()) {
            returnGame.setIsGuessed(1);
        }
        return returnGame;
    }

    @GetMapping("/startGame{id}")
    public Game startGame(@PathVariable int id) {
        int answer = 0;
        while (String.valueOf(answer).length() < 4) {
            answer = (int) (Math.random() * 10000);
        }
        return playerService.addGame(id, new Game(answer, 0));
    }
}
