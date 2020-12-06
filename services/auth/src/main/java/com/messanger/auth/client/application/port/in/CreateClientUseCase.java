package com.messanger.auth.client.application.port.in;

import com.messanger.auth.client.domain.command.CreateClientCommand;

public interface CreateClientUseCase {
    void create(CreateClientCommand command);
}
