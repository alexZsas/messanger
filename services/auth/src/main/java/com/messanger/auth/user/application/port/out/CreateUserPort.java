package com.messanger.auth.user.application.port.out;

import com.messanger.auth.user.domain.User;

public interface CreateUserPort {
    void create(User user);
}
