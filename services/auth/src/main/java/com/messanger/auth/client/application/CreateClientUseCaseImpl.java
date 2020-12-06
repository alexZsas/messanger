package com.messanger.auth.client.application;

import com.messanger.auth.client.application.port.in.CreateClientUseCase;
import com.messanger.auth.client.application.port.out.CreateClientPort;
import com.messanger.auth.client.application.port.out.ExistsClientByIdPort;
import com.messanger.auth.client.application.port.out.SendClientCreatedEventPort;
import com.messanger.auth.client.domain.Client;
import com.messanger.auth.client.domain.command.CreateClientCommand;
import com.messanger.auth.common.exception.Exceptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateClientUseCaseImpl implements CreateClientUseCase {
    private final ExistsClientByIdPort existsClientByIdPort;
    private final CreateClientPort createClientPort;
    private final SendClientCreatedEventPort sendClientCreatedEventPort;

    @Override
    public void create(CreateClientCommand command) {
        if (command.getClientId() != null && existsClientByIdPort.exists(command.getClientId())) {
            Exceptions.ELEMENT_ALREADY_EXISTS.create()
                    .message("Client with id ''{}'' already exists.", command.getClientId())
                    .doThrow();
        }

        Client client = new Client(command);
        createClientPort.create(client);
        sendClientCreatedEventPort.send(client);
    }
}
