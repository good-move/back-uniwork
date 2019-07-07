package ru.supernova.controller;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import ru.supernova.exception.error.ApiError;
import ru.supernova.exception.error.DuplicateError;
import ru.supernova.exception.error.NotFoundError;
import ru.supernova.exception.error.SimpleApiError;
import ru.supernova.exception.http.ResourceDuplicateException;
import ru.supernova.exception.http.ResourceNotFoundException;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    private static final String BAD_REQUEST_LOG_MSG = "BAD_REQUEST occurred during request processing";
    private static final String NOT_FOUND_LOG_MSG = "NOT_FOUND occurred during request processing";
    private static final String CONFLICT_LOG_MSG = "CONFLICT occurred during request processing";
    private static final String INTERNAL_SERVER_ERROR_LOG_MSG =
        "INTERNAL_SERVER_ERROR occurred during request processing";

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ApiError handle(final MissingServletRequestParameterException e) {
        log.warn(BAD_REQUEST_LOG_MSG, e);
        return new SimpleApiError(e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ApiError handle(final ConstraintViolationException e) {
        log.warn(BAD_REQUEST_LOG_MSG, e);
        return new SimpleApiError(e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ApiError handleNoResourceFoundException(ResourceNotFoundException e) {
        log.warn(NOT_FOUND_LOG_MSG, e);
        return new NotFoundError(e.getType(), e.getIdentifiers(), e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(value = ResourceDuplicateException.class)
    public ApiError handleResourceFoundDuplicateException(ResourceDuplicateException e) {
        log.warn(NOT_FOUND_LOG_MSG, e);
        return new DuplicateError(e.getType(), e.getUrls(), e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ApiError handle(final MethodArgumentTypeMismatchException e) {
        log.warn(BAD_REQUEST_LOG_MSG, e);
        return new SimpleApiError(Optional.ofNullable(e.getRootCause())
            .map(Throwable::getMessage)
            .orElseGet(e::getMessage));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiError handle(final HttpMessageNotReadableException e) {
        log.warn(BAD_REQUEST_LOG_MSG, e);
        return new SimpleApiError(e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MultipartException.class)
    public ApiError handle(final MultipartException e) {
        log.warn(BAD_REQUEST_LOG_MSG, e);
        return new SimpleApiError(e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ApiError handle(final BindException e) {
        log.warn(BAD_REQUEST_LOG_MSG, e);
        return new SimpleApiError(buildErrorMessageFrom(e.getBindingResult()));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiError handle(final MethodArgumentNotValidException e) {
        log.warn(BAD_REQUEST_LOG_MSG, e);
        return new SimpleApiError(buildErrorMessageFrom(e.getBindingResult()));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(HttpStatusCodeException.class)
    public ApiError handle(HttpStatusCodeException e) {
        log.warn(buildRestClientResponseLog(e), e);
        return new SimpleApiError(e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ApiError defaultExceptionHandler(final Throwable e) {
        log.warn(INTERNAL_SERVER_ERROR_LOG_MSG, e);
        return new SimpleApiError(e.getMessage());
    }

    private String buildRestClientResponseLog(HttpStatusCodeException e) {
        return String.format("Invalid client response\nStatus: %s\nHeaders: %s\nBody: %s\n",
            e.getStatusCode(), e.getResponseHeaders(), e.getResponseBodyAsString());
    }

    private String buildErrorMessageFrom(BindingResult bindingResult) {
        return bindingResult.getFieldErrors()
            .stream()
            .map(this::formatFieldError)
            .sorted()
            .collect(Collectors.joining("\n", "Following validation errors occurred:\n", ""));
    }

    private String formatFieldError(final FieldError fieldError) {
        return String.format(
            "Field: '%s', message: '%s'",
            fieldError.getField(),
            Optional.ofNullable(fieldError.getDefaultMessage()).orElse("Not provided").split(";")[0]
        );
    }

}
