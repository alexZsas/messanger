package com.messanger.auth.user.application.port.out;

import com.messanger.auth.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PageUsersPort {
    Page<User> get(Pageable pageable);
}
