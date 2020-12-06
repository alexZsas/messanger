package com.messanger.auth.client.application.port.out;

public interface SendClientDeletedEventPort {
    void send(String userId);
}
