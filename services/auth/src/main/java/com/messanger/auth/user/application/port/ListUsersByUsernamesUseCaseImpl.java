package com.messanger.auth.user.application.port;

import com.messanger.auth.user.application.port.in.ListUsersByUsernamesUseCase;
import com.messanger.auth.user.application.port.out.ListUsersByUsernamesPort;
import com.messanger.auth.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class ListUsersByUsernamesUseCaseImpl implements ListUsersByUsernamesUseCase {
    private final ListUsersByUsernamesPort listUsersByUsernamesPort;

    @Override
    public Collection<User> list(Collection<String> usernames) {
        return listUsersByUsernamesPort.get(usernames);
    }
}
