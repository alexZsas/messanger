package com.messanger.auth.client.adapter.out.jpa;

import com.messanger.auth.client.adapter.mapper.ClientMapper;
import com.messanger.auth.client.adapter.out.jpa.repository.JpaClientRepository;
import com.messanger.auth.client.application.port.out.CreateClientPort;
import com.messanger.auth.client.domain.Client;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CreateClientPortImpl extends AbstractJpaClientPort implements CreateClientPort {
    public CreateClientPortImpl(JpaClientRepository repository, PasswordEncoder encoder, ClientMapper mapper) {
        super(repository, encoder, mapper);
    }

    @Override
    public void create(Client client) {
        String encodedPassword = encoder.encode(client.getPassword());
        client.setPassword(encodedPassword);

        repository.save(mapper.mapClient(client));
    }
}
