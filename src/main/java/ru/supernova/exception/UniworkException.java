package ru.supernova.exception;

public class UniworkException extends RuntimeException {
    public UniworkException() {
    }

    public UniworkException(final String message) {
        super(message);
    }

    public UniworkException(final String message,
                            final Throwable cause) {
        super(message, cause);
    }

    public UniworkException(final Throwable cause) {
        super(cause);
    }

    public UniworkException(final String message,
                            final Throwable cause,
                            final boolean enableSuppression,
                            final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
