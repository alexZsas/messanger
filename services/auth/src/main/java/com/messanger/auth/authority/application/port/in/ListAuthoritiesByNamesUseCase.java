package com.messanger.auth.authority.application.port.in;

import com.messanger.auth.authority.domain.Authority;

import java.util.Collection;

public interface ListAuthoritiesByNamesUseCase {
    Collection<Authority> get(Collection<String> ids);
}
