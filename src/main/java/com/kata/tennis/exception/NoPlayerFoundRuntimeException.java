package com.kata.tennis.exception;

public class NoPlayerFoundRuntimeException extends TennisRunTimeException {

    public NoPlayerFoundRuntimeException(String message) {
        super(message);
    }

    public NoPlayerFoundRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
