package com.tgc.bullsandcows.controller;

import com.tgc.bullsandcows.dto.PlayerErrorResponseDTO;
import com.tgc.bullsandcows.dto.StepDTO;
import com.tgc.bullsandcows.exception.GameEndedException;
import com.tgc.bullsandcows.service.StepService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.LOCKED;

@RestController
@RequestMapping("/step")
@CrossOrigin("http://localhost:3000/")
@RequiredArgsConstructor
public class StepController {
    private final StepService stepService;

    @GetMapping("/getSteps{gameId}")
    public List<StepDTO> getStepsByGameId(@PathVariable Long gameId) {
        return stepService.getStepsByGameId(gameId);
    }


    @PostMapping("/addStepToGame/{gameId}")
    public void addGameToGame(@PathVariable Long gameId, @RequestBody StepDTO stepDTO) {
        stepService.addStep(gameId, stepDTO);
    }

    @ExceptionHandler(GameEndedException.class)
    public ResponseEntity<PlayerErrorResponseDTO> handleGameEndedException() {
        return ResponseEntity.status(LOCKED).body(new PlayerErrorResponseDTO(
                "Game is already ended",
                System.currentTimeMillis(),
                null
        ));
    }
}
