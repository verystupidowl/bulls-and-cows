package com.tgc.bullsandcows.dto;

import lombok.Builder;

@Builder
public record PlayerErrorResponseDTO(
        String message,
        long timestamp,
        String field
) {
}
