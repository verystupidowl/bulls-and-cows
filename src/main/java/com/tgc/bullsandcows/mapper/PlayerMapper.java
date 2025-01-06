package com.tgc.bullsandcows.mapper;

import com.tgc.bullsandcows.dto.PlayerDTO;
import com.tgc.bullsandcows.dto.ProfileDTO;
import com.tgc.bullsandcows.entity.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PlayerMapper extends Mappable<Player, PlayerDTO> {

    @Override
    Player toEntity(PlayerDTO dto);

    @Override
    PlayerDTO toDto(Player entity);

    @Mapping(target = "isActive", source = "enabled")
    ProfileDTO toProfileDTO(Player player);
}
