package com.messanger.auth.client.adapter.out.jpa;

import com.messanger.auth.client.adapter.mapper.ClientMapper;
import com.messanger.auth.client.adapter.out.jpa.repository.JpaClientRepository;
import com.messanger.auth.client.application.port.out.PageClientsPort;
import com.messanger.auth.client.domain.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PageClientsPortImpl extends AbstractJpaClientPort implements PageClientsPort {
    public PageClientsPortImpl(JpaClientRepository repository, PasswordEncoder encoder, ClientMapper mapper) {
        super(repository, encoder, mapper);
    }

    @Override
    public Page<Client> get(Pageable pageable) {
        return mapper.mapClients(repository.findAll(pageable));
    }
}
