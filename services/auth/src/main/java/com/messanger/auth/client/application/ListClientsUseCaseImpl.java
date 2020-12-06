package com.messanger.auth.client.application;

import com.messanger.auth.client.application.port.in.ListClientsUseCase;
import com.messanger.auth.client.application.port.out.GetAllClientsByIdsPort;
import com.messanger.auth.client.domain.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class ListClientsUseCaseImpl implements ListClientsUseCase {
    private final GetAllClientsByIdsPort getAllClientsByIdsPort;

    @Override
    public Collection<Client> list(Collection<String> ids) {
        return getAllClientsByIdsPort.getByIds(ids);
    }
}
