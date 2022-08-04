package com.tgc.bullsAndCows.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayerErrorResponse {

    private String message;
    private long timestamp;
}
