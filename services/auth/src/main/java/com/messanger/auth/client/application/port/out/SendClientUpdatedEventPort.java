package com.messanger.auth.client.application.port.out;

import com.messanger.auth.client.domain.Client;

public interface SendClientUpdatedEventPort {
    void send(Client client);
}
