package com.messanger.chat.common.exception;

import com.messanger.common.helper.exception.BaseException;

public class ChatNotFoundException extends BaseException {
    private static final long serialVersionUID = -1007683432026163935L;

    public ChatNotFoundException(String message) {
        super(message);
    }
}
