package com.tgc.bullsandcows.service;

import com.tgc.bullsandcows.dto.AuthenticationRequestDTO;
import com.tgc.bullsandcows.dto.AuthenticationResponseDTO;
import com.tgc.bullsandcows.dto.RegisterDTO;

public interface AuthenticationService {

    AuthenticationResponseDTO login(AuthenticationRequestDTO authenticationRequest);

    AuthenticationResponseDTO register(RegisterDTO dto);
}
