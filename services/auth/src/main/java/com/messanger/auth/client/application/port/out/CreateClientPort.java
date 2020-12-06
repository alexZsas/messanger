package com.messanger.auth.client.application.port.out;

import com.messanger.auth.client.domain.Client;

public interface CreateClientPort {
    void create(Client client);
}
