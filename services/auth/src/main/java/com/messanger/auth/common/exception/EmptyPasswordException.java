package com.messanger.auth.common.exception;

import com.messanger.common.helper.exception.BaseException;

public class EmptyPasswordException extends BaseException {
    private static final long serialVersionUID = 5354487964968053333L;

    public EmptyPasswordException(String message, Throwable cause) {
        super(message, cause);
    }
}
