package com.messanger.auth.user.adapter.out.jpa;

import com.messanger.auth.user.adapter.mapper.UserMapper;
import com.messanger.auth.user.adapter.out.jpa.repository.JpaUserRepository;
import com.messanger.auth.user.application.port.out.GetAllUsersByIdsPort;
import com.messanger.auth.user.domain.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class GetAllUsersByIdsPortImpl extends AbstractJpaUserPort implements GetAllUsersByIdsPort {
    public GetAllUsersByIdsPortImpl(JpaUserRepository repository, PasswordEncoder encoder, UserMapper mapper) {
        super(repository, encoder, mapper);
    }

    @Override
    public Collection<User> get(Collection<String> ids) {
        return mapper.toUsers(repository.findAllById(ids));
    }
}
