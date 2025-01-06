package com.tgc.bullsandcows.service.impl;

import com.tgc.bullsandcows.dto.LimitDTO;
import com.tgc.bullsandcows.entity.Limitation;
import com.tgc.bullsandcows.exception.LimitException;
import com.tgc.bullsandcows.repository.GameRepository;
import com.tgc.bullsandcows.service.LimitService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static com.tgc.bullsandcows.entity.Limitation.STEPS;
import static com.tgc.bullsandcows.entity.Limitation.TIME;
import static com.tgc.bullsandcows.entity.Limitation.WITHOUT;

@Service
@RequiredArgsConstructor
public class LimitServiceImpl implements LimitService {
    @Value("${limitation}")
    private String limitation;

    private final GameRepository gameRepository;

    @Override
    public Limitation getLimitation() {
        return switch (limitation) {
            case "steps" -> STEPS;
            case "time" -> TIME;
            case "without" -> WITHOUT;
            default ->
                    throw new LimitException("There is no limit defined for " + limitation + ". Please check config file");
        };
    }

    @Override
    public Boolean isEnded(Long gameId) {
        return getLimit(gameId).getIsEnded();
    }

    @Override
    public LimitDTO getLimit(Long gameId) {
        return gameRepository.findById(gameId)
                .map(game -> {
                    Limitation gameLimitation = game.getLimitation();
                    return switch (gameLimitation) {
                        case TIME -> {
                            LocalDateTime end = game.getStartTime().plusSeconds(60);
                            yield new LimitDTO(
                                    TIME,
                                    end.toEpochSecond(ZoneOffset.UTC) - LocalDateTime.now().toEpochSecond(ZoneOffset.UTC),
                                    end.isBefore(LocalDateTime.now()) || game.isGuessed()
                            );
                        }
                        case STEPS -> {
                            long end = 10;
                            yield new LimitDTO(
                                    STEPS,
                                    end - game.getSteps().size() > 0 ? end - game.getSteps().size() : (long) -1,
                                    game.getSteps().size() >= 10 || game.isGuessed()
                            );
                        }
                        case WITHOUT -> LimitDTO.builder().code(WITHOUT).build();
                    };
                })
                .orElseGet(LimitDTO::new);
    }
}
