package com.tgc.bullsandcows.controller;

import com.tgc.bullsandcows.dto.PlayerDTO;
import com.tgc.bullsandcows.dto.ProfileDTO;
import com.tgc.bullsandcows.entity.Player;
import com.tgc.bullsandcows.service.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/player")
@Slf4j
@CrossOrigin("http://localhost:3000/")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;

    @GetMapping("/getPlayer")
    public PlayerDTO getPlayer(UsernamePasswordAuthenticationToken token) {
        Player player = (Player) token.getPrincipal();
        return playerService.findPlayerById(player.getId());
    }

    @GetMapping("/profile")
    public ProfileDTO getProfile(UsernamePasswordAuthenticationToken token) {
        Player player = (Player) token.getPrincipal();
        return playerService.getProfile(player.getId());
    }

    @GetMapping("/getAllPlayers")
    public List<PlayerDTO> getAllPlayers() {
        return playerService.getAllPlayers();
    }

}
