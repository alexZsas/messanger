package com.messanger.auth.common.exception;

import com.messanger.common.helper.exception.BaseException;

public class ElementNotFoundException extends BaseException {
    private static final long serialVersionUID = 4966271776275864860L;

    public ElementNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
