package com.messanger.auth.user.application.port.out;

import com.messanger.auth.user.domain.User;

import java.util.Collection;

public interface GetAllUsersPort {
    Collection<User> getAll();
}
