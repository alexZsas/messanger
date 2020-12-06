package com.messanger.auth.user.application.port.in;

import com.messanger.auth.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PageUsersUseCase {
    Page<User> page(Pageable pageable);
}
