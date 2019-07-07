package ru.supernova.exception.http;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.supernova.exception.UniworkException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends UniworkException {
    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
