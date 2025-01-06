package com.tgc.bullsandcows.exception;

public class PlayerNotFoundException extends RuntimeException {

    public PlayerNotFoundException(String msg) {
        super(msg);
    }
}
