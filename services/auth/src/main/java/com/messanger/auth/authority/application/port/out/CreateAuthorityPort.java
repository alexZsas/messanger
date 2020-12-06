package com.messanger.auth.authority.application.port.out;

import com.messanger.auth.authority.domain.Authority;

public interface CreateAuthorityPort {
    void create(Authority authority);
}
