package com.company;

/**
 * This exception will be thrown if the validation is failed
 */
public class MismatchException extends Exception {

    public MismatchException() {
    }

    public MismatchException(String message) {
        super(message);
    }

    public MismatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public MismatchException(Throwable cause) {
        super(cause);
    }

    public MismatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
