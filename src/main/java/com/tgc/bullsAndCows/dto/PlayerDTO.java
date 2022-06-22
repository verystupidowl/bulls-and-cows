package com.tgc.bullsAndCows.dto;

import lombok.Data;

import java.util.List;

@Data
public class PlayerDTO {

    Integer id;
    String name;
    List<GameDTO> games;
}
