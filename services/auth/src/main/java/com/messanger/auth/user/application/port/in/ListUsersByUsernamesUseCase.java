package com.messanger.auth.user.application.port.in;

import com.messanger.auth.user.domain.User;

import java.util.Collection;

public interface ListUsersByUsernamesUseCase {
    Collection<User> list(Collection<String> usernames);
}
