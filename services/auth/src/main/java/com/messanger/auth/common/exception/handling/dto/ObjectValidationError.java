package com.messanger.auth.common.exception.handling.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ObjectValidationError extends ValidationError {
    private String objectName;
}
