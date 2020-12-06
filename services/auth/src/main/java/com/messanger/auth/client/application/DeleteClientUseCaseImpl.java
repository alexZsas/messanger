package com.messanger.auth.client.application;

import com.messanger.auth.client.application.port.in.DeleteClientUseCase;
import com.messanger.auth.client.application.port.out.DeleteClientPort;
import com.messanger.auth.client.application.port.out.ExistsClientByIdPort;
import com.messanger.auth.client.application.port.out.SendClientDeletedEventPort;
import com.messanger.auth.common.exception.Exceptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteClientUseCaseImpl implements DeleteClientUseCase {
    private final ExistsClientByIdPort existsClientByIdPort;
    private final DeleteClientPort deleteClientPort;
    private final SendClientDeletedEventPort sendClientDeletedEventPort;

    @Override
    public void delete(String id) {
        if (!existsClientByIdPort.exists(id)) {
            Exceptions.ELEMENT_NOT_FOUND.create()
                    .message("Client with id ''{}'' not found.", id)
                    .doThrow();
        }
        deleteClientPort.delete(id);
        sendClientDeletedEventPort.send(id);
    }
}
