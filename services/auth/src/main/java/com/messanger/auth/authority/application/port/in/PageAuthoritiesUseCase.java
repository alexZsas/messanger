package com.messanger.auth.authority.application.port.in;

import com.messanger.auth.authority.domain.Authority;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PageAuthoritiesUseCase {
    Page<Authority> page(Pageable pageable);
}
