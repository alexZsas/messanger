package com.messanger.auth.user.application.port.out;

import com.messanger.auth.user.domain.User;

import java.util.Collection;

public interface GetAllUsersByIdsPort {
    Collection<User> get(Collection<String> ids);
}
