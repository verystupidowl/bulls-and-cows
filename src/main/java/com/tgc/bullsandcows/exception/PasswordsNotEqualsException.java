package com.tgc.bullsandcows.exception;

public class PasswordsNotEqualsException extends RuntimeException {

    public PasswordsNotEqualsException() {
        super("Passwords do not match");
    }
}
