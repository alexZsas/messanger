package com.messanger.auth.user.adapter.out.jpa;

import com.messanger.auth.user.adapter.mapper.UserMapper;
import com.messanger.auth.user.adapter.out.jpa.repository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public abstract class AbstractJpaUserPort {
    protected final JpaUserRepository repository;
    protected final PasswordEncoder encoder;
    protected final UserMapper mapper;
}
