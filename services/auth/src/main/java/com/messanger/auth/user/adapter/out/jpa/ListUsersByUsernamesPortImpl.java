package com.messanger.auth.user.adapter.out.jpa;

import com.messanger.auth.user.adapter.mapper.UserMapper;
import com.messanger.auth.user.adapter.out.jpa.repository.JpaUserRepository;
import com.messanger.auth.user.application.port.out.ListUsersByUsernamesPort;
import com.messanger.auth.user.domain.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class ListUsersByUsernamesPortImpl extends AbstractJpaUserPort implements ListUsersByUsernamesPort {
    public ListUsersByUsernamesPortImpl(JpaUserRepository repository, PasswordEncoder encoder, UserMapper mapper) {
        super(repository, encoder, mapper);
    }

    @Override
    public Collection<User> get(Collection<String> usernames) {
        return mapper.toUsers(repository.findAllByUsernameIn(usernames));
    }
}
