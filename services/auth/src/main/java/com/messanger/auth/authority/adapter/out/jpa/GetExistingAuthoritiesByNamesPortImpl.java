package com.messanger.auth.authority.adapter.out.jpa;

import com.messanger.auth.authority.adapter.mapper.AuthorityMapper;
import com.messanger.auth.authority.adapter.out.jpa.repository.JpaAuthorityRepository;
import com.messanger.auth.authority.application.port.out.GetExistingAuthoritiesByNamesPort;
import com.messanger.auth.authority.domain.Authority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class GetExistingAuthoritiesByNamesPortImpl extends AbstractJpaAuthorityPort implements GetExistingAuthoritiesByNamesPort {
    public GetExistingAuthoritiesByNamesPortImpl(AuthorityMapper mapper, JpaAuthorityRepository repository) {
        super(mapper, repository);
    }

    @Override
    public Collection<Authority> get(Collection<String> authorityNames) {
        return mapper.toAuthorities(repository.findAllByNameIn(authorityNames));
    }
}
