package com.tgc.bullsandcows.mapper;

import com.tgc.bullsandcows.dto.StepDTO;
import com.tgc.bullsandcows.entity.Step;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface StepMapper extends Mappable<Step, StepDTO> {

    @Override
    @Mapping(target = "cows", source = "answerBullsAndCows.cows")
    @Mapping(target = "bulls", source = "answerBullsAndCows.bulls")
    Step toEntity(StepDTO dto);

    @Override
    @Mapping(target = "answerBullsAndCows.cows", source = "cows")
    @Mapping(target = "answerBullsAndCows.bulls", source = "bulls")
    StepDTO toDto(Step entity);
}
