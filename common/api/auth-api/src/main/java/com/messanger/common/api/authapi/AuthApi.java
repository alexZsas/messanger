package com.messanger.common.api.authapi;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public final class AuthApi {
    private AuthApi() {
        throw new UnsupportedOperationException();
    }

    @Data
    @Accessors(chain = true)
    public static class User {
        private String id;
        private String username;
        private String password;
        private boolean expired;
        private boolean locked;
        private boolean enabled;
        private Collection<Authority> authorities;
    }

    @Data
    @Accessors(chain = true)
    public static class Authority {
        private String id;
        private String name;
    }

    @Data
    @Accessors(chain = true)
    public static class Client {
        private String id;
        private String password;
        private Set<String> scope;
        private Set<String> autoApproveScopes;
        private Set<String> grantTypes;
        private Collection<Authority> authorities;
        private Integer accessTokenValiditySeconds;
        private Integer refreshTokenValiditySeconds;
        private Map<String, Object> additionalInfo;
        private boolean enabled;
    }
}
