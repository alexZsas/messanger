package com.messanger.auth.authority.application.port.in;

import com.messanger.auth.authority.domain.Authority;

import java.util.Optional;

public interface GetAuthorityByIdUseCase {
    Optional<Authority> get(String id);
}
