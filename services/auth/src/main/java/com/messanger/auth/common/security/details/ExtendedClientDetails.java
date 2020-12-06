package com.messanger.auth.common.security.details;

import org.springframework.security.oauth2.provider.ClientDetails;

@SuppressWarnings("deprecation")
public interface ExtendedClientDetails extends ClientDetails {
    boolean isEnabled();
}
