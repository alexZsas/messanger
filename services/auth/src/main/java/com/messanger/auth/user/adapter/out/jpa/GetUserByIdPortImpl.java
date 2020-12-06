package com.messanger.auth.user.adapter.out.jpa;

import com.messanger.auth.user.adapter.mapper.UserMapper;
import com.messanger.auth.user.adapter.out.jpa.repository.JpaUserRepository;
import com.messanger.auth.user.application.port.out.GetUserByIdPort;
import com.messanger.auth.user.domain.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetUserByIdPortImpl extends AbstractJpaUserPort implements GetUserByIdPort {
    public GetUserByIdPortImpl(JpaUserRepository repository, PasswordEncoder encoder, UserMapper mapper) {
        super(repository, encoder, mapper);
    }

    @Override
    public Optional<User> get(String id) {
        return repository.findById(id).map(mapper::toUser);
    }
}
