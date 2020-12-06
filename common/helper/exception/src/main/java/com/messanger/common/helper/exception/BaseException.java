package com.messanger.common.helper.exception;

@SuppressWarnings("serial")
public abstract class BaseException extends RuntimeException {
    public BaseException(String message) {
        super(message);
    }
    
    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
