package com.messanger.auth.user.application.port;

import com.messanger.auth.user.application.port.in.ListUsersByIdsUseCase;
import com.messanger.auth.user.application.port.out.GetAllUsersByIdsPort;
import com.messanger.auth.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class ListUsersByIdsUseCaseImpl implements ListUsersByIdsUseCase {
    private final GetAllUsersByIdsPort getAllUsersByIdsPort;

    @Override
    public Collection<User> list(Collection<String> ids) {
        return getAllUsersByIdsPort.get(ids);
    }
}
