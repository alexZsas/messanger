package com.messanger.auth.authority.application;

import com.messanger.auth.authority.application.port.in.GetAuthorityByIdUseCase;
import com.messanger.auth.authority.application.port.out.GetAuthorityByIdPort;
import com.messanger.auth.authority.domain.Authority;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetAuthorityByIdUseCaseImpl implements GetAuthorityByIdUseCase {
    private final GetAuthorityByIdPort getAuthorityByIdPort;

    @Override
    public Optional<Authority> get(String id) {
        return getAuthorityByIdPort.get(id);
    }
}
