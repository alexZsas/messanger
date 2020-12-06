package com.messanger.auth.client.adapter.out.jpa;

import com.messanger.auth.client.adapter.mapper.ClientMapper;
import com.messanger.auth.client.adapter.out.jpa.repository.JpaClientRepository;
import com.messanger.auth.client.application.port.out.DeleteClientPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DeleteClientPortImpl extends AbstractJpaClientPort implements DeleteClientPort {
    public DeleteClientPortImpl(JpaClientRepository repository, PasswordEncoder encoder, ClientMapper mapper) {
        super(repository, encoder, mapper);
    }

    @Override
    public void delete(String id) {
        repository.findById(id)
                .map(u -> u.setEnabled(false))
                .map(repository::save)
                .orElseThrow();
    }
}
