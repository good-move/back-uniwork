package ru.supernova.exception.error;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class SimpleApiError implements ApiError {
    /**
     * Текстовое сообщение об ошибке.
     */
    private String message;

    public SimpleApiError() {
    }

    public SimpleApiError(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("message", message)
            .toString();
    }
}
