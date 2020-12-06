package com.messanger.auth.authority.application;

import com.messanger.auth.authority.application.port.in.ListAuthoritiesByNamesUseCase;
import com.messanger.auth.authority.application.port.out.GetExistingAuthoritiesByNamesPort;
import com.messanger.auth.authority.domain.Authority;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class ListAuthoritiesByNamesUseCaseImpl implements ListAuthoritiesByNamesUseCase {
    private final GetExistingAuthoritiesByNamesPort getExistingAuthoritiesByNamesPort;

    @Override
    public Collection<Authority> get(Collection<String> id) {
        return getExistingAuthoritiesByNamesPort.get(id);
    }
}
