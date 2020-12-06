package com.messanger.auth.common.exception;

import com.messanger.common.helper.exception.BaseException;

public class AuthorityAlreadyExistsException extends BaseException {
    private static final long serialVersionUID = 2181768544287129654L;

    public AuthorityAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
