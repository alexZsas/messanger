package com.messanger.auth.common.exception;

import com.messanger.common.helper.exception.BaseException;

public class IllegalPasswordException extends BaseException {
    private static final long serialVersionUID = 7044802895927750963L;

    public IllegalPasswordException(String message, Throwable cause) {
        super(message, cause);
    }
}
