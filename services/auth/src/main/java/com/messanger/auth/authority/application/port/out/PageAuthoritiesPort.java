package com.messanger.auth.authority.application.port.out;

import com.messanger.auth.authority.domain.Authority;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PageAuthoritiesPort {
    Page<Authority> get(Pageable pageable);
}
