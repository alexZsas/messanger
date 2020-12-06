package com.messanger.auth.client.application;

import com.messanger.auth.client.application.port.in.GetClientByIdUseCase;
import com.messanger.auth.client.application.port.out.GetClientByIdPort;
import com.messanger.auth.client.domain.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetClientByIdUseCaseImpl implements GetClientByIdUseCase {
    private final GetClientByIdPort getClientByIdPort;

    @Override
    public Optional<Client> get(String id) {
        return getClientByIdPort.get(id);
    }
}
