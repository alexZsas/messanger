package com.messanger.auth.authority.adapter.out.jpa;

import com.messanger.auth.authority.adapter.mapper.AuthorityMapper;
import com.messanger.auth.authority.adapter.out.jpa.repository.JpaAuthorityRepository;
import com.messanger.auth.authority.application.port.out.PageAuthoritiesPort;
import com.messanger.auth.authority.domain.Authority;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class PageAuthoritiesPortImpl extends AbstractJpaAuthorityPort implements PageAuthoritiesPort {
    public PageAuthoritiesPortImpl(AuthorityMapper mapper, JpaAuthorityRepository repository) {
        super(mapper, repository);
    }

    @Override
    public Page<Authority> get(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toAuthority);
    }
}
