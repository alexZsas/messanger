package com.messanger.auth.authority.application.port.out;

public interface SendAuthorityDeletedEventPort {
    void send(String authorityId);
}
