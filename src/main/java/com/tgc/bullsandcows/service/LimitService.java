package com.tgc.bullsandcows.service;

import com.tgc.bullsandcows.dto.LimitDTO;
import com.tgc.bullsandcows.entity.Limitation;

public interface LimitService {

    Limitation getLimitation();

    LimitDTO getLimit(Long gameId);

    Boolean isEnded(Long gameId);
}
