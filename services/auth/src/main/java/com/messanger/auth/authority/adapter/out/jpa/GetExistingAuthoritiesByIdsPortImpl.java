package com.messanger.auth.authority.adapter.out.jpa;

import com.messanger.auth.authority.adapter.mapper.AuthorityMapper;
import com.messanger.auth.authority.adapter.out.jpa.repository.JpaAuthorityRepository;
import com.messanger.auth.authority.application.port.out.GetExistingAuthoritiesByIdsPort;
import com.messanger.auth.authority.domain.Authority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class GetExistingAuthoritiesByIdsPortImpl extends AbstractJpaAuthorityPort implements GetExistingAuthoritiesByIdsPort {
    public GetExistingAuthoritiesByIdsPortImpl(AuthorityMapper mapper, JpaAuthorityRepository repository) {
        super(mapper, repository);
    }

    @Override
    public Collection<Authority> get(Collection<String> authorityIds) {
        return mapper.toAuthorities(repository.findAllById(authorityIds));
    }
}
