package com.messanger.auth.user.adapter.out.jpa;

import com.messanger.auth.user.adapter.mapper.UserMapper;
import com.messanger.auth.user.adapter.out.jpa.repository.JpaUserRepository;
import com.messanger.auth.user.application.port.out.GetAllUsersPort;
import com.messanger.auth.user.domain.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class GetAllUsersPortImpl extends AbstractJpaUserPort implements GetAllUsersPort {
    public GetAllUsersPortImpl(JpaUserRepository repository, PasswordEncoder encoder, UserMapper mapper) {
        super(repository, encoder, mapper);
    }

    @Override
    public Collection<User> getAll() {
        return mapper.toUsers(repository.findAll());
    }
}
