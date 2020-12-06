package com.messanger.auth.client.adapter.out.jpa;

import com.messanger.auth.client.adapter.mapper.ClientMapper;
import com.messanger.auth.client.adapter.out.jpa.repository.JpaClientRepository;
import com.messanger.auth.client.application.port.out.GetAllClientsByIdsPort;
import com.messanger.auth.client.domain.Client;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class GetAllClientsByIdsPortImpl extends AbstractJpaClientPort implements GetAllClientsByIdsPort {
    public GetAllClientsByIdsPortImpl(JpaClientRepository repository, PasswordEncoder encoder, ClientMapper mapper) {
        super(repository, encoder, mapper);
    }

    @Override
    public Collection<Client> getByIds(Collection<String> ids) {
        return mapper.mapClients(repository.findAllById(ids));
    }
}
