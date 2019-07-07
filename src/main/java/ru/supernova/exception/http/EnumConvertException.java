package ru.supernova.exception.http;

public class EnumConvertException extends BadRequestException {
    public EnumConvertException() {
        super();
    }

    public EnumConvertException(String reason) {
        super(reason);
    }

    public EnumConvertException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
