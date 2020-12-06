package com.messanger.auth.common.exception;

import org.springframework.security.oauth2.provider.ClientRegistrationException;

@SuppressWarnings("deprecation")
public class ClientDisabledException extends ClientRegistrationException {
    private static final long serialVersionUID = -6575094564779060525L;

    public ClientDisabledException(String msg) {
        super(msg);
    }
}
