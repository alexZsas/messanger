package com.messanger.auth.client.application.port.in;

import com.messanger.auth.client.domain.Client;

import java.util.Optional;

public interface GetClientByIdUseCase {
    Optional<Client> get(String id);
}
