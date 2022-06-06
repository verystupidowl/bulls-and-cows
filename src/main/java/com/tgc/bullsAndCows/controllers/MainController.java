package com.tgc.bullsAndCows.controllers;


import com.tgc.bullsAndCows.model.Game;
import com.tgc.bullsAndCows.model.Player;
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
        return playerService.savePlayer(player);
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
    public Game addGameToPlayer(@PathVariable int playerId, @RequestBody Game game) {
        System.out.println(game);
        Game returnGame = playerService.addStep(playerId, game);
        if (returnGame.getAnswer() == game.getAnswer()) {
            returnGame.setIsGuessed(1);
        }
        return returnGame;
    }

    @GetMapping("/startGame{id}")
    public Game startGame(@PathVariable int id) {
        Game game = playerService.addGame(id, new Game(0, 0, (int) (Math.random() * 10000), 0));
        System.out.println(game);
        return game;
    }
}
