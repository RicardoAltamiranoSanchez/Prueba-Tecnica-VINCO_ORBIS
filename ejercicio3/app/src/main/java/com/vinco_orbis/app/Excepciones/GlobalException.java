package com.vinco_orbis.app.Excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalException  {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), Collections.singletonList(ex.getLocalizedMessage()));
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), Collections.singletonList(ex.getLocalizedMessage()));
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                                .collect(Collectors.toList());
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Validación fallida", errors);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Object> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        ApiError apiError = new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Tipo de medio no soportado", Collections.singletonList(ex.getLocalizedMessage()));
        return new ResponseEntity<>(apiError, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        ApiError apiError = new ApiError(HttpStatus.METHOD_NOT_ALLOWED, "Método HTTP no soportado", Collections.singletonList(ex.getLocalizedMessage()));
        return new ResponseEntity<>(apiError, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllUncaughtException(Exception ex) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor", Collections.singletonList("Ocurrió un error inesperado. Por favor intente más tarde."));
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ConflictException.class)
public ResponseEntity<Object> handleConflictException(ConflictException ex) {
    ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getMessage(), Collections.singletonList(ex.getLocalizedMessage()));
    return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
}

@ExceptionHandler(InternalServerErrorException.class)
public ResponseEntity<Object> handleInternalServerErrorException(InternalServerErrorException ex) {
    ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), Collections.singletonList(ex.getLocalizedMessage()));
    return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
}
@ExceptionHandler(BusinessException.class)
public ResponseEntity<Object> handleBusinessException(BusinessException ex) {
    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), Collections.singletonList(ex.getLocalizedMessage()));
    return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
}

}