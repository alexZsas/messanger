package com.messanger.auth.authority.application.port.out;

import com.messanger.auth.authority.domain.Authority;

import java.util.Optional;

public interface GetAuthorityByIdPort {
    Optional<Authority> get(String id);
}
