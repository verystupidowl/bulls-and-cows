package com.tgc.bullsAndCows.controllers;


import com.tgc.bullsAndCows.model.Player;
import com.tgc.bullsAndCows.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public String savePlayer(@RequestBody Player player) {
        playerService.savePlayer(player);
        System.out.println(player);
        return "new player has been added";
    }

    @GetMapping("/getPlayer{id}")
    public Player getPlayer(@PathVariable int id) {
        return playerService.findPlayer(id);
    }
}
