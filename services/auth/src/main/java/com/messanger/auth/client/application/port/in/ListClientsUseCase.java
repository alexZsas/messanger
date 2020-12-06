package com.messanger.auth.client.application.port.in;

import com.messanger.auth.client.domain.Client;

import java.util.Collection;

public interface ListClientsUseCase {
    Collection<Client> list(Collection<String> ids);
}
