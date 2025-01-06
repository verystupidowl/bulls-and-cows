package com.tgc.bullsandcows.controller;

import com.tgc.bullsandcows.dto.PlayerDTO;
import com.tgc.bullsandcows.dto.ProfileDTO;
import com.tgc.bullsandcows.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000/")
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/getAllUsers")
    public List<PlayerDTO> getAllUsers() {
        return adminService.getAllPlayers();
    }

    @GetMapping("/getUser/{id}")
    public ProfileDTO getUser(@PathVariable long id) {
        return adminService.getOnePlayer(id);
    }

    @PostMapping("/setBlocked/{id}")
    public ProfileDTO block(@PathVariable long id) {
        return adminService.setBlocked(id);
    }
}
