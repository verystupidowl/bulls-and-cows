package com.tgc.bullsandcows.service;

import com.tgc.bullsandcows.dto.StepDTO;

import java.util.List;

public interface StepService {

    void addStep(Long id, StepDTO stepDTO);

    List<StepDTO> getStepsByGameId(Long gameId);
}
