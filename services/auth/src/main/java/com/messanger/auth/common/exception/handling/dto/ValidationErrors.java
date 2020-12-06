package com.messanger.auth.common.exception.handling.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationErrors {
    private Collection<ValidationError> errors = new ArrayList<>();
}
