package com.messanger.auth.user.application.port.out;

import com.messanger.auth.user.domain.User;

import java.util.Optional;

public interface GetUserByUsernamePort {
    Optional<User> get(String username);
}
