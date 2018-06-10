package com.kata.tennis.exception;

public class MatchAlreadyOverRuntimeException extends TennisRunTimeException {

    public MatchAlreadyOverRuntimeException(String message) {
        super(message);
    }

    public MatchAlreadyOverRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
