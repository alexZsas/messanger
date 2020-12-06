package com.messanger.auth.authority.application.port.out;

import com.messanger.auth.authority.domain.Authority;

import java.util.Collection;

public interface GetExistingAuthoritiesByIdsPort {
    Collection<Authority> get(Collection<String> authorityIds);
}
