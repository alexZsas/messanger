package com.messanger.auth.authority.adapter.out.jpa;

import com.messanger.auth.authority.adapter.mapper.AuthorityMapper;
import com.messanger.auth.authority.adapter.out.jpa.repository.JpaAuthorityRepository;
import com.messanger.auth.authority.application.port.out.ExistsAuthorityByNamePort;
import org.springframework.stereotype.Component;

@Component
public class ExistsAuthorityByNamePortImpl extends AbstractJpaAuthorityPort implements ExistsAuthorityByNamePort {
    public ExistsAuthorityByNamePortImpl(AuthorityMapper mapper, JpaAuthorityRepository repository) {
        super(mapper, repository);
    }

    @Override
    public boolean exists(String name) {
        return repository.existsByName(name);
    }
}
