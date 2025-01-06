package com.tgc.bullsandcows.dto;

import com.tgc.bullsandcows.entity.Limitation;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record GameStatisticDTO(
        Long id,
        String rightAnswer,
        Boolean isGuessed,
        Limitation limitation,
        LocalDateTime startTime,
        List<StepDTO> steps
) {
}
