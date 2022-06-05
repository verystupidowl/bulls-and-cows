package com.tgc.bullsAndCows.controllers;


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
        Player returnPlayer = playerService.savePlayer(player);
        System.out.println(returnPlayer);
        return returnPlayer;
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

    @PostMapping("/addStepToPlayer{playerId}")
    public String addStepToPlayer(@PathVariable int playerId, @RequestBody Step step) {
        playerService.addStep(playerId, step);
        return "new step for player has been added";
    }
}
