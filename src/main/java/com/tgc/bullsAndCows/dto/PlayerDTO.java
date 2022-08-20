package com.tgc.bullsAndCows.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class PlayerDTO {

    private int id;
    @NotEmpty(message = "Name should not be empty!")
    @Size(min = 2, max = 10, message = "Name size should be between 2 and 10 characters!")
    private String name;
    private List<GameDTO> games;
}
