package com.messanger.auth.common.exception;

import com.messanger.common.helper.exception.BaseException;

public class ConfirmPasswordNotMatchedException extends BaseException {
    private static final long serialVersionUID = -1866951002333739021L;

    public ConfirmPasswordNotMatchedException(String message, Throwable cause) {
        super(message, cause);
    }
}
