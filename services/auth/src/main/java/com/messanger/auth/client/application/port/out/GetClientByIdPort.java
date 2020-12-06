package com.messanger.auth.client.application.port.out;

import com.messanger.auth.client.domain.Client;

import java.util.Optional;

public interface GetClientByIdPort {
    Optional<Client> get(String id);
}
