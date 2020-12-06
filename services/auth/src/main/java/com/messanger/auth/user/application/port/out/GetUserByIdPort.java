package com.messanger.auth.user.application.port.out;

import com.messanger.auth.user.domain.User;

import java.util.Optional;

public interface GetUserByIdPort {
    Optional<User> get(String id);
}
