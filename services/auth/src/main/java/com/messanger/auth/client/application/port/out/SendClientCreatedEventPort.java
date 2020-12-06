package com.messanger.auth.client.application.port.out;

import com.messanger.auth.client.domain.Client;

public interface SendClientCreatedEventPort {
    void send(Client client);
}
