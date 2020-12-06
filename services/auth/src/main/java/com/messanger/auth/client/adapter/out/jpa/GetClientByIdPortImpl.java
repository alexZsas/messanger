package com.messanger.auth.client.adapter.out.jpa;

import com.messanger.auth.client.adapter.mapper.ClientMapper;
import com.messanger.auth.client.adapter.out.jpa.repository.JpaClientRepository;
import com.messanger.auth.client.application.port.out.GetClientByIdPort;
import com.messanger.auth.client.domain.Client;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetClientByIdPortImpl extends AbstractJpaClientPort implements GetClientByIdPort {
    public GetClientByIdPortImpl(JpaClientRepository repository, PasswordEncoder encoder, ClientMapper mapper) {
        super(repository, encoder, mapper);
    }

    @Override
    public Optional<Client> get(String id) {
        return repository.findById(id)
                .map(mapper::mapClient);
    }
}
