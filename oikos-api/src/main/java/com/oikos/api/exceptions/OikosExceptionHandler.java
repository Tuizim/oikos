package com.oikos.api.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class OikosExceptionHandler {

    @ExceptionHandler(OikosException.class)
    public ResponseEntity<OikosErrorResponse>  handleOikosException(OikosException ex) {
        return ResponseEntity
                .badRequest()
                .body(OikosErrorResponse.builder()
                        .codigo(ex.getCodigo())
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<OikosErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {

        FieldError fieldError = ex.getBindingResult().getFieldErrors().get(0);

        String codigoErro = fieldError.getDefaultMessage();

        return ResponseEntity
                .badRequest()
                .body(OikosErrorResponse.builder()
                        .codigo(codigoErro)
                        .timestamp(LocalDateTime.now())
                        .build());
    }
}
