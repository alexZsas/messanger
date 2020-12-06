package com.messanger.auth.user.application.port;

import com.messanger.auth.user.application.port.in.PageUsersUseCase;
import com.messanger.auth.user.application.port.out.PageUsersPort;
import com.messanger.auth.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PageUsersUseCaseImpl implements PageUsersUseCase {
    private final PageUsersPort pageUsersPort;

    @Override
    public Page<User> page(Pageable pageable) {
        return pageUsersPort.get(pageable);
    }
}
