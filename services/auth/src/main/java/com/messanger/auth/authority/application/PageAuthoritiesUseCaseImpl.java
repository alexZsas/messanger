package com.messanger.auth.authority.application;

import com.messanger.auth.authority.application.port.in.PageAuthoritiesUseCase;
import com.messanger.auth.authority.application.port.out.PageAuthoritiesPort;
import com.messanger.auth.authority.domain.Authority;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PageAuthoritiesUseCaseImpl implements PageAuthoritiesUseCase {
    private final PageAuthoritiesPort pageAuthoritiesPort;
    @Override
    public Page<Authority> page(Pageable pageable) {
        return pageAuthoritiesPort.get(pageable);
    }
}
