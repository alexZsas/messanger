package com.messanger.auth.authority.application.port.out;

import com.messanger.auth.authority.domain.Authority;

public interface SendAuthorityUpdatedEventPort {
    void send(Authority authority);
}
