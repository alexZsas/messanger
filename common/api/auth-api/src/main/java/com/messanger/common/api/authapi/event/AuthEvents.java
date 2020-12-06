package com.messanger.common.api.authapi.event;

import com.messanger.common.api.authapi.AuthApi;
import com.messanger.common.api.event.Events;
import lombok.experimental.SuperBuilder;

public final class AuthEvents {
    private AuthEvents() {
        throw new UnsupportedOperationException();
    }

    @SuperBuilder
    public static class UserCreated extends Events.BaseEvent<AuthApi.User> {
    }

    @SuperBuilder
    public static class UserUpdated extends Events.BaseEvent<AuthApi.User> {
    }

    @SuperBuilder
    public static class UserDeleted extends Events.BaseEvent<String> {
    }

    @SuperBuilder
    public static class ClientCreated extends Events.BaseEvent<AuthApi.Client> {
    }

    @SuperBuilder
    public static class ClientUpdated extends Events.BaseEvent<AuthApi.Client> {
    }

    @SuperBuilder
    public static class ClientDeleted extends Events.BaseEvent<String> {
    }

    @SuperBuilder
    public static class AuthorityCreated extends Events.BaseEvent<AuthApi.Authority> {
    }

    @SuperBuilder
    public static class AuthorityUpdated extends Events.BaseEvent<AuthApi.Authority> {
    }

    @SuperBuilder
    public static class AuthorityDeleted extends Events.BaseEvent<String> {
    }
}
