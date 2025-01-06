package com.tgc.bullsandcows.service;

import com.tgc.bullsandcows.entity.Player;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String generateToken(Player player);

    boolean isValidToken(String token, UserDetails userDetails);

    String extractUsername(String jwt);
}
