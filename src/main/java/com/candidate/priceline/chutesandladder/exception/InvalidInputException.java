package com.candidate.priceline.chutesandladder.exception;

public class InvalidInputException extends Exception {
    private String errorMessage;

    public InvalidInputException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public InvalidInputException() {
        super();
    }

    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidInputException(Throwable cause) {
        super(cause);
    }

    protected InvalidInputException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
