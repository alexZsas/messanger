package com.messanger.auth.user.application.port.out;

public interface ExistsUserByUsernamePort {
    boolean exists(String username);
}
