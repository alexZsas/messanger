package com.messanger.auth.authority.application.port.in;

import com.messanger.auth.authority.domain.Authority;

import java.util.Collection;

public interface ListAuthoritiesUseCase {
    Collection<Authority> get();
}
