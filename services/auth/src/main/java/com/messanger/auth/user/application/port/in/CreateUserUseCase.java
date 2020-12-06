package com.messanger.auth.user.application.port.in;

import com.messanger.auth.user.domain.command.CreateUserCommand;

public interface CreateUserUseCase {
    void create(CreateUserCommand command);
}
