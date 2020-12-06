package com.messanger.auth.authority.adapter.out.jpa;

import com.messanger.auth.authority.adapter.mapper.AuthorityMapper;
import com.messanger.auth.authority.adapter.out.jpa.repository.JpaAuthorityRepository;
import com.messanger.auth.authority.application.port.out.GetAuthorityByIdPort;
import com.messanger.auth.authority.domain.Authority;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetAuthorityByIdPortImpl extends AbstractJpaAuthorityPort implements GetAuthorityByIdPort {
    public GetAuthorityByIdPortImpl(AuthorityMapper mapper, JpaAuthorityRepository repository) {
        super(mapper, repository);
    }

    @Override
    public Optional<Authority> get(String id) {
        return repository.findById(id).map(mapper::toAuthority);
    }
}
