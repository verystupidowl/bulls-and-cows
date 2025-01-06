package com.tgc.bullsandcows.exception;

public class PlayerAlreadyRegisteredException extends RuntimeException {
    private static final String MESSAGE_TEMPLATE = "Player with email %s already registered";

    public PlayerAlreadyRegisteredException(String message) {
        super(String.format(MESSAGE_TEMPLATE, message));
    }
}
