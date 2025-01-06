package com.tgc.bullsandcows.controller;

import com.tgc.bullsandcows.dto.AuthenticationRequestDTO;
import com.tgc.bullsandcows.dto.AuthenticationResponseDTO;
import com.tgc.bullsandcows.dto.PlayerErrorResponseDTO;
import com.tgc.bullsandcows.dto.RegisterDTO;
import com.tgc.bullsandcows.exception.PasswordsNotEqualsException;
import com.tgc.bullsandcows.exception.PlayerAlreadyRegisteredException;
import com.tgc.bullsandcows.exception.PlayerNotFoundException;
import com.tgc.bullsandcows.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth")
@CrossOrigin("http://localhost:3000/")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public AuthenticationResponseDTO login(@Valid @RequestBody AuthenticationRequestDTO request) {
        return authenticationService.login(request);
    }

    @PostMapping("/register")
    public AuthenticationResponseDTO register(@Valid @RequestBody RegisterDTO dto) {
        return authenticationService.register(dto);
    }

    @ExceptionHandler({
            PlayerNotFoundException.class,
            PlayerAlreadyRegisteredException.class
    })
    public ResponseEntity<PlayerErrorResponseDTO> exceptionHandler(Exception e) {
        var playerErrorResponse = new PlayerErrorResponseDTO(
                e.getMessage(),
                System.currentTimeMillis(),
                null
        );
        return new ResponseEntity<>(playerErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordsNotEqualsException.class)
    public ResponseEntity<List<PlayerErrorResponseDTO>> exceptionHandler(PasswordsNotEqualsException e) {
        var playerErrorResponse = new PlayerErrorResponseDTO(
                e.getMessage(),
                System.currentTimeMillis(),
                "passwordConfirmation"
        );
        return new ResponseEntity<>(List.of(playerErrorResponse), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<PlayerErrorResponseDTO>> exceptionHandler(MethodArgumentNotValidException e) {
        List<PlayerErrorResponseDTO> playerErrorResponse = getDTOs(e);
        return new ResponseEntity<>(playerErrorResponse, HttpStatus.BAD_REQUEST);
    }

    private List<PlayerErrorResponseDTO> getDTOs(MethodArgumentNotValidException e) {
        return e.getFieldErrors().stream()
                .map(err -> new PlayerErrorResponseDTO(
                        err.getDefaultMessage(),
                        System.currentTimeMillis(),
                        err.getField()
                ))
                .toList();
    }
}
