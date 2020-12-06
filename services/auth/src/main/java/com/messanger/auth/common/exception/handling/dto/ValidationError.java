package com.messanger.auth.common.exception.handling.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@Data
@Accessors(chain = true)
public abstract class ValidationError {
    private String message;

    public static ValidationError from(ObjectError error) {
        if (error instanceof FieldError) {
            return new FieldValidationError()
                    .setField(((FieldError) error).getField())
                    .setMessage(error.getDefaultMessage());
        } else {
            return new ObjectValidationError()
                    .setObjectName(error.getObjectName())
                    .setMessage(error.getDefaultMessage());
        }
    }
}
