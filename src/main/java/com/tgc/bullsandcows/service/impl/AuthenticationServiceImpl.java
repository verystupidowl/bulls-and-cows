package com.tgc.bullsandcows.service.impl;

import com.tgc.bullsandcows.dto.AuthenticationRequestDTO;
import com.tgc.bullsandcows.dto.AuthenticationResponseDTO;
import com.tgc.bullsandcows.dto.RegisterDTO;
import com.tgc.bullsandcows.entity.Gender;
import com.tgc.bullsandcows.entity.Player;
import com.tgc.bullsandcows.exception.PasswordsNotEqualsException;
import com.tgc.bullsandcows.exception.PlayerAlreadyRegisteredException;
import com.tgc.bullsandcows.exception.PlayerNotFoundException;
import com.tgc.bullsandcows.repository.PlayerRepository;
import com.tgc.bullsandcows.service.AuthenticationService;
import com.tgc.bullsandcows.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.tgc.bullsandcows.entity.Role.ROLE_USER;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final PlayerRepository playerRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationResponseDTO login(AuthenticationRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        Player player = playerRepository
                .findByEmail(request.email())
                .orElseThrow(() -> new PlayerNotFoundException(request.email()));

        String token = jwtService.generateToken(player);
        return new AuthenticationResponseDTO(token);
    }

    @Override
    public AuthenticationResponseDTO register(RegisterDTO dto) {
        if (!dto.password().equals(dto.passwordConfirmation())) {
            throw new PasswordsNotEqualsException();
        }
        playerRepository.findByEmail(dto.email()).ifPresent(p -> {
            throw new PlayerAlreadyRegisteredException(p.getEmail());
        });
        Player player = Player.builder()
                .email(dto.email())
                .name(dto.name())
                .role(ROLE_USER)
                .isActive(true)
                .gender(Gender.valueOf(dto.gender()))
                .password(passwordEncoder.encode(dto.password()))
                .build();

        playerRepository.save(player);
        String token = jwtService.generateToken(player);
        return new AuthenticationResponseDTO(token);
    }
}
