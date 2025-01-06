package com.tgc.bullsandcows.mapper;

import com.tgc.bullsandcows.dto.GameDTO;
import com.tgc.bullsandcows.dto.GameStatisticDTO;
import com.tgc.bullsandcows.dto.GameWithStepsDTO;
import com.tgc.bullsandcows.entity.Game;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = StepMapper.class)
public interface GameMapper extends Mappable<Game, GameDTO> {

    @Override
    Game toEntity(GameDTO dto);

    @Override
    GameDTO toDto(Game entity);

    @Mapping(target = "isGuessed", source = "guessed")
    GameStatisticDTO toStatisticDTO(Game entity);

    @Mapping(target = "isGuessed", source = "guessed")
    GameWithStepsDTO toGameWithStepsDTO(Game entity);
}
