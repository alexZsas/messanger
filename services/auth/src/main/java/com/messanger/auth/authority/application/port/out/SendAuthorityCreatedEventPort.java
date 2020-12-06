package com.messanger.auth.authority.application.port.out;

import com.messanger.auth.authority.domain.Authority;

public interface SendAuthorityCreatedEventPort {
    void send(Authority authority);
}
