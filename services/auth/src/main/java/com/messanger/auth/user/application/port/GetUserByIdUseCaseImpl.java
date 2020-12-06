package com.messanger.auth.user.application.port;

import com.messanger.auth.user.application.port.in.GetUserByIdUseCase;
import com.messanger.auth.user.application.port.out.GetUserByIdPort;
import com.messanger.auth.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetUserByIdUseCaseImpl implements GetUserByIdUseCase {
    private final GetUserByIdPort getUserByIdPort;

    @Override
    public Optional<User> get(String id) {
        return getUserByIdPort.get(id);
    }
}
