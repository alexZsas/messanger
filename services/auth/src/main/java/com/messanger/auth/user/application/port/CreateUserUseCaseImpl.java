package com.messanger.auth.user.application.port;

import com.messanger.auth.common.exception.Exceptions;
import com.messanger.auth.user.application.port.in.CreateUserUseCase;
import com.messanger.auth.user.application.port.out.CreateUserPort;
import com.messanger.auth.user.application.port.out.ExistsUserByUsernamePort;
import com.messanger.auth.user.application.port.out.SendUserCreatedEventPort;
import com.messanger.auth.user.domain.User;
import com.messanger.auth.user.domain.command.CreateUserCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final ExistsUserByUsernamePort existsUserByUsernamePort;
    private final CreateUserPort createUserPort;
    private final SendUserCreatedEventPort sendUserCreatedEventPort;

    @Override
    public void create(CreateUserCommand command) {
        if (existsUserByUsernamePort.exists(command.getUsername())) {
            Exceptions.ELEMENT_ALREADY_EXISTS.create()
                    .message("User with username '{}' already exists.", command.getUsername())
                    .doThrow();
        }

        User user = new User(command);
        createUserPort.create(user);
        sendUserCreatedEventPort.send(user);
    }
}
