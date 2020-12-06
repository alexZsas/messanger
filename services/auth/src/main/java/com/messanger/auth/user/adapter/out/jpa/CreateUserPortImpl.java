package com.messanger.auth.user.adapter.out.jpa;

import com.messanger.auth.user.adapter.mapper.UserMapper;
import com.messanger.auth.user.adapter.out.jpa.repository.JpaUserRepository;
import com.messanger.auth.user.application.port.out.CreateUserPort;
import com.messanger.auth.user.domain.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CreateUserPortImpl extends AbstractJpaUserPort implements CreateUserPort {
    public CreateUserPortImpl(JpaUserRepository repository, PasswordEncoder encoder, UserMapper mapper) {
        super(repository, encoder, mapper);
    }

    @Override
    public void create(User user) {
        String encoded = encoder.encode(user.getPassword());
        user.setPassword(encoded);

        repository.save(mapper.toUserEntity(user));
    }
}
