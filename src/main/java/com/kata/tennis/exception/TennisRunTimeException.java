package com.kata.tennis.exception;

public class TennisRunTimeException extends RuntimeException {

    public TennisRunTimeException(String message) {
        super(message);
    }

    public TennisRunTimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
