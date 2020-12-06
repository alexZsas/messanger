package com.messanger.auth.client.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum GrantType {
    AUTHORIZATION_CODE("authorization_code"),
    IMPLICIT("implicit"),
    REFRESH_TOKEN("refresh_token"),
    CLIENT_CREDENTIALS("client_credentials"),
    PASSWORD("password");

    private final String grantTypeName;

    public String getGrantTypeName() {
        return grantTypeName;
    }

    public static boolean isGrantType(String str) {
        for (GrantType value : values()) {
            if (value.getGrantTypeName().equalsIgnoreCase(str)) {
                return true;
            }
        }

        return false;
    }

    public static GrantType of(String grantType) {
        for (GrantType value : values()) {
            if (value.getGrantTypeName().equalsIgnoreCase(grantType)) {
                return value;
            }
        }

        throw new IllegalArgumentException("Unknown grant type '" + grantType + "'");
    }
}
