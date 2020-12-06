package com.messanger.auth.user.application.port;

import com.messanger.auth.common.exception.Exceptions;
import com.messanger.auth.user.application.port.in.DeleteUserUseCase;
import com.messanger.auth.user.application.port.out.DeleteUserPort;
import com.messanger.auth.user.application.port.out.ExistsUserByUsernamePort;
import com.messanger.auth.user.application.port.out.SendUserDeletedEventPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {
    private final ExistsUserByUsernamePort existsUserByUsernamePort;
    private final DeleteUserPort deleteUserPort;
    private final SendUserDeletedEventPort sendUserDeletedEventPort;

    @Override
    public void delete(String id) {
        if (!existsUserByUsernamePort.exists(id)) {
            Exceptions.ELEMENT_NOT_FOUND.create()
                    .message("User with id ''{}'' not found.", id)
                    .doThrow();
        }
        deleteUserPort.delete(id);
        sendUserDeletedEventPort.send(id);
    }
}
