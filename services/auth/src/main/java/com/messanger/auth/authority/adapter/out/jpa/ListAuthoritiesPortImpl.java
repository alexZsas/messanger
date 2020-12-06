package com.messanger.auth.authority.adapter.out.jpa;

import com.messanger.auth.authority.adapter.mapper.AuthorityMapper;
import com.messanger.auth.authority.adapter.out.jpa.repository.JpaAuthorityRepository;
import com.messanger.auth.authority.application.port.out.ListAuthoritiesPort;
import com.messanger.auth.authority.domain.Authority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class ListAuthoritiesPortImpl extends AbstractJpaAuthorityPort implements ListAuthoritiesPort {
    public ListAuthoritiesPortImpl(AuthorityMapper mapper, JpaAuthorityRepository repository) {
        super(mapper, repository);
    }

    @Override
    public Collection<Authority> get() {
        return mapper.toAuthorities(repository.findAll());
    }
}
