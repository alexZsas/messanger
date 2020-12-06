package com.messanger.auth.user.adapter.out.jpa;

import com.messanger.auth.user.adapter.mapper.UserMapper;
import com.messanger.auth.user.adapter.out.jpa.repository.JpaUserRepository;
import com.messanger.auth.user.application.port.out.ExistsUserByUsernamePort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ExistsUserByUsernamePortImpl extends AbstractJpaUserPort implements ExistsUserByUsernamePort {
    public ExistsUserByUsernamePortImpl(JpaUserRepository repository, PasswordEncoder encoder, UserMapper mapper) {
        super(repository, encoder, mapper);
    }

    @Override
    public boolean exists(String username) {
        return repository.existsByUsername(username);
    }
}
