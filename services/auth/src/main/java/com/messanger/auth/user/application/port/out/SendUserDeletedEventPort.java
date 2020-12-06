package com.messanger.auth.user.application.port.out;

public interface SendUserDeletedEventPort {
    void send(String userId);
}
