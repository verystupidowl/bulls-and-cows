package com.tgc.bullsAndCows.controllers;


import com.tgc.bullsAndCows.model.Game;
import com.tgc.bullsAndCows.model.Player;
import com.tgc.bullsAndCows.model.Step;
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
    public Player addNewPlayer(@RequestBody Player player) {
        return playerService.savePlayer(player);
    }

    @GetMapping("/getTimer{id}")
    public Long getTimer(@PathVariable int id) {
        Player player = playerService.findPlayer(id);
        if (!player.getGames().isEmpty()) {
            Game game = player.getGames().get(player.getGames().size() - 1);
            switch (game.getLimitation()) {
                case "time": {
                    long end = game
                            .getStartTime() + 300000;
//                    .getStartTime() + 10000;
                    return end - new Date().getTime() > 0 ? end - new Date().getTime() : (long) -1;
                }
                case "steps": {
                    long end = 10;
                    return end - game.getSteps().size() > 0 ? end - game.getSteps().size() : (long) -1;
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
        Game returnGame = playerService.addStep(playerId, step);
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
        return playerService.addGame(id, new Game(answer, 0, "time", new Date().getTime()));
    }
}
