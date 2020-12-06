package com.messanger.auth.client.application.port.out;

import com.messanger.auth.client.domain.Client;

import java.util.Collection;

public interface GetAllClientsPort {
    Collection<Client> getAll();
}
