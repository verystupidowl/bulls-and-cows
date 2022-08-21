package com.tgc.bullsAndCows.controllers;

import com.tgc.bullsAndCows.Utils.PlayerErrorResponse;
import com.tgc.bullsAndCows.Utils.PlayerNotCreatedException;
import com.tgc.bullsAndCows.dto.PlayerDTO;
import com.tgc.bullsAndCows.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/login")
@CrossOrigin("http://localhost:3000/")
public class LoginController {

    private final PlayerService playerService;

    @Autowired
    public LoginController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/addPlayer")
    public PlayerDTO addNewPlayer(@RequestBody @Valid PlayerDTO playerDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMsg = errorMsgBuilder(bindingResult.getFieldErrors());
            throw new PlayerNotCreatedException(errorMsg);
        }
        return playerService.savePlayer(playerDTO);
    }


    private String errorMsgBuilder(List<FieldError> errors) {
        StringBuilder errorMsg = new StringBuilder();
        for (FieldError error : errors) {
            errorMsg
                    .append(error.getField())
                    .append(" - ")
                    .append(error.getDefaultMessage())
                    .append(";");
        }
        return errorMsg.toString();
    }

    @ExceptionHandler
    private ResponseEntity<PlayerErrorResponse> exceptionHAndler(PlayerNotCreatedException e) {
        var playerErrorResponse = new PlayerErrorResponse(
                e.getMessage(), System.currentTimeMillis()
        );
        return new ResponseEntity<>(playerErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
