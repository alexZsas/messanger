package com.messanger.auth.common.exception.handling;

import com.messanger.auth.common.exception.handling.dto.ValidationError;
import com.messanger.auth.common.exception.handling.dto.ValidationErrors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrors> handle(MethodArgumentNotValidException ex) {
        List<ValidationError> errors = ex.getBindingResult().getAllErrors().stream()
                .map(ValidationError::from)
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(new ValidationErrors(errors));
    }
}
