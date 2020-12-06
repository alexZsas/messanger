package com.messanger.auth.user.application.port.in;

import com.messanger.auth.user.domain.User;

import java.util.Optional;

public interface GetUserByIdUseCase {
    Optional<User> get(String id);
}
