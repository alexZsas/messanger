package com.messanger.auth.user.adapter.out.jpa;

import com.messanger.auth.user.adapter.mapper.UserMapper;
import com.messanger.auth.user.adapter.out.jpa.repository.JpaUserRepository;
import com.messanger.auth.user.application.port.out.ExistsUserByIdPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ExistsUserByIdPortImpl extends AbstractJpaUserPort implements ExistsUserByIdPort {
    public ExistsUserByIdPortImpl(JpaUserRepository repository, PasswordEncoder encoder, UserMapper mapper) {
        super(repository, encoder, mapper);
    }

    @Override
    public boolean exists(String id) {
        return repository.existsById(id);
    }
}
