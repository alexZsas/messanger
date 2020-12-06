package com.messanger.auth.authority.application;

import com.messanger.auth.authority.application.port.in.ListAuthoritiesUseCase;
import com.messanger.auth.authority.application.port.out.ListAuthoritiesPort;
import com.messanger.auth.authority.domain.Authority;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class ListAuthoritiesUseCaseImpl implements ListAuthoritiesUseCase {
    private final ListAuthoritiesPort listAuthoritiesPort;

    @Override
    public Collection<Authority> get() {
        return listAuthoritiesPort.get();
    }
}
