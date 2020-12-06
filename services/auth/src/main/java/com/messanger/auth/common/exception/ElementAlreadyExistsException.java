package com.messanger.auth.common.exception;

import com.messanger.common.helper.exception.BaseException;

public class ElementAlreadyExistsException extends BaseException {
    private static final long serialVersionUID = -2943389384543773502L;

    public ElementAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
